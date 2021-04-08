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
import java.sql.Statement;

public class UserLogin extends Application /*implements ActionListener*/ {

    public void start(Stage stage) {
        Text text0 = new Text("Welcome to Task Management System");
        //creating label email
        Text text1 = new Text("Username");

        //creating label password
        Text text2 = new Text("Password");

        //Creating Text Filed for email
        TextField textField1 = new TextField();
                /*textField1.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                                System.out.println(textField1);
                                }  });*/

        //Creating Text Filed for password
        PasswordField textField2 = new PasswordField();
                /*textField2.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                                System.out.println(textField2);
                                }  });*/

        //Creating Buttons
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Submit button pushed");
                String username = textField1.getText();
                String password = textField2.getText();

                try
                {
                    //todo
                    Connection connection = SetDatabaseConnection.getConnection();
                    //SELECT Approved_user_ID AS ID FROM approved_user_account WHERE Approved_username = '" + username + "' AND Approved_user_password = '" + password + "';
                    String sql = "SELECT Approved_user_ID AS ID FROM approved_user_account WHERE Approved_username LIKE BINARY'" + username + "' AND Approved_user_password LIKE BINARY '" + password + "';";
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
                        UserMenu userMenu = new UserMenu();
                        userMenu.setID(ID);
                        userMenu.start(stage);
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

                System.out.print("Username:" + username + "\n");
                System.out.print("Password:" + password + "\n");

                textField1.setText("");
                textField2.setText("");
            }
        });

        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Clear button pushed");
                textField1.setText("");
                textField2.setText("");
            }
        });

        Button createUser = new Button("Create New User");
        createUser.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Create New User button pushed");
                CreateNewUserAccount createNewUserAccount = new CreateNewUserAccount();
                createNewUserAccount.start(stage);
            }
        });
        /*Button date = new Button("Admin Menu");
        date.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("date button pushed");
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.start(stage);
            }
        });*/
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
        gridPane.add(text0, 0, 0, 3, 1);
        GridPane.setHalignment(text0, HPos.CENTER);
        gridPane.add(text1, 0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(text2, 0, 2);
        gridPane.add(textField2, 1, 2);
        gridPane.add(submit, 0, 3, 3, 1);
        GridPane.setHalignment(submit, HPos.CENTER);
        gridPane.add(clear, 0, 4, 3, 1);
        GridPane.setHalignment(clear, HPos.LEFT);
        gridPane.add(createUser, 0, 4, 3, 1);
        GridPane.setHalignment(createUser, HPos.CENTER);
        //gridPane.add(date, 3, 2);
        gridPane.add(admin, 0, 4, 3, 1);
        GridPane.setHalignment(admin, HPos.RIGHT);


        //Styling nodes
        submit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clear.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        createUser.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        //date.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        admin.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
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

    public static void main(String args[]){
        launch(args);
    }

}
