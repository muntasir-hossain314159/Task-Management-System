package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

//Admin Login Screen
public class AdminLogin extends Application{

    public void start(Stage stage)
    {
        //Texts
        Text text0= new Text("Admin LogIn");
        Text text1 = new Text("Username");
        Text text2 = new Text("Password");

        //Text fields for user input
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                System.out.println("Submit button pushed");
                String Admin_username = textField1.getText();
                String Admin_password = textField2.getText();

                System.out.print("Admin Username:"+ Admin_username +"\n");
                System.out.print("Admin Password:"+ Admin_password +"\n");

                //Execute SQL query to determine if admin Username and Password matches exactly with the user input
                try
                {
                    Connection connection = SetDatabaseConnection.getConnection();
                    String sql = "SELECT Admin_ID AS ID FROM admin_account WHERE Admin_username LIKE BINARY'" + Admin_username + "' AND Admin_password LIKE BINARY'" + Admin_password + "';";
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet resultSet = statement.executeQuery(sql);

                    //Count the number of rows that the SQL query outputs
                    int count = 0;
                    while(resultSet.next())
                    {
                        count++;
                    }
                    resultSet.beforeFirst(); //set the pointer for resultSet before the first row

                    //If count == 1, then a single admin exists with that username and password (user can log in)
                    if(count == 1)
                    {
                        resultSet.next();
                        int ID = resultSet.getInt("ID");
                        AdminMenu adminMenu = new AdminMenu(ID);
                        adminMenu.start(stage);
                    }
                    //If count > 1, admin is not able to log in
                    else if(count > 1)
                    {
                        System.out.println("System Error"); //There is a system error because the application is built as such that the username has to be unique
                    }
                    //Else (count == 0), admin has inputted wrong username or password
                    else
                    {
                        Stage stage1 = new Stage();
                        //Display warning screen for wrong username or password
                        UsernamePasswordWarningScreen usernamePasswordWarningScreen = new UsernamePasswordWarningScreen();
                        usernamePasswordWarningScreen.start(stage1);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }

                textField1.setText("");
                textField2.setText("");
            }  });

        //Cancel Button
        Button cancel= new Button("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Cancel button pushed");
                //Return to user login page
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
        gridPane1.add(text0, 0, 0,2,1);
        GridPane.setHalignment(text0, HPos.CENTER);
        GridPane.setValignment(text0, VPos.TOP);

        gridPane1.add(text1, 0, 1);
        gridPane1.add(textField1, 1, 1);

        gridPane1.add(text2, 0, 2);
        gridPane1.add(textField2, 1, 2);

        gridPane1.add(submit, 0, 3,2,1);
        GridPane.setHalignment(submit, HPos.CENTER);

        gridPane1.add(cancel, 0, 4,2,1);
        GridPane.setHalignment(cancel, HPos.CENTER);

        //Set GridPane and Node style
        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        cancel.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane1.setStyle("-fx-background-color: BEIGE;");

        //Set Scene and Stage
        Scene scene1 = new Scene(gridPane1);
        stage.setTitle("Admin Login");
        stage.setScene(scene1);
        stage.show();
    }
}
