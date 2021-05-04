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

//EditTask class is used to edit a pre-existing task
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

    //Constructor
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
        //Convert startTime (TimeStamp) to startDateText and startTimeText (String)
        startDateText = startTime.toString().split(" ")[0];
        System.out.println("Start Date: " + startDateText);
        startTimeText = startTime.toString().split(" ")[1];
        System.out.println("Start Time: " + startTimeText);

        //Convert endTime (TimeStamp) to endDateText and endTimeText (String)
        endDateText = endTime.toString().split(" ")[0];
        System.out.println("End date: " + endDateText);
        endTimeText = endTime.toString().split(" ")[1];
        System.out.println("End Time: " + endTimeText);

        //Header
        Text text0 = new Text("Edit Task");

        //Title
        Text titleName = new Text("Title");
        TextField title = new TextField();

        //Start Date DatePicker
        DatePicker startDate = new DatePicker();
        startDate.setValue(null);
        startDate.setEditable(true);
        startDate.setShowWeekNumbers(true);

        //Start Time
        Text startTimeName = new Text("Start Time");
        TextField startTime = new TextField();

        //End Date DatePicker
        DatePicker endDate = new DatePicker();
        endDate.setValue(null);
        endDate.setEditable(true);
        endDate.setShowWeekNumbers(true);

        //End Time
        Text endTimeName = new Text("End Time");
        TextField endTime = new TextField();

        //Description
        Text descriptionName = new Text("Description");
        TextField description = new TextField();

        //Cancel button
        Button cancel = new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Cancel button pushed");
                primaryStage.close();
            }
        });

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");

                //Title
                if(!title.getText().isEmpty())
                    titleText = title.getText();

                //Start Date
                LocalDate startDateTime = startDate.getValue();
                if (startDateTime != null)
                    startDateText = startDateTime.toString();

                //Start Time
                if(!startTime.getText().isEmpty())
                    startTimeText = startTime.getText();

                //End Date
                LocalDate endDateTime = endDate.getValue();
                if (endDateTime != null)
                    endDateText = endDateTime.toString();

                //End Time
                if(!endTime.getText().isEmpty())
                    endTimeText = endTime.getText();

                //Description
                if(!description.getText().isEmpty())
                    descriptionText = description.getText();

                editTask(primaryStage, taskID, userID, startDateText, startTimeText, endDateText, endTimeText, titleText, descriptionText);

                //Set fields to initial value
                title.setText("");
                startDate.setValue(null);
                startTime.setText("");
                endDate.setValue(null);
                endTime.setText("");
                description.setText("");
            }
        });

        //Creating GridPane
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(400, 400);

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

        gridPane.add(cancel, 0, 6, 1, 1);
        GridPane.setHalignment(cancel, HPos.CENTER);

        submit.setMaxHeight(Double.MAX_VALUE);
        gridPane.add(submit, 0, 6, 3, 1);
        GridPane.setHalignment(submit, HPos.CENTER);

        //Styling Nodes
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

    //editTask updates task table with new user inputted values for a task
    private void editTask(Stage stage, int taskID, int userID, String startDateText, String startTime, String endDateText, String endTime, String title, String description)
    {

        String sql = "UPDATE task "
                   + "SET Start_date_time = '" + startDateText + " " + startTime + "', End_date_time = '"  + endDateText + " " +  endTime + "', Duration = TIMESTAMPDIFF(MINUTE, '" + startDateText + " " + startTime   + "', '" + endDateText + " " +  endTime + "'), Title = '" + title + "', Description_of_task = '" + description + "' WHERE Task_ID = " + taskID;
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

