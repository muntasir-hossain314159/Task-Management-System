package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Display Username or Password Warning Screen if the wrong username or password is inputted during log in
public class UsernamePasswordWarningScreen extends Application {

    public void start(Stage stage)
    {
        //Texts
        Text text = new Text("Wrong Username or Password!");
        Text text1 = new Text("Please try again");

        //GridPane set up
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Add Nodes to GridPane and set alignment
        gridPane.add(text, 0, 0);
        gridPane.add(text1, 0, 1);
        GridPane.setHalignment(text1, HPos.CENTER);

        //Styling GridPane and Nodes
        text.setStyle("-fx-font: normal bold 20px 'serif'");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        gridPane.setStyle("-fx-background-color: PINK;");

        //Set Scene and Stage
        Scene scene = new Scene(gridPane);
        stage.setTitle("Warning");
        stage.setScene(scene);
        stage.show();
    }
}
