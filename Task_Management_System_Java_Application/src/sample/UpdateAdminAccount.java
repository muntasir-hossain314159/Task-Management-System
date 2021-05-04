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

//Update Admin Account Screen
public class UpdateAdminAccount extends Application {
    private int ID;

    //Constructor
    public UpdateAdminAccount(int ID) {
        this.ID = ID;
    }

    public void start(Stage stage)
    {
        //Texts and TextFields
        Text updateAdminAccount= new Text ("Update Admin Account");
        Text newAdminUsername = new Text("New Admin Username");
        Text newAdminPassword = new Text("New Admin Password");

        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        //Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");
                String Admin_username = textField1.getText();
                String Admin_password = textField2.getText();

                //Updates admin_account table with user inputted values for username and password
                try
                {
                    Connection connection = SetDatabaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admin_account SET Admin_username = '" + Admin_username + "', Admin_password = '" + Admin_password + "' WHERE Admin_ID = " + ID);
                    preparedStatement.executeUpdate();
                    AdminLogin adminLogin = new AdminLogin();
                    adminLogin.start(stage);
                }
                catch (Exception e)
                {
                    //If username exists for a different user, the error screen is displayed
                    new AccountExistsWarningScreen().start(new Stage());
                    System.out.println(e);;
                }

                System.out.print("Admin's New Email: " + Admin_username + "\n");
                System.out.print("Admin's New Password: " + Admin_password + "\n");

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
        Button menuReturn= new Button("Return to Menu");
        menuReturn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return to Menu button pushed");
                AdminMenu adminMenu = new AdminMenu(ID);
                adminMenu.start(stage);
            }  });

        //GridPane set up
        GridPane gridPane1 = new GridPane();

        gridPane1.setMinSize(400, 400);
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setAlignment(Pos.CENTER);

        //Add Nodes to GridPane and set alignment
        gridPane1.add(updateAdminAccount,0,0,2,1);
        GridPane.setHalignment(updateAdminAccount, HPos.CENTER);

        gridPane1.add(newAdminUsername, 0, 1);
        gridPane1.add(textField1, 1, 1);

        gridPane1.add(newAdminPassword, 0, 2);
        gridPane1.add(textField2, 1, 2);

        gridPane1.add(submit, 0, 3,2,1);
        GridPane.setHalignment(submit, HPos.CENTER);

        gridPane1.add(clear, 0, 4,2,1);
        GridPane.setHalignment(clear, HPos.LEFT);

        gridPane1.add(menuReturn,0, 4,2,1);
        GridPane.setHalignment(menuReturn, HPos.RIGHT);

        //Styling Nodes and GridPane
        updateAdminAccount.setStyle("-fx-font: normal bold 20px 'serif' ");
        newAdminUsername.setStyle("-fx-font: normal bold 20px 'serif' ");
        newAdminPassword.setStyle("-fx-font: normal bold 20px 'serif' ");
        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        menuReturn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        gridPane1.setStyle("-fx-background-color: BEIGE;");

        //Set Scene and Stage
        Scene scene1 = new Scene(gridPane1);
        stage.setTitle("Update Admin Account");
        stage.setScene(scene1);
        stage.show();
    }
}
