package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//SearchTasksTableViewScreen class displays a table of Tasks created by the user
public class SearchTasksTableViewScreen extends Application {

    //Search for Tasks fields
    private int userID;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String duration;
    private String title;
    private String description;

    //Tasks for this Week fields
    private String weekFromStartDate;
    private boolean check = false;

    //Constructor for Search for Tasks page
    public SearchTasksTableViewScreen(int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description) {
        this.userID = userID;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.duration = duration;
        this.title = title;
        this.description = description;
    }

    //Constructor for Tasks for this Week page
    public SearchTasksTableViewScreen(int userID, String startDate, String weekFromStartDate, boolean check)
    {
        this.userID = userID;
        this.startDate = startDate;
        this.weekFromStartDate = weekFromStartDate;
        this.check = check;
    }

    public void start(Stage stage)
    {
        TableView<Task> tableView = new TableView<>();

        //ObservableList for tasks
        ObservableList<Task> observableList;

        //Check == false, Search for Tasks button was pressed
        //Check == true, Tasks for this Week button was pressed
        if(!check)
            observableList = GetTaskList.retrieveTaskList(userID, startDate, startTime, endDate, endTime, duration, title, description);
        else
            observableList = GetTaskList.retrieveTaskList(userID, startDate, weekFromStartDate);

        //Add rows to the TableView
        tableView.getItems().addAll(observableList);

        //Add columns to the TableView
        tableView.getColumns().addAll(SearchTasksTableViewHelper.getTitleColumn(), SearchTasksTableViewHelper.getStartTime(), SearchTasksTableViewHelper.getEndTime(), SearchTasksTableViewHelper.getDuration(), SearchTasksTableViewHelper.getDescriptionOfTask(), SearchTasksTableViewHelper.addEditButtonToTable(), SearchTasksTableViewHelper.addDeleteButtonToTable());

        //Set the column resize policy to unconstrained resize policy
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        //Refresh button
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!check)
                    SearchTasksTableViewHelper.refreshPage(stage, userID, startDate, startTime, endDate, endTime, duration, title, description);
                else
                    new SearchTasksTableViewScreen(userID, startDate, weekFromStartDate, true).start(stage);
            }
        });

        //Return button
        Button returnToUserMenu = new Button("Return");
        returnToUserMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!check)
                    SearchTasksTableViewHelper.returnToSearchTasksPage(stage, userID);
                else
                    new UserMenu(userID).start(stage);
            }
        });

        //Set HBox and align Nodes
        HBox leftBox = new HBox(refreshButton);
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(returnToUserMenu);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        HBox hbox = new HBox(leftBox, rightBox);

        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        //VBox set up
        VBox root = new VBox();
        root.setPrefWidth(1000);

        //Add the TableView to the VBox
        root.getChildren().addAll(tableView, hbox);

        //Styling VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        //Create the Scene
        Scene scene = new Scene(root);

        //Add the Scene to the Stage
        stage.setScene(scene);

        //Set the title of the Stage and center the Stage on screen
        stage.setTitle("Task List");
        stage.centerOnScreen();

        // Display the Stage
        stage.show();
    }
}
