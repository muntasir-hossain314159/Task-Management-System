package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Task extends Application {

    private int ID;

    public Task(int ID) {
        this.ID = ID;
    }

    public void start(Stage primaryStage)
    {
        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return button pushed");
                UserMenu userMenu = new UserMenu();
                userMenu.start(primaryStage);
            }
        });


        Text text0 = new Text("Task");

        TextField title = new TextField();
        Text titleName = new Text("Title");


        TextField startTime = new TextField();
        Text startTimeName = new Text("Start Time");

        TextField endTime = new TextField();
        Text endTimeName = new Text("End Time");

        TextField description = new TextField();
        Text descriptionName = new Text("Description");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Add task button pushed");
                String titleText = title.getText();
                String startTimeText = startTime.getText();
                String endTimeText = endTime.getText();
                String descriptionText = description.getText();
                addTask(ID, titleText, startTimeText, endTimeText, descriptionText);
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
        gridPane.add(text0, 0, 0, 2, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(titleName, 0, 2, 1, 1);
        gridPane.add(title, 1, 2, 1, 1);

        gridPane.add(startTimeName, 0, 3, 1, 1);
        gridPane.add(startTime, 1, 3, 1, 1);

        gridPane.add(endTimeName, 0, 4, 1, 1);
        gridPane.add(endTime, 1, 4, 1, 1);

        gridPane.add(descriptionName, 0, 5, 1, 1);
        gridPane.add(description, 1, 5, 1, 1);

        menu.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(menu, 0, 6, 1, 1);

        addButton.setMaxHeight(Double.MAX_VALUE);
        gridPane.add(addButton, 1, 6, 1, 1);
        GridPane.setHalignment(addButton, HPos.CENTER);

        //GridPane.setHalignment(popupContent, HPos.CENTER);

        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        primaryStage.setTitle("Task");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    private void addTask(int ID, String title, String startTime, String endTime, String description)
    {
        String sql = "INSERT INTO task(Task_ID, User_ID, Start_date_time, End_date_time, Title, Description_of_task)"
                + " VALUES (0, " + ID + ", '" + startTime + "', '" + endTime + "', '" + description + "');";
        
        try
        {
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }



    }


    
}
