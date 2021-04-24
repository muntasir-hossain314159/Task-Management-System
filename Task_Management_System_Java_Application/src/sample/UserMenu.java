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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

import static java.util.Calendar.DATE;

public class UserMenu extends Application{

    private int ID;

    public UserMenu(int ID) {
        this.ID = ID;
    }

    public void start(Stage stage) {
        Text text0 = new Text("Menu");

        //Creating Buttons
        Button calendar = new Button("Calendar");
        calendar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("calendar button pushed");
                DayAndMonthCalendarView dayAndMonthCalendarView = new DayAndMonthCalendarView(ID);
                dayAndMonthCalendarView.start(stage);
            }
        });

        Button tasksForWeek  = new Button("Tasks for this Week");
        tasksForWeek.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Tasks for this week button pushed");
                new SearchTasksTableViewScreen(ID, LocalDate.now().toString(), LocalDate.now().plusDays(6).toString(), true).start(stage);
                System.out.println(LocalDate.now().toString());
                System.out.println(LocalDate.now().plusDays(6).toString());
            }
        });


        Button searchForTasks = new Button("Search for Tasks");
        searchForTasks.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Search for Tasks button pushed");
                SearchTasks searchTasks = new SearchTasks(ID);
                searchTasks.start(stage);
            }
        });


        Button createNewTask = new Button("Create New Task");
        createNewTask.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Task button pushed");
                CreateNewTask createNewTask = new CreateNewTask(ID);
                createNewTask.start(stage);
            }
        });

        Button deleteUserAccount = new Button("Delete User Account");
        deleteUserAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Delete user account button pushed");
                new DeleteUserAccount(ID).start(stage);
            }
        });

        Button logOut = new Button("Log Out");
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Log Out button pushed");
                UserLogin userLogin = new UserLogin();
                userLogin.start(stage);
            }
        });

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Exit button pushed");
                Exit exit = new Exit(ID);
                exit.start(stage);
            }
        });

        calendar.setMaxWidth(Double.MAX_VALUE);
        createNewTask.setMaxWidth(Double.MAX_VALUE);
        searchForTasks.setMaxWidth(Double.MAX_VALUE);
        tasksForWeek.setMaxWidth(Double.MAX_VALUE);
        logOut.setMaxWidth(Double.MAX_VALUE);
        deleteUserAccount.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);

        //Creating a Grid Pane
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
        gridPane.add(text0, 0, 0, 1, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(calendar, 0, 1, 1, 1);
        gridPane.add(tasksForWeek, 0, 2, 1, 1);
        gridPane.add(createNewTask, 0, 3, 1, 1);
        gridPane.add(searchForTasks, 0, 4, 1, 1);
        gridPane.add(deleteUserAccount, 0, 5, 1, 1);
        gridPane.add(logOut, 0, 6, 1, 1);
        gridPane.add(exit, 0, 7, 1, 1);



        //Styling nodes
        calendar.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        tasksForWeek.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        createNewTask.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        searchForTasks.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        deleteUserAccount.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        exit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        logOut.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("User Menu");

        //Adding scene to the stage
        stage.setScene(scene);
        stage.centerOnScreen();

        //Displaying the contents of the stage
        stage.show();
    }
}
