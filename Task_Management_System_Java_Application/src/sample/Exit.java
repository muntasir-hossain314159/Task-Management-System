package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Exit Screen for closing the application
public class Exit extends Application {

    private int userID;

    //Constructor
    public Exit(int userID)
    {
        this.userID = userID;
    }

    public void start(Stage stage)
    {
        //Text
        Text text = new Text("Thank You for using Task Management System");

        //GridPane set up
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);

        //Close button
        Button close= new Button("Close");
        close.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Close button pushed");
                stage.close();
            }  });

        //Menu button
        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Menu button pushed");
                UserMenu userMenu = new UserMenu(userID);
                userMenu.start(stage);
            }
        });


        //Add Nodes to GridPane and set alignment
        gridPane.add(text, 0, 0);
        GridPane.setHalignment(text, HPos.CENTER);

        gridPane.add(menu, 0, 1);
        GridPane.setHalignment(menu, HPos.LEFT);

        gridPane.add(close, 0, 1);
        GridPane.setHalignment(close, HPos.RIGHT);

        //Styling GridPane and Nodes
        gridPane.setStyle("-fx-background-color: rgb(102, 204, 255);");
        text.setStyle("-fx-font: normal bold 20px 'serif'");

        //Set Scene and Stage
        stage.setTitle("Thank You");
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
}
