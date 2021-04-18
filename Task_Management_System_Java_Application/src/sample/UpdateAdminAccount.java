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

public class UpdateAdminAccount extends Application {
    private int ID;

    public UpdateAdminAccount(int ID) {
        this.ID = ID;
    }

    public void start(Stage stage) {
        Text text0= new Text ("Update Admin Account");
        Text text3 = new Text("New Admin Username");
        Text text4 = new Text("New Admin Password");
        TextField textField3 = new TextField();
        PasswordField textField4 = new PasswordField();

        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");
                String Admin_username = textField3.getText();
                String Admin_password = textField4.getText();

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
                    System.out.println(e);;
                }

                System.out.print("Admin's New Email: " + Admin_username + "\n");
                System.out.print("Admin's New Password: " + Admin_password + "\n");

                textField3.setText("");
                textField4.setText("");
            }  });
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Clear button pushed");
                textField3.setText("");
                textField4.setText("");
            }  });
        Button menuReturn= new Button("Return to Menu");
        menuReturn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return to Menu button pushed");
                AdminMenu adminMenu = new AdminMenu(ID);
                adminMenu.start(stage);
            }  });
        GridPane gridPane1 = new GridPane();
        //gridPane1.setGridLinesVisible(true);
        gridPane1.setMinSize(400, 400);
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setAlignment(Pos.CENTER);
        gridPane1.add(text0,0,0,2,1);
        GridPane.setHalignment(text0, HPos.CENTER);
        gridPane1.add(text3, 0, 1);
        gridPane1.add(textField3, 1, 1);
        gridPane1.add(text4, 0, 2);
        gridPane1.add(textField4, 1, 2);
        gridPane1.add(submit, 0, 3,2,1);
        GridPane.setHalignment(submit, HPos.CENTER);
        gridPane1.add(clear, 0, 4,2,1);
        GridPane.setHalignment(clear, HPos.LEFT);
        gridPane1.add(menuReturn,0, 4,2,1);
        GridPane.setHalignment(menuReturn, HPos.RIGHT);
        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        menuReturn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text3.setStyle("-fx-font: normal bold 20px 'serif' ");
        text4.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane1.setStyle("-fx-background-color: BEIGE;");
        Scene scene1 = new Scene(gridPane1);
        stage.setTitle("Update Admin Account");
        stage.setScene(scene1);
        stage.show();
    }
}
