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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchTasks extends Application {

    private int userID;

    public SearchTasks(int userID)
    {
        this.userID = userID;
    }

    public void start(Stage stage)
    {
        Text text0 = new Text("Search for Tasks");

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

        TextField duration = new TextField();
        Text durationName = new Text("Duration");

        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return button pushed");
                UserMenu userMenu = new UserMenu(userID);
                userMenu.start(stage);
            }
        });

        Button search = new Button("Search");
        search.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("search button pushed");

                String titleText = title.getText();

                LocalDate startDateTime = startDate.getValue();
                String startDateText = "";
                if (startDateTime != null)
                    startDateText = startDateTime.toString();

                String startTimeText = startTime.getText();

                LocalDate endDateTime = endDate.getValue();
                String endDateText = "";
                if (endDateTime != null)
                    endDateText = endDateTime.toString();

                String endTimeText = endTime.getText();

                String durationText = duration.getText();

                String descriptionText = description.getText();

                //todo how do I take the duration from user?? do i take hours or mins or seconds
                searchForTask(stage, userID, startDateText, startTimeText, endDateText, endTimeText, durationText, titleText, descriptionText);

                title.setText("");
                startDate.setValue(null);
                startTime.setText("");
                endDate.setValue(null);
                endTime.setText("");
                duration.setText("");
                description.setText("");
                System.out.println("Search done successfully");
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

        gridPane.add(durationName, 0, 5, 1, 1);
        gridPane.add(duration, 1, 5, 1, 1);

        gridPane.add(descriptionName, 0, 6, 1, 1);
        gridPane.add(description, 1, 6, 1, 1);

        //menu.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        gridPane.add(menu, 0, 7, 1, 1);
        GridPane.setHalignment(menu, HPos.CENTER);

        search.setMaxHeight(Double.MAX_VALUE);
        gridPane.add(search, 0, 7, 3, 1);
        GridPane.setHalignment(search, HPos.CENTER);

        //GridPane.setHalignment(popupContent, HPos.CENTER);

        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Search for Tasks");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    private void searchForTask(Stage stage, int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description)
    {
        SearchTasksTableViewScreen searchTasksTableViewScreen = new SearchTasksTableViewScreen(userID, startDate, startTime, endDate, endTime, duration, title, description);
        searchTasksTableViewScreen.start(stage);
    }

}
