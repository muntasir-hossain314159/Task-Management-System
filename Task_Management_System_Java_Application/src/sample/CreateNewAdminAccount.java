package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Create New Admin Account Screen
public class CreateNewAdminAccount extends Application {

    private int adminID;

    //Constructor
    public CreateNewAdminAccount(int adminID) {
        this.adminID = adminID;
    }

    public void start(Stage stage)
    {
        //Texts
        Text text0= new Text ("Create New Admin Account");
        Text text1 = new Text("New Admin Username");
        Text text2 = new Text("New Admin Password");

        //Text Fields for user input
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event)
            {
                System.out.println("Submit button pushed");
                String Admin_username = textField1.getText();
                String Admin_password = textField2.getText();

                //Password Length Warning Screen is displayed if the password is less than 8 characters long
                if(Admin_password.length() < 8)
                {
                    new PasswordLengthWarningScreen().start(new Stage());
                }

                //Password is at least 8 characters long
                else
                {
                    try
                    {
                        Connection connection = SetDatabaseConnection.getConnection();

                        //Execute SQL query to determine current number of admins in the admin_account table
                        String sql1 = "SELECT COUNT(Admin_ID) AS Admin_Count FROM admin_account";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                        ResultSet resultSet = preparedStatement1.executeQuery();

                        //Set adminCount to the value from the query and increment adminCount
                        //The incremented value of the adminCount is the admin ID of the new admin
                        resultSet.next();
                        int adminCount = resultSet.getInt("Admin_Count");
                        System.out.println(adminCount);
                        adminCount++;

                        //Insert into admin_account the admin ID, username and password for the new admin
                        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admin_account VALUES (" + adminCount + ", '" + Admin_username + "', '" + Admin_password + "')");
                        preparedStatement.executeUpdate();
                    }
                    catch (Exception e)
                    {
                        //Username of the admin has to be unique, an error is thrown if the username exists
                        new AccountExistsWarningScreen().start(new Stage());
                        System.out.println(e);;
                    }

                    System.out.print("New Admin's Email: " + Admin_username + "\n");
                    System.out.print("New Admin's Password: " + Admin_password + "\n");
                }

                textField1.setText("");
                textField2.setText("");
            }  });

        //Clear button
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Clear button pushed");
                textField1.setText("");
                textField2.setText("");
            }  });

        //Return to Menu button
        Button menu = new Button("Return to Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return to menu button pushed");
                AdminMenu adminMenu = new AdminMenu(adminID);
                adminMenu.start(stage);
            }  });

        //Log Out button
        Button logOut= new Button("Log Out");
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Log Out button pushed");
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.start(stage);
            }  });

        //GridPane set up
        GridPane gridPane1 = new GridPane();
        gridPane1.setMinSize(400, 400);
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setAlignment(Pos.CENTER);

        //Add Nodes to GridPane and set alignment
        gridPane1.add(text0,0,0,2,1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane1.add(text1, 0, 1);
        gridPane1.add(textField1, 1, 1);

        gridPane1.add(text2, 0, 2);
        gridPane1.add(textField2, 1, 2);

        gridPane1.add(submit, 0, 3,2,1);
        GridPane.setHalignment(submit, HPos.CENTER);

        gridPane1.add(clear, 0, 4,2,1);
        GridPane.setHalignment(clear, HPos.LEFT);

        gridPane1.add(menu, 0, 4,2,1);
        GridPane.setHalignment(menu, HPos.CENTER);

        gridPane1.add(logOut,0, 4,2,1);
        GridPane.setHalignment(logOut, HPos.RIGHT);

        //GridPane and Nodes style
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");

        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        menu.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        logOut.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        gridPane1.setStyle("-fx-background-color: BEIGE;");

        //Set Scene and Stage
        Scene scene1 = new Scene(gridPane1);
        stage.setTitle("Create New Admin Account");
        stage.setScene(scene1);
        stage.show();
    }
}
