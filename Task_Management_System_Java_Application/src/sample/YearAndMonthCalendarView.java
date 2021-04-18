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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class YearAndMonthCalendarView extends Application {

    private int userID;

    public YearAndMonthCalendarView(int userID) {
        this.userID = userID;
    }

    public void start(Stage stage)
    {
        //todo button that will take the user to the current date - call it return to current date

        Text text0 = new Text("Month & Year");

        Text text1 = new Text("Year: ");

        TextField year = new TextField();


        DayAndMonthCalendarView dayAndMonthCalendarView = new DayAndMonthCalendarView(userID);

        //Creating Buttons
        Button january = new Button("January");
        january.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("jan button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 1);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        });

        Button february = new Button("February");
        february.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 2);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button march = new Button("March");
        march.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 3);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button april = new Button("April");
        april.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 4);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        });

        Button may = new Button("May");
        may.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 5);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        });

        Button june = new Button("June");
        june.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 6);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button july = new Button("July");
        july.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 7);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        });

        Button august = new Button("August");
        august.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 8);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button september = new Button("September");
        september.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 9);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button october = new Button("October");
        october.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 10);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button november = new Button("November");
        november.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 11);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        });

        Button december = new Button("December");
        december.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                try
                {
                    String yearInput = year.getText();
                    int yearValue = Integer.parseInt(yearInput);
                    dayAndMonthCalendarView.start(stage, yearValue, 12);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
        });

        Button menu = new Button("Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return button pushed");
                UserMenu userMenu = new UserMenu(userID);
                userMenu.start(stage);
            }
        });

        Button currentDate = new Button("Current Date");
        currentDate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Current Date button pushed");

                DayAndMonthCalendarView dayAndMonthCalendarView = new DayAndMonthCalendarView(userID);
                dayAndMonthCalendarView.start(stage);
            }
        });

        GridPane gridPane = new GridPane();
        //GridPane gp=new GridPane();
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
        gridPane.add(text0, 1, 0, 2, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(text1, 0, 1, 1, 1);
        GridPane.setHalignment(text1, HPos.CENTER);
        gridPane.add(year, 1, 1, 3, 1);
        //GridPane.setHgrow(year, Priority.ALWAYS);

        menu.setMaxWidth(Double.MAX_VALUE);
        currentDate.setMaxWidth(Double.MAX_VALUE);

        january.setMaxWidth(Double.MAX_VALUE);
        february.setMaxWidth(Double.MAX_VALUE);
        march.setMaxWidth(Double.MAX_VALUE);
        april.setMaxWidth(Double.MAX_VALUE);
        may.setMaxWidth(Double.MAX_VALUE);
        june.setMaxWidth(Double.MAX_VALUE);
        july.setMaxWidth(Double.MAX_VALUE);
        august.setMaxWidth(Double.MAX_VALUE);
        september.setMaxWidth(Double.MAX_VALUE);
        october.setMaxWidth(Double.MAX_VALUE);
        november.setMaxWidth(Double.MAX_VALUE);
        december.setMaxWidth(Double.MAX_VALUE);

        gridPane.add(january, 0, 2);
        gridPane.add(february, 1, 2);
        gridPane.add(march, 2, 2);
        gridPane.add(april, 3, 2);

        gridPane.add(may, 0, 3);
        gridPane.add(june, 1, 3);
        gridPane.add(july, 2, 3);
        gridPane.add(august, 3, 3);

        gridPane.add(september, 0, 4);
        gridPane.add(october, 1, 4);
        gridPane.add(november, 2, 4);
        gridPane.add(december, 3, 4);

        gridPane.add(menu, 3, 5);
        gridPane.add(currentDate, 0, 5);

        GridPane.setHgrow(january, Priority.ALWAYS);
        GridPane.setHgrow(february, Priority.ALWAYS);
        GridPane.setHgrow(march, Priority.ALWAYS);
        GridPane.setHgrow(april, Priority.ALWAYS);
        GridPane.setHgrow(may, Priority.ALWAYS);
        GridPane.setHgrow(june, Priority.ALWAYS);
        GridPane.setHgrow(july, Priority.ALWAYS);
        GridPane.setHgrow(august, Priority.ALWAYS);
        GridPane.setHgrow(september, Priority.ALWAYS);
        GridPane.setHgrow(october, Priority.ALWAYS);
        GridPane.setHgrow(november, Priority.ALWAYS);
        GridPane.setHgrow(december, Priority.ALWAYS);

        GridPane.setHgrow(currentDate, Priority.ALWAYS);
        GridPane.setHgrow(menu, Priority.ALWAYS);

        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        menu.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        currentDate.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

/*
        //Styling nodes
        calendar.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        task.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        logOut.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");


*/
        gridPane.setStyle("-fx-background-color: BEIGE;");
        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Calendar and Month View");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
}

