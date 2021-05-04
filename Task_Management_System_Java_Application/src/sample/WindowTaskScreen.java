package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Display Window Task Screen to inform a user if a task has been added successfully, or if the task could not be added
public class WindowTaskScreen extends Application {

    public void start(Stage stage, boolean createdTaskSuccessfully)
    {
        //GridPane set up
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Styling GridPane
        gridPane.setStyle("-fx-background-color: PINK;");

        if(createdTaskSuccessfully)
        {
            //Texts
            Text text = new Text("Task added Successfully!");

            //Add Node to GridPane
            gridPane.add(text, 0, 0);

            //Styling Node
            text.setStyle("-fx-font: normal bold 20px 'serif'");

            //Set Stage title
            stage.setTitle("Task Added");

        }
        else
        {
            //Texts
            Text text = new Text("Error, Task could not be added!");
            Text text1 = new Text("Please input a valid start date and end date");

            //Add Nodes to GridPane and set alignment
            gridPane.add(text, 0, 0);
            GridPane.setHalignment(text, HPos.CENTER);
            gridPane.add(text1, 0, 1);
            GridPane.setHalignment(text1, HPos.CENTER);

            //Styling Nodes
            text.setStyle("-fx-font: normal bold 20px 'serif'");
            text1.setStyle("-fx-font: normal bold 20px 'serif'");

            //Set Stage title
            stage.setTitle("Warning");
        }

        //Set Scene and Stage
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();

    }

    //Overriding start()
    @Override
    public void start(Stage stage) throws Exception
    { }
}
