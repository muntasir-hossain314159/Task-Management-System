package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jfr.Description;

import java.sql.Time;

public class SearchFunctionInformation extends Application {

    public void start(Stage stage)
    {
        Text search = new Text("Search Options:\n");
        Text text0 = new Text("1. Title");
        Text text1 = new Text("2. Start Date (MM/DD/YYYY)");
        Text text2 = new Text("3. Start Time (HH:MM)/(HH:MM:SS)");
        Text text3 = new Text("4. End Date (MM/DD/YYYY)");
        Text text4 = new Text("5. End Time (HH:MM)/(HH:MM:SS)");
        Text text5 = new Text("6. Duration (Minutes)");
        Text text6 = new Text("7. Description");
        Text text7 = new Text("8. Title and Start Date");
        Text text8 = new Text("9. Title, Start Date, and Start Time");
        Text text9 = new Text("10. Start Date and Start Time");
        Text text10 = new Text("11. Start Date, Start Time, End Date, and End Time");
        Text text11 = new Text("12. Start Date and Duration");
        Text text12 = new Text("13. Title and Description");
        Text text13 = new Text("14. No Input: List all tasks for that particular user\n");
        Text text14 = new Text("Note: Click 'Enter' after typing in the start and/or end date");


        VBox root = new VBox();
        root.setSpacing(5);
        root.getChildren().addAll(search, text0, text1, text2, text3, text4, text5, text6, text7,text8, text9, text10, text11, text12, text13, text14);
        //root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setStyle("-fx-background-color: BEIGE;");


        root.setMinSize(300, 300);
        search.setStyle("-fx-font: normal bold 18px 'serif'");


        Scene scene = new Scene(root);
        stage.setTitle("Help");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
