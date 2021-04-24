package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowTaskScreen extends Application {

    public void start(Stage stage, boolean createdTaskSuccessfully)
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: PINK;");
        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        if(createdTaskSuccessfully)
        {
            Text text = new Text("Task added Successfully!");

            gridPane.add(text, 0, 0);

            text.setStyle("-fx-font: normal bold 20px 'serif'");

            stage.setTitle("Task Added");

        }
        else
        {
            Text text = new Text("Error, Task could not be added!");
            Text text1 = new Text("Please input a valid start date and end date");

            gridPane.add(text, 0, 0);
            GridPane.setHalignment(text, HPos.CENTER);
            gridPane.add(text1, 0, 1);
            GridPane.setHalignment(text1, HPos.CENTER);

            text.setStyle("-fx-font: normal bold 20px 'serif'");
            text1.setStyle("-fx-font: normal bold 20px 'serif'");

            stage.setTitle("Warning");
        }
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void start(Stage stage) throws Exception
    {
    }
}
