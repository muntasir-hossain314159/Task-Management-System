package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;


public class EditTask extends Application {

    private int taskID;
    private int userID;
    private String titleText;
    private Timestamp startTime;
    private String startDateText;
    private String startTimeText;
    private Timestamp endTime;
    private String endDateText;
    private String endTimeText;
    private String descriptionText;


    public EditTask(int taskID, int userID, String titleText, Timestamp startTime, Timestamp endTime, String descriptionText) {
        this.taskID = taskID;
        this.userID = userID;
        this.titleText = titleText;
        this.startTime = startTime;
        this.endTime = endTime;
        this.descriptionText = descriptionText;
    }

    public void start(Stage primaryStage)
    {
        startDateText = startTime.toString().split(" ")[0];
        System.out.println("Start Date: " + startDateText);
        endDateText = endTime.toString().split(" ")[0];
        System.out.println("End date: " + endDateText);

        startTimeText = startTime.toString().split(" ")[1];
        System.out.println("Start Time: " + startTimeText);
        endTimeText = endTime.toString().split(" ")[1];
        System.out.println("End Time: " + endTimeText);

        Text text0 = new Text("Edit Task");

        TextField title = new TextField();
        Text titleName = new Text("Title");

        DatePicker startDate = new DatePicker();
        startDate.setValue(null);
        startDate.setEditable(true);
        startDate.setShowWeekNumbers(true);
        TextField startTime = new TextField();
        Text startTimeName = new Text("Start Time");

        DatePicker endDate = new DatePicker();
        endDate.setValue(null);
        endDate.setEditable(true);
        endDate.setShowWeekNumbers(true);
        TextField endTime = new TextField();
        Text endTimeName = new Text("End Time");

        TextField description = new TextField();
        Text descriptionName = new Text("Description");


        Button cancel = new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Cancel button pushed");
                primaryStage.close();
            }
        });

        Button search = new Button("Submit");
        search.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");

                if(!title.getText().isEmpty())
                    titleText = title.getText();

                LocalDate startDateTime = startDate.getValue();
                if (startDateTime != null)
                    startDateText = startDateTime.toString();

                if(!startTime.getText().isEmpty())
                    startTimeText = startTime.getText();

                LocalDate endDateTime = endDate.getValue();
                if (endDateTime != null)
                    endDateText = endDateTime.toString();

                if(!endTime.getText().isEmpty())
                    endTimeText = endTime.getText();

                if(!description.getText().isEmpty())
                    descriptionText = description.getText();

                //todo how do I take the duration from user?? do i take hours or mins or seconds
                editTask(primaryStage, taskID, userID, startDateText, startTimeText, endDateText, endTimeText, titleText, descriptionText);

                title.setText("");
                startDate.setValue(null);
                startTime.setText("");
                endDate.setValue(null);
                endTime.setText("");
                description.setText("");
                //System.out.println("Search done successfully");
            }
        });

        GridPane gridPane = new GridPane();
        //Setting size for the pane
        gridPane.setMinSize(400, 400);
        //gridPane.setGridLinesVisible(true);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text0, 0, 0, 3, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(titleName, 0, 2, 1, 1);
        gridPane.add(title, 1, 2, 1, 1);

        gridPane.add(startTimeName, 0, 3, 1, 1);
        gridPane.add(startDate, 1, 3, 1, 1);
        gridPane.add(startTime, 2, 3, 1, 1);

        gridPane.add(endTimeName, 0, 4, 1, 1);
        gridPane.add(endDate, 1, 4,1, 1);
        gridPane.add(endTime, 2, 4, 1, 1);


        gridPane.add(descriptionName, 0, 5, 1, 1);
        gridPane.add(description, 1, 5, 1, 1);

        //menu.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(cancel, 0, 6, 1, 1);
        GridPane.setHalignment(cancel, HPos.CENTER);

        search.setMaxHeight(Double.MAX_VALUE);
        gridPane.add(search, 0, 6, 3, 1);
        GridPane.setHalignment(search, HPos.CENTER);

        //GridPane.setHalignment(popupContent, HPos.CENTER);

        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        primaryStage.setTitle("Edit Task");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    private void editTask(Stage stage, int taskID, int userID, String startDateText, String startTime, String endDateText, String endTime, String title, String description)
    {

        String sql = "UPDATE task "
                   + "SET Start_date_time = '" + startDateText + " " + startTime + "', End_date_time = '"  + endDateText + " " +  endTime + "', Duration = TIMESTAMPDIFF(SECOND, '" + startDateText + " " + startTime   + "', '" + endDateText + " " +  endTime + "'), Title = '" + title + "', Description_of_task = '" + description + "' WHERE Task_ID = " + taskID;
        try
        {
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            stage.close();
        }
        catch (Exception e)
        {
            //warning window
            new UpdateTaskWarningScreen().start(new Stage());
            System.out.println(e);
        }


    }

}

