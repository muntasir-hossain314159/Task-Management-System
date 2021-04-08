package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Set;

public class TableViewScreen extends Application {

    private int adminID;
    public TableViewScreen(int ID)
    {
        this.adminID = ID;
    }

    public void start(Stage stage)
    {
        TableView tableView = new TableView();

        ObservableList<NewUser> newUserList = GetNewUserList.retrieveNewUserList(adminID);
        // Add rows to the TableView
        tableView.getItems().addAll(newUserList);
        // Add columns to the TableView
        tableView.getColumns().addAll(TableViewHelper.getUsernameColumn(), TableViewHelper.getPasswordColumn(), TableViewHelper.addApproveButtonToTable(), TableViewHelper.addRejectButtonToTable());

        ///*
        //Set the column resize policy to constrained resize policy
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        //tableView.setPlaceholder(new Label("No visible columns and/or data exist."));
        //*/

        // Create the VBox
        VBox root = new VBox();
        // Add the Table to the VBox
        root.getChildren().add(tableView);
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

        stage.setFullScreen(true);
        // Display the Stage
        stage.show();
    }

}
