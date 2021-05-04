package sample;

import com.sun.javafx.collections.NonIterableChange;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;

//Search Tasks Screen
public class SearchTasks extends Application {

    private int userID;

    //Constructor
    public SearchTasks(int userID)
    {
        this.userID = userID;
    }

    public void start(Stage stage)
    {
        //Header
        Text text0 = new Text("Search for Tasks");

        //Title
        Text titleName = new Text("Title");
        TextField title = new TextField();

        //Start Date Calendar
        DatePicker startDate = new DatePicker();
        startDate.setValue(null);
        startDate.setEditable(true);
        startDate.setShowWeekNumbers(true);

        //Start Time
        Text startTimeName = new Text("Start Date & Time");
        TextField startTime = new TextField();

        //End Date Calendar
        DatePicker endDate = new DatePicker();
        endDate.setValue(null);
        endDate.setEditable(true);
        endDate.setShowWeekNumbers(true);

        //End Time
        Text endTimeName = new Text("End Date & Time");
        TextField endTime = new TextField();

        //Description
        Text descriptionName = new Text("Description");
        TextField description = new TextField();

        //Duration
        Text durationName = new Text("Duration");
        TextField duration = new TextField();

        //Menu button
        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Menu button pushed");
                UserMenu userMenu = new UserMenu(userID);
                userMenu.start(stage);
            }
        });

        //Help button
        Button help = new Button("Help");
        help.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Help button pushed");
                new SearchFunctionInformation().start(new Stage());
            }
        });

        //Search button
        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                System.out.println("Search button pushed");

                //Title
                String titleText = title.getText();

                //Start Date
                LocalDate startDateTime = startDate.getValue();
                String startDateText = "";
                if (startDateTime != null)
                    startDateText = startDateTime.toString();

                //Start Time
                String startTimeText = startTime.getText();

                //End Date
                LocalDate endDateTime = endDate.getValue();
                String endDateText = "";
                if (endDateTime != null)
                    endDateText = endDateTime.toString();

                //End Time
                String endTimeText = endTime.getText();

                //Duration
                String durationText = duration.getText();

                //Description
                String descriptionText = description.getText();

                searchForTask(stage, userID, startDateText, startTimeText, endDateText, endTimeText, durationText, titleText, descriptionText);

                //Set fields to initial state
                title.setText("");
                startDate.setValue(null);
                startTime.setText("");
                endDate.setValue(null);
                endTime.setText("");
                duration.setText("");
                description.setText("");
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

        gridPane.add(durationName, 0, 5, 1, 1);
        gridPane.add(duration, 1, 5, 1, 1);

        gridPane.add(descriptionName, 0, 6, 1, 1);
        gridPane.add(description, 1, 6, 1, 1);

        gridPane.add(menu, 0, 7, 1, 1);
        GridPane.setHalignment(menu, HPos.CENTER);

        search.setMaxHeight(Double.MAX_VALUE);
        gridPane.add(search, 1, 7, 1, 1);
        GridPane.setHalignment(search, HPos.CENTER);

        gridPane.add(help, 2, 7, 1, 1);
        GridPane.setHalignment(help, HPos.CENTER);

        //Styling Nodes
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Search for Tasks");

        //Adding scene to the stage and centering stage
        stage.setScene(scene);
        stage.centerOnScreen();

        //Displaying the contents of the stage
        stage.show();
    }

    //Search for a task based on the user inputted values
    private void searchForTask(Stage stage, int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description)
    {
        SearchTasksTableViewScreen searchTasksTableViewScreen = new SearchTasksTableViewScreen(userID, startDate, startTime, endDate, endTime, duration, title, description);
        searchTasksTableViewScreen.start(stage);
    }

}
