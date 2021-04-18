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

public class AdminLogin extends Application{

    public void start(Stage stage) {
        Text text0= new Text("Admin LogIn");
        Text text1 = new Text("Username");
        Text text2 = new Text("Password");
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");
                String Admin_username = textField1.getText();
                String Admin_password = textField2.getText();

                System.out.print("Username:"+ Admin_username +"\n");
                System.out.print("Password:"+ Admin_password +"\n");

                try
                {
                    Connection connection = SetDatabaseConnection.getConnection();
                    //SELECT Approved_user_ID AS ID FROM approved_user_account WHERE Approved_username = '" + username + "' AND Approved_user_password = '" + password + "';
                    String sql = "SELECT Admin_ID AS ID FROM admin_account WHERE Admin_username LIKE BINARY'" + Admin_username + "' AND Admin_password LIKE BINARY'" + Admin_password + "';";
                    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //fix this
                    ResultSet resultSet = statement.executeQuery(sql);

                    int count = 0;
                    while(resultSet.next())
                    {
                        count++;
                    }
                    resultSet.beforeFirst();

                    if(count == 1)
                    {
                        resultSet.next();
                        int ID = resultSet.getInt("ID");
                        AdminMenu adminMenu = new AdminMenu(ID);
                        adminMenu.start(stage);
                    }
                    else if(count > 1)
                    {
                        System.out.println("System Error");
                    }
                    else
                    {
                        System.out.println("Error, Wrong Password");
                        //New Window saying error
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }

                textField1.setText("");
                textField2.setText("");
            }  });

        Button loginReturn= new Button("Cancel");
        loginReturn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Cancel button pushed");
                UserLogin userLogin = new UserLogin();
                userLogin.start(stage);
            }  });
        GridPane gridPane1 = new GridPane();
        //GridPane.getColumnIndex(text0);
        //System.out.println(GridPane.getColumnIndex(text0));
        //gridPane1.setGridLinesVisible(true);
        //GridPane.setColumnIndex(text0,2);
        gridPane1.setMinSize(400, 400);
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setAlignment(Pos.CENTER);
        gridPane1.add(text0, 0, 0,2,1);
        //System.out.println(GridPane.getColumnIndex(text0));
        GridPane.setHalignment(text0, HPos.CENTER);
        GridPane.setValignment(text0, VPos.TOP);
        gridPane1.add(text1, 0, 1);
        gridPane1.add(textField1, 1, 1);
        gridPane1.add(text2, 0, 2);
        gridPane1.add(textField2, 1, 2);
        gridPane1.add(submit, 0, 3,2,1);
        GridPane.setHalignment(submit, HPos.CENTER);
        gridPane1.add(loginReturn, 0, 4,2,1);
        GridPane.setHalignment(loginReturn, HPos.CENTER);
        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        loginReturn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane1.setStyle("-fx-background-color: BEIGE;");
        Scene scene1 = new Scene(gridPane1);
        stage.setTitle("Admin Login");
        stage.setScene(scene1);
        stage.show();
    }
}
