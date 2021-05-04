package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Create Task Information displays a help page when creating a new task
public class CreateTaskInformation extends Application {

    public void start(Stage stage)
    {
        //Texts
        Text search = new Text("Create Task Information:\n");
        Text text0 = new Text("1. Title");
        Text text1 = new Text("2. Start Date (MM/DD/YYYY)");
        Text text2 = new Text("3. Start Time (HH:MM)/(HH:MM:SS)");
        Text text3 = new Text("4. End Date (MM/DD/YYYY)");
        Text text4 = new Text("5. End Time (HH:MM)/(HH:MM:SS)");
        Text text5 = new Text("6. Description\n");
        Text text13 = new Text("Note: Start Date and End Date are required fields");
        Text text14 = new Text("Note: Click 'Enter' after typing in the start and/or end date");

        //VBox set up
        VBox root = new VBox();

        root.setSpacing(5);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setMinSize(300, 300);

        //Add Nodes to VBox
        root.getChildren().addAll(search, text0, text1, text2, text3, text4, text5, text13, text14);

        //Set GridPane and Nodes style
        root.setStyle("-fx-background-color: BEIGE;");
        search.setStyle("-fx-font: normal bold 18px 'serif'");

        //Set Scene and Stage
        Scene scene = new Scene(root);
        stage.setTitle("Help");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
