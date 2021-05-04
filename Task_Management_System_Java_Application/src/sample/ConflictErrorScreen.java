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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Conflict Error Screen is displayed when a user tries to create a task that conflicts with a pre-existing task
public class ConflictErrorScreen extends Application {

    private boolean check = false;

    public void start(Stage stage)
    {
        //GridPane set up
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        //GridPane style
        gridPane.setStyle("-fx-background-color: PINK;");

        //Texts
        Text text0 = new Text("Error: This task conflicts with another one on your calendar");
        Text text1 = new Text("Would you like to continue:");

        //Texts style
        text0.setStyle("-fx-font: normal bold 20px 'serif'");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");

        //Add text0 to GridPane
        gridPane.add(text0, 0, 0);
        GridPane.setHalignment(text0, HPos.CENTER);

        //Yes button
        Button yes= new Button("Yes");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Yes button pushed");
                check = true;
                stage.close();
            }  });

        //No button
        Button no = new Button("No");
        no.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("No button pushed");
                check = false;
                stage.close();
            }  });

        //Add text1, Yes and No button into an HBox and align Nodes
        HBox hBox = new HBox(text1, yes, no);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);

        //Add hBox to gridPane
        gridPane.add(hBox, 0, 1);

        //Set Scene and Stage
        stage.setTitle("Error");
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    //Returns the value of the boolean check
    public boolean setCheck()
    {
        //check = true, if user clicks on the Yes button
        //check = false, if user clicks on the No button
        //check = false, if user closes the error screen window

        return check;
    }

}
