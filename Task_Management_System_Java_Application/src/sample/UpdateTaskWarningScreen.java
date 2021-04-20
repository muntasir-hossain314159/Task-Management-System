package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateTaskWarningScreen extends Application {

    public void start(Stage stage)
    {
        Text text = new Text("Cannot edit task!");
        Text text1 = new Text("Please input a valid start and end date");

        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(text, 0, 0);
        gridPane.add(text1, 0, 1);
        GridPane.setHalignment(text1, HPos.CENTER);

        text.setStyle("-fx-font: normal bold 20px 'serif'");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        gridPane.setStyle("-fx-background-color: PINK;");

        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(gridPane);
        stage.setTitle("Warning");
        stage.setScene(scene);
        stage.show();
    }
}
