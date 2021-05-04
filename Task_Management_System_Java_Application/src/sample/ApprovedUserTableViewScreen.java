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

//ApprovedUserTableViewScreen class displays a table of Approved Users
public class ApprovedUserTableViewScreen extends Application {

    private int adminID;

    //Constructor
    public ApprovedUserTableViewScreen(int ID)
    {
        this.adminID = ID;
    }

    public void start(Stage stage)
    {
        TableView<ApprovedUser> tableView = new TableView<>();

        //Initializes the ObservableList for approved users
        ObservableList<ApprovedUser> approvedUserObservableList = GetApprovedUserList.retrieveApprovedUserList();

        //Add rows to the TableView
        tableView.getItems().addAll(approvedUserObservableList);

        //Add columns to the TableView
        tableView.getColumns().addAll(ApprovedUserTableViewHelper.getUsernameColumn(), ApprovedUserTableViewHelper.getPasswordColumn(), ApprovedUserTableViewHelper.addDeleteButtonToTable());

        //Set the column resize policy to constrained resize policy
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Refresh Button
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ApprovedUserTableViewHelper.refreshPage(stage, adminID);
            }
        });

        //Return to Menu Button
        Button returnToAdminMenu = new Button("Return to Menu");
        returnToAdminMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ApprovedUserTableViewHelper.returnToMenuPage(stage, adminID);
            }
        });

        //Set HBox and align Nodes
        HBox leftBox = new HBox(refreshButton);
        leftBox.setAlignment(Pos.TOP_LEFT);

        HBox rightBox = new HBox(returnToAdminMenu);
        rightBox.setAlignment(Pos.TOP_RIGHT);

        HBox hbox = new HBox(leftBox, rightBox);

        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        //Create the VBox
        VBox root = new VBox();
        root.setPrefWidth(600);

        //Add the Table to the VBox
        root.getChildren().addAll(tableView, hbox);

        //Set the Padding and Border for the VBox
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

        //Set the Title of the Stage
        stage.setTitle("Approved User List");

        //Display the Stage
        stage.show();
    }

}
