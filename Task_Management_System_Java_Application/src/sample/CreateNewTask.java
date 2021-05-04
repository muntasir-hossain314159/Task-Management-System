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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

//Create New Task Screen
public class CreateNewTask extends Application {

    private int ID;

    //Constructor
    public CreateNewTask(int ID) {
        this.ID = ID;
    }

    public void start(Stage primaryStage)
    {
        //Texts and Text Fields
        Text createNewTask = new Text("Create New Task");

        Text titleName = new Text("Title");
        TextField title = new TextField();

        Text startTimeName = new Text("Start Date & Time");
        TextField startTime = new TextField();

        //Start Date Calendar
        DatePicker startDate = new DatePicker(LocalDate.now());
        startDate.setEditable(true);
        startDate.setShowWeekNumbers(true);

        Text endTimeName = new Text("End Date & Time");
        TextField endTime = new TextField();

        //End Date Calendar
        DatePicker endDate = new DatePicker(LocalDate.now());
        endDate.setEditable(true);
        endDate.setShowWeekNumbers(true);

        Text descriptionName = new Text("Description");
        TextField description = new TextField();

        //Add Task button
        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Add task button pushed");
                String titleText = title.getText();

                LocalDate startDateTime = startDate.getValue();
                String startDateText = "";
                if(startDateTime != null)
                    startDateText= startDateTime.toString();

                String startTimeText = startTime.getText();
                System.out.println(startDateText);

                LocalDate endDateTime = endDate.getValue();
                String endDateText = "";
                if(endDateTime != null)
                    endDateText = endDateTime.toString();

                String endTimeText = endTime.getText();
                System.out.println(endDateText);

                String descriptionText = description.getText();

                boolean check = checkForConflict(ID, startDateText, startTimeText, endDateText, endTimeText);
                //if check == true, add the user inputted values into the task table
                if(check)
                    addTask(ID, titleText, startDateText, startTimeText, endDateText, endTimeText, descriptionText);

                //Set fields to their initial state
                title.setText("");
                startDate.setValue(LocalDate.now());
                startTime.setText("");
                endDate.setValue(LocalDate.now());
                endTime.setText("");
                description.setText("");
            }
        });

        //Menu button
        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Menu button pushed");
                UserMenu userMenu = new UserMenu(ID);
                userMenu.start(primaryStage);
            }
        });

        //Help button
        Button help = new Button("Help");
        help.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Help button pushed");
                new CreateTaskInformation().start(new Stage());
            }
        });

        //GridPane set up
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
        gridPane.add(createNewTask, 0, 0, 3, 1);
        GridPane.setHalignment(createNewTask, HPos.CENTER);

        gridPane.add(titleName, 0, 2, 1, 1);
        gridPane.add(title, 1, 2, 1, 1);

        gridPane.add(startTimeName, 0, 3, 1, 1);
        gridPane.add(startDate, 1, 3, 1, 1);
        gridPane.add(startTime, 2, 3, 1, 1);

        gridPane.add(endTimeName, 0, 4, 1, 1);
        gridPane.add(endDate, 1, 4, 1,1);
        gridPane.add(endTime, 2, 4, 1, 1);

        gridPane.add(descriptionName, 0, 5, 1, 1);
        gridPane.add(description, 1, 5, 1, 1);

        gridPane.add(menu, 0, 6, 1, 1);
        GridPane.setHalignment(menu, HPos.CENTER);

        addTaskButton.setMaxHeight(Double.MAX_VALUE);
        gridPane.add(addTaskButton, 1, 6, 1, 1);
        GridPane.setHalignment(addTaskButton, HPos.CENTER);

        gridPane.add(help, 2, 6, 1, 1);
        GridPane.setHalignment(help, HPos.CENTER);

        //Set GridPane and Node style
        createNewTask.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        primaryStage.setTitle("Create New Task");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    private boolean checkForConflict(int ID, String startDateText, String startTime, String endDateText, String endTime)
    {
        //If no conflict exists, check = true
        boolean check = true;

        //Execute SQL query to determine if the start time or end time of the current task is between the start time and end time of another task
        String sql1 = "SELECT * FROM task WHERE Start_date_time <= '" + startDateText + startTime + "' AND End_date_time >= '" + startDateText + startTime + "' AND User_ID = " + ID;
        String sql2 = "SELECT * FROM task WHERE Start_date_time <= '" + endDateText + endTime + "' AND End_date_time >= '" + endDateText + endTime + "' AND User_ID = " + ID;

        try {
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            ResultSet resultSet2 = preparedStatement1.executeQuery();

            //If there exists such a task from the SQL query, then there is a conflict between tasks
            if(resultSet1.next() || resultSet2.next())
            {
                //Display Conflict Error screen and wait for user input
                ConflictErrorScreen conflictErrorScreen = new ConflictErrorScreen();
                conflictErrorScreen.start(new Stage());
                check = conflictErrorScreen.setCheck();
            }

        }
        catch (Exception e)
        {
            System.out.println("Error");
        }

        return check;
    }

    //Adds user inputted values into Task table
    private void addTask(int ID, String title, String startDateText, String startTime, String endDateText, String endTime, String description)
    {
        String sql = "INSERT INTO task (Task_ID, User_ID, Start_date_time, End_date_time, Duration, Title, Description_of_task)"
                   + " VALUES (0, " + ID + ", '" + startDateText + " " + startTime + "', '" + endDateText + " " +  endTime + "', TIMESTAMPDIFF(MINUTE, '" + startDateText + " " + startTime   + "', '" + endDateText + " " +  endTime + "'), '" + title + "', '"+ description + "');";

        try
        {
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            //Display Task Added Successfully screen if there is no error
            new WindowTaskScreen().start(new Stage(), true);


        }
        catch (Exception e)
        {
            System.out.println(e);

            //Display Task Not Added Successfully Screen if there is an error
            new WindowTaskScreen().start(new Stage(), false);
        }
    }
}


    

