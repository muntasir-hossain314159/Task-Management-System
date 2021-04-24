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

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteUserAccount extends Application {

    private int ID;

    public DeleteUserAccount(int ID) {
        this.ID = ID;
    }

    public void start(Stage stage) {

        Text text0 = new Text("Delete User Account");

        Button menu = new Button("Return to Menu");
        menu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Return button pushed");
                UserMenu userMenu = new UserMenu(ID);
                userMenu.start(stage);
            }
        });


        Text text1 = new Text("Are you sure you want to delete your account: ");
        Button yes = new Button("Yes");
        yes.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Delete account button pushed");
                deleteAccount(ID, stage);
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
        gridPane.setHgap(5);

        yes.setMaxWidth(Double.MAX_VALUE);
        //menu.setMaxWidth(8);

        //Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text0, 0, 0, 2, 1);
        GridPane.setHalignment(text0, HPos.CENTER);

        gridPane.add(text1, 0, 1, 1, 1);
        //GridPane.setHalignment(newUserList, HPos.CENTER);

        gridPane.add(yes, 1, 1, 1, 1);

        gridPane.add(menu, 0, 2, 2, 1);
        GridPane.setHalignment(menu, HPos.CENTER);



        //Styling nodes
        yes.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        menu.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");

        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Delete Account");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    private void deleteAccount(int ID, Stage stage)
    {
        try
        {

            String sql2 = "DELETE FROM approved_user_account WHERE Approved_user_ID LIKE BINARY " + ID + ";";

            Connection connection = SetDatabaseConnection.getConnection();

            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            preparedStatement2.executeUpdate();

            new UserLogin().start(stage);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }

}
