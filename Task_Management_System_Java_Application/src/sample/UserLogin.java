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
import java.sql.ResultSet;
import java.sql.Statement;

//User Login Screen
public class UserLogin extends Application
{

    public void start(Stage stage)
    {
        //Texts
        Text welcomeToTaskManagementSystem = new Text("Welcome to Task Management System");
        Text username = new Text("Username");
        Text password = new Text("Password");

        //TextField and PasswordField
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");
                String username = textField1.getText();
                String password = textField2.getText();

                try
                {
                    Connection connection = SetDatabaseConnection.getConnection();

                    //Execute SQL query to determine if Username and Password matches exactly with the user input
                    String sql = "SELECT Approved_user_ID AS ID FROM approved_user_account WHERE Approved_username LIKE BINARY'" + username + "' AND Approved_user_password LIKE BINARY '" + password + "';";
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet resultSet = statement.executeQuery(sql);

                    //Count the number of rows that the SQL query outputs
                    int count = 0;
                    while(resultSet.next())
                    {
                        count++;
                    }
                    resultSet.beforeFirst(); //set the pointer for resultSet before the first row

                    //If count == 1, then a single user exists with that username and password (user can log in)
                    if(count == 1)
                    {
                        resultSet.next();
                        int ID = resultSet.getInt("ID");
                        UserMenu userMenu = new UserMenu(ID);
                        userMenu.start(stage);
                    }
                    //If count > 1, user is not able to log in
                    else if(count > 1)
                    {
                        System.out.println("System Error"); //There is a system error because the application is built as such that the username has to be unique
                    }
                    //Else (count == 0), user has inputted wrong username or password
                    else
                    {
                        System.out.println("Error, Wrong Password");
                        Stage stage1 = new Stage();
                        UsernamePasswordWarningScreen usernamePasswordWarningScreen = new UsernamePasswordWarningScreen();
                        usernamePasswordWarningScreen.start(stage1);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }

                //Print Username and Password
                System.out.print("Username:" + username + "\n");
                System.out.print("Password:" + password + "\n");

                //Make TextField and PasswordField empty
                textField1.setText("");
                textField2.setText("");
            }
        });

        //Clear button
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Clear button pushed");
                textField1.setText("");
                textField2.setText("");
            }
        });

        //Create New User button
        Button createUser = new Button("Create New User");
        createUser.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Create New User button pushed");
                CreateNewUserAccount createNewUserAccount = new CreateNewUserAccount();
                createNewUserAccount.start(stage);
            }
        });

        //Admin button
        Button admin = new Button("Admin");
        admin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("admin button pushed");
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.start(stage);
            }
        });

        //Creating a Grid Pane
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
        gridPane.add(welcomeToTaskManagementSystem, 0, 0, 3, 1);
        GridPane.setHalignment(welcomeToTaskManagementSystem, HPos.CENTER);

        gridPane.add(username, 0, 1);
        gridPane.add(textField1, 1, 1);

        gridPane.add(password, 0, 2);
        gridPane.add(textField2, 1, 2);

        gridPane.add(submit, 0, 3, 3, 1);
        GridPane.setHalignment(submit, HPos.CENTER);

        gridPane.add(clear, 0, 4, 3, 1);
        GridPane.setHalignment(clear, HPos.LEFT);

        gridPane.add(createUser, 0, 4, 3, 1);
        GridPane.setHalignment(createUser, HPos.CENTER);

        gridPane.add(admin, 0, 4, 3, 1);
        GridPane.setHalignment(admin, HPos.RIGHT);


        //Styling nodes
        welcomeToTaskManagementSystem.setStyle("-fx-font: normal bold 20px 'serif' ");
        username.setStyle("-fx-font: normal bold 20px 'serif' ");
        password.setStyle("-fx-font: normal bold 20px 'serif' ");

        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        createUser.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        admin.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("User Login");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    //main() function
    public static void main(String args[]){
        launch(args);
    }

}
