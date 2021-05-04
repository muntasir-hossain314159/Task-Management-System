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

//Admin Menu Screen
public class AdminMenu extends Application{

    private int ID;

    //Constructor
    public AdminMenu(int ID) {
        this.ID = ID;
    }

    public void start(Stage stage) {
        //Text
        Text text0 = new Text("Admin Menu");

        //New User List button
        Button newUserList = new Button("New User List");
        newUserList.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("New User List button pushed");
                NewUserTableViewScreen newUserTableViewScreen = new NewUserTableViewScreen(ID);
                newUserTableViewScreen.start(stage);
            }
        });

        //Approved User List button
        Button approvedUserList = new Button("Approved User List");
        approvedUserList.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Approved User List button pushed");
                ApprovedUserTableViewScreen approvedUserTableViewScreen = new ApprovedUserTableViewScreen(ID);
                approvedUserTableViewScreen.start(stage);
            }
        });

        //Create New Admin Account button
        Button createNewAdminAccount = new Button("Create New Admin Account");
        createNewAdminAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Create New Admin Account button pushed");
                CreateNewAdminAccount createNewAdminAccount = new CreateNewAdminAccount(ID);
                createNewAdminAccount.start(stage);
            }
        });

        //Update Admin Account button
        Button updateAdminLoginInformation = new Button("Update Admin Login Information");
        updateAdminLoginInformation.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                UpdateAdminAccount updateAdminAccount = new UpdateAdminAccount(ID);
                updateAdminAccount.start(stage);
                System.out.println("Update Admin Login Information button pushed");
            }
        });

        //Log Out button
        Button logOut = new Button("Log Out");
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Log Out button pushed");
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.start(stage);
            }
        });

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();

        gridPane.setMinSize(400, 400);


        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        //Setting all buttons to max width
        newUserList.setMaxWidth(Double.MAX_VALUE);
        approvedUserList.setMaxWidth(Double.MAX_VALUE);
        createNewAdminAccount.setMaxWidth(Double.MAX_VALUE);
        updateAdminLoginInformation.setMaxWidth(Double.MAX_VALUE);
        logOut.setMaxWidth(Double.MAX_VALUE);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text0, 0, 0, 1, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(newUserList, 0, 1, 1, 1);

        gridPane.add(approvedUserList, 0, 2, 1, 1);

        gridPane.add(createNewAdminAccount, 0, 3, 1, 1);

        gridPane.add(updateAdminLoginInformation, 0, 4, 1, 1);

        gridPane.add(logOut, 0, 5, 1, 1);


        //Styling nodes
        approvedUserList.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        updateAdminLoginInformation.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        newUserList.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        createNewAdminAccount.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        logOut.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Admin Menu");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
}
