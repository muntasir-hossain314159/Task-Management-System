package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchTasksTableViewScreen extends Application {

    private int userID;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String duration;
    private String title;
    private String description;

    private String weekFromStartDate;
    private boolean check = false;

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

        ObservableList<Task> observableList;
        if(!check)
            observableList = GetTaskList.retrieveTaskList(userID, startDate, startTime, endDate, endTime, duration, title, description);
        else
            observableList = GetTaskList.retrieveTaskList(userID, startDate, weekFromStartDate);

        tableView.getItems().addAll(observableList);

        tableView.getColumns().addAll(SearchTasksTableViewHelper.getTitleColumn(), SearchTasksTableViewHelper.getStartTime(), SearchTasksTableViewHelper.getEndTime(), SearchTasksTableViewHelper.getDuration(), SearchTasksTableViewHelper.getDescriptionOfTask(), SearchTasksTableViewHelper.addEditButtonToTable(), SearchTasksTableViewHelper.addDeleteButtonToTable());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        //tableView.setPlaceholder(new Label("No visible columns and/or data exist."));
        //*/

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!check)
                    SearchTasksTableViewHelper.refreshPage(stage, userID, startDate, startTime, endDate, endTime, duration, title, description);
                else
                    new SearchTasksTableViewScreen(userID, startDate, weekFromStartDate, true).start(stage);
            }
        });

        Button returnToUserMenu = new Button("Return");
        returnToUserMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(!check)
                    SearchTasksTableViewHelper.returnToSearchTasksPage(stage, userID);
                else
                    new UserMenu(userID).start(stage);
            }
        });

        HBox leftBox = new HBox(refreshButton);
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(returnToUserMenu);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        HBox hbox = new HBox(leftBox, rightBox);

        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);


        // Create the VBox
        VBox root = new VBox();
        // Add the Table to the VBox
        root.getChildren().addAll(tableView, hbox);
        root.setPrefWidth(1000);
        //root.setAlignment(Pos.CENTER);
        // Set the Padding and Border for the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Task List");
        stage.centerOnScreen();

        //stage.setFullScreen(true);
        // Display the Stage
        stage.show();

    }
}
