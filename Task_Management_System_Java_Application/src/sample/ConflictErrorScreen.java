package sample;

import com.mysql.cj.protocol.Resultset;
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
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConflictErrorScreen extends Application {

    private boolean check = false;

    public void start(Stage stage)
    {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: PINK;");
        gridPane.setMinSize(50, 50);
        gridPane.setPadding(new Insets(10, 10, 10, 10));


        Text text = new Text("Error: This task conflicts with another one on your calendar");

        Text text1 = new Text("Would you like to continue:");

        gridPane.add(text, 0, 0);
        GridPane.setHalignment(text, HPos.CENTER);

        text.setStyle("-fx-font: normal bold 20px 'serif'");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");


        Button yes= new Button("Yes");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Yes button pushed");
                check = true;
                stage.close();
            }  });

        Button no = new Button("No");
        no.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("no button pushed");
                check = false;
                stage.close();
            }  });

        HBox hBox = new HBox(text1, yes, no);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setSpacing(10);

        gridPane.add(hBox, 0, 1);
        //GridPane.setHalignment(hBox, HPos.LEFT);
        //yes.setMaxWidth(Double.MAX_VALUE);
        //no.setMaxWidth(Double.MAX_VALUE);
        //gridPane.add(yes, 0, 1);;
        //GridPane.setHalignment(yes, HPos.CENTER);
       // gridPane.setHgap(100);

        //gridPane.add(no, 0, 1);
        //GridPane.setHalignment(no, HPos.CENTER);
        /*GridPane.setHgrow(text1, Priority.ALWAYS);
        GridPane.setHgrow(yes, Priority.ALWAYS);
        GridPane.setHgrow(no, Priority.ALWAYS);
*/
        stage.setTitle("Error");
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();


    }

    public boolean setCheck()
    {
        return check;
    }

}
