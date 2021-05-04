package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Warning Screen when an account for an admin or user already exists in TMS
public class AccountExistsWarningScreen extends Application {

    public void start(Stage stage)
    {
        //Texts
        Text text0 = new Text("Account already exists with that username!");
        Text text1 = new Text("Please try again");

        //GridPane set up
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        //Add nodes to GridPane and set alignment
        gridPane.add(text0, 0, 0);
        gridPane.add(text1, 0, 1);
        GridPane.setHalignment(text1, HPos.CENTER);

        //Set GridPane and Nodes style
        text0.setStyle("-fx-font: normal bold 20px 'serif'");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        gridPane.setStyle("-fx-background-color: PINK;");

        //Set Scene and Stage
        Scene scene = new Scene(gridPane);
        stage.setTitle("Warning");
        stage.setScene(scene);
        stage.show();
    }
}
