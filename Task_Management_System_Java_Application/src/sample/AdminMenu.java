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

public class AdminMenu extends Application{

    private int ID;

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public void start(Stage stage) {
        Text text0 = new Text("Admin Menu");

        //Creating Buttons
        Button adminLoginInformation = new Button("Update Admin Login Information");
        adminLoginInformation.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                UpdateAdminAccount updateAdminAccount = new UpdateAdminAccount(ID);
                updateAdminAccount.start(stage);
                System.out.println("Admin Login button pushed");
            }
        });

        Button newUserList = new Button("New User List");
        newUserList.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("New User List button pushed");
                TableViewScreen tableViewScreen = new TableViewScreen(ID);
                tableViewScreen.start(stage);
            }
        });

        Button newAdminAccount = new Button("Create New Admin Account");
        newAdminAccount.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Create New Admin Account button pushed");
               CreateNewAdminAccount createNewAdminAccount = new CreateNewAdminAccount();
               createNewAdminAccount.start(stage);
            }
        });

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

        gridPane.add(newUserList, 0, 1, 1, 1);
        GridPane.setHalignment(newUserList, HPos.CENTER);

        gridPane.add(newAdminAccount, 0, 2, 1, 1);
        GridPane.setHalignment(newAdminAccount, HPos.CENTER);

        gridPane.add(adminLoginInformation, 0, 3, 1, 1);
        GridPane.setHalignment(adminLoginInformation, HPos.CENTER);

        gridPane.add(logOut, 0, 4, 1, 1);
        GridPane.setHalignment(logOut, HPos.CENTER);


        //Styling nodes
        adminLoginInformation.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        newUserList.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        newAdminAccount.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
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
