package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Password Length Warning Screen is displayed when the length of a password during user or admin sign up is less than 8 character
public class PasswordLengthWarningScreen extends Application {
    public void start(Stage stage)
    {
        //Texts
        Text text = new Text("Password must be at least 8 characters long!");
        Text text1 = new Text("Please try again");

        //GridPane set up
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        //Add Nodes to GridPane and set alignment
        gridPane.add(text, 0, 0);
        gridPane.add(text1, 0, 1);
        GridPane.setHalignment(text1, HPos.CENTER);

        //Styling Nodes and GridPane
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
