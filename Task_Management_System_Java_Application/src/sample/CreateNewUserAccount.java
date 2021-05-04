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
import java.util.Random;

//Create New User Account Screen
public class CreateNewUserAccount extends Application {

    public void start(Stage stage)
    {
        //Texts
        Text newUserSignUp= new Text ("New User SignUp");
        Text newUsername = new Text("New Username");
        Text newUserPassword = new Text("New User Password");

        //TextField and Password for user input
        TextField textField0 = new TextField();
        PasswordField textField1 = new PasswordField();

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");
                String Username = textField0.getText();
                String User_password = textField1.getText();

                //Password Length Warning Screen is displayed if the password is less than 8 characters long
                if(User_password.length() < 8)
                {
                    new PasswordLengthWarningScreen().start(new Stage());
                }
                else
                {
                    try
                    {
                        Connection connection = SetDatabaseConnection.getConnection();

                        //Execute a SQL query to determine the number of admins in the admin_account table
                        String sql1 = "SELECT COUNT(Admin_ID) AS Admin_Count FROM admin_account";

                        //Execute a SQL query to determine if a user exists with the same username as the new user
                        String sql2 = "SELECT Approved_username AS username FROM approved_user_account WHERE Approved_username LIKE BINARY '" + Username + "';";

                        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                        ResultSet resultSet2 = preparedStatement2.executeQuery();

                        //Store the number of admins in adminCount
                        resultSet1.next();
                        int adminCount = resultSet1.getInt("Admin_Count");
                        System.out.println(adminCount);

                        //If a new or approved user does not exist with the same username, the user inputted values for username and password are inserted into the new_user_account table
                        if(!resultSet2.next())
                        {
                            //Generate a random admin ID for the new user
                            //The particular admin would approve or reject the new user
                            Random rand = new Random();
                            int randomNum = rand.nextInt(adminCount) + 1;
                            System.out.println(randomNum);

                            //Insert into new_user_account the user ID, username, and password of the new user and the corresponding admin ID
                            PreparedStatement preparedStatementInsertValue = connection.prepareStatement("INSERT INTO new_user_account VALUES (0, '" + Username + "', '" + User_password + "', " + randomNum + ")");
                            preparedStatementInsertValue.executeUpdate();
                            UserLogin userLogin = new UserLogin();
                            userLogin.start(stage);
                        }
                        else
                            throw new Exception("Error");
                    }
                    catch (Exception e)
                    {
                        //Username of the new user exists, therefore an error screen is displayed
                        new AccountExistsWarningScreen().start(new Stage());
                        System.out.println(e);;
                    }

                    System.out.print("New User's Email: " + Username + "\n");
                    System.out.print("New User's Password: " + User_password + "\n");

                }

                textField0.setText("");
                textField1.setText("");
            }  });

        //Clear button
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Clear button pushed");
                textField0.setText("");
                textField1.setText("");
            }  });

        //Return to LogIn Page button
        Button loginReturn= new Button("Return to LogIn Page");
        loginReturn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return to LogIn Page button pushed");
                UserLogin userLogin = new UserLogin();
                userLogin.start(stage);
            }  });

        //GridPane set up
        GridPane gridPane1 = new GridPane();
        gridPane1.setMinSize(400, 400);
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setAlignment(Pos.CENTER);

        //Add nodes to GridPane and set alignment
        gridPane1.add(newUserSignUp,0,0,2,1);
        GridPane.setHalignment(newUserSignUp, HPos.CENTER);

        gridPane1.add(newUsername, 0, 1);
        gridPane1.add(textField0, 1, 1);

        gridPane1.add(newUserPassword, 0, 2);
        gridPane1.add(textField1, 1, 2);

        gridPane1.add(submit, 0, 3,2,1);
        GridPane.setHalignment(submit, HPos.CENTER);

        gridPane1.add(clear, 0, 4,2,1);
        GridPane.setHalignment(clear, HPos.LEFT);

        gridPane1.add(loginReturn,0, 4,2,1);
        GridPane.setHalignment(loginReturn, HPos.RIGHT);

        //Set GridPane and Node style
        newUserSignUp.setStyle("-fx-font: normal bold 20px 'serif' ");
        newUsername.setStyle("-fx-font: normal bold 20px 'serif' ");
        newUserPassword.setStyle("-fx-font: normal bold 20px 'serif' ");
        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        loginReturn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        gridPane1.setStyle("-fx-background-color: BEIGE;");

        //Set Scene and Stage
        Scene scene1 = new Scene(gridPane1);
        stage.setTitle("Create New User Account");
        stage.setScene(scene1);
        stage.show();
    }
}


