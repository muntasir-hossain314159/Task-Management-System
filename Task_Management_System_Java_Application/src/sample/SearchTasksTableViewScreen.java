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

    public void start(Stage stage)
    {
        TableView<Task> tableView = new TableView<>();

        ObservableList<Task> observableList = GetTaskList.retrieveTaskList(userID, startDate, startTime, endDate, endTime, duration, title, description);

        tableView.getItems().addAll(observableList);

        tableView.getColumns().addAll(SearchTasksTableViewHelper.getTitleColumn(), SearchTasksTableViewHelper.getStartTime(), SearchTasksTableViewHelper.getEndTime(), SearchTasksTableViewHelper.getDuration(), SearchTasksTableViewHelper.getDescriptionOfTask(), SearchTasksTableViewHelper.addDeleteButtonToTable());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        //tableView.setPlaceholder(new Label("No visible columns and/or data exist."));
        //*/

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SearchTasksTableViewHelper.refreshPage(stage, userID, startDate, startTime, endDate, endTime, duration, title, description);
            }
        });

        Button returnToUserMenu = new Button("Return to Menu");
        returnToUserMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                SearchTasksTableViewHelper.returnToMenuPage(stage, userID);
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
        root.setPrefWidth(800);
        //root.setAlignment(Pos.CENTER);
        // Set the Padding and Border for the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("New User List");

        //stage.setFullScreen(true);
        // Display the Stage
        stage.show();

    }
}
