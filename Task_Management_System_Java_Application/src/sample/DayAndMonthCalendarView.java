package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.time.LocalDate;

//Displays the current Calendar and a Calendar for user inputted values for month and year
public class DayAndMonthCalendarView extends Application {

    private int userID;

    //Constructor
    public DayAndMonthCalendarView(int userID) {
        this.userID = userID;
    }

    public void start(Stage primaryStage)
    {
        //Text
        Text text0 = new Text("Day & Month");

        //DatePicker to display current calendar
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setShowWeekNumbers(true);
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node popupContent = datePickerSkin.getPopupContent();

        //Search for Month & Year button
        Button yearAndMonth = new Button("Search for Month & Year");
        yearAndMonth.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Search for Month & Year button pushed");

                YearAndMonthCalendarView yearAndMonthCalendarView = new YearAndMonthCalendarView(userID);
                yearAndMonthCalendarView.start(primaryStage);
            }
        });

        //Menu button
        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Menu button pushed");
                UserMenu userMenu = new UserMenu(userID);
                userMenu.start(primaryStage);
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
        gridPane.add(text0, 0, 0, 1, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(popupContent, 0, 2, 1, 1);
        GridPane.setHalignment(popupContent, HPos.CENTER);

        gridPane.add(yearAndMonth, 0, 3, 1, 1);
        GridPane.setHalignment(yearAndMonth, HPos.LEFT);

        gridPane.add(menu, 0, 3 , 1, 1);
        GridPane.setHalignment(menu, HPos.RIGHT);

        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        primaryStage.setTitle("Day and Month Calendar View");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }

    public void start(Stage primaryStage, int year, int month)
    {
        //Text
        Text text0 = new Text("Day & Month");

        //DatePicker to display calendar of user inputted values for month and year
        DatePicker datePicker = new DatePicker(LocalDate.of(year,month,1));
        datePicker.setShowWeekNumbers(true);
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node popupContent = datePickerSkin.getPopupContent();

        Button returnToYearAndMonth = new Button("Return");
        returnToYearAndMonth.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return button pushed");

                YearAndMonthCalendarView yearAndMonthCalendarView = new YearAndMonthCalendarView(userID);
                yearAndMonthCalendarView.start(primaryStage);
            }
        });

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
        gridPane.add(text0, 0, 0, 1, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(popupContent, 0, 2, 1, 1);
        GridPane.setHalignment(popupContent, HPos.CENTER);

        gridPane.add(returnToYearAndMonth, 0, 3, 1, 1);
        GridPane.setHalignment(returnToYearAndMonth, HPos.LEFT);

        //Set GridPane and Node style
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        primaryStage.setTitle("Day and Month Calendar View");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }
}
