package sample;


import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class TableViewHelper {

    public static TableColumn<NewUser, String> getUsernameColumn()
    {
        TableColumn<NewUser, String> usernameCol = new TableColumn<>("Username");
        PropertyValueFactory<NewUser, String> usernameCellValueFactory = new PropertyValueFactory<>("username");
        usernameCol.setCellValueFactory(usernameCellValueFactory);
        return usernameCol;
    }

    public static TableColumn<NewUser, String> getPasswordColumn() {
        TableColumn<NewUser, String> passwordCol = new TableColumn<>("Password");
        PropertyValueFactory<NewUser, String> passwordCellValueFactory = new PropertyValueFactory<>("password");
        passwordCol.setCellValueFactory(passwordCellValueFactory);
        return passwordCol;
    }

    public static TableColumn<NewUser, Void> addApproveButtonToTable()
    {
        TableColumn<NewUser, Void> colBtn = new TableColumn<>("Approve User");

        Callback<TableColumn<NewUser, Void>, TableCell<NewUser, Void>> cellFactory = new Callback<>()
        {

            @Override
            public TableCell<NewUser, Void> call(final TableColumn<NewUser, Void> param)
            {
                TableCell<NewUser, Void> cell = new TableCell<>()
                {
                    final Button btn = new Button("Approve");
                    {
                        btn.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent event) {
                                TableView<NewUser> tableView = getTableView();
                                NewUser newUser = tableView.getItems().get(getIndex());
                                insertNewUser(newUser);
                                deleteNewUser(newUser);
                                tableView.getItems().remove(newUser);
                                System.out.println("Button clicked");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };

                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        return colBtn;
    }

    public static TableColumn<NewUser, Void> addRejectButtonToTable()
    {
        TableColumn<NewUser, Void> colBtn = new TableColumn<>("Reject User");

        Callback<TableColumn<NewUser, Void>, TableCell<NewUser, Void>> cellFactory = new Callback<>()
        {
            @Override
            public TableCell<NewUser, Void> call(final TableColumn<NewUser, Void> param)
            {
                 TableCell<NewUser, Void> cell = new TableCell<>() {

                    final Button btn = new Button("Reject");
                    {
                        btn.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent event) {
                                TableView<NewUser> tableView = getTableView();
                                NewUser newUser = tableView.getItems().get(getIndex());
                                tableView.getItems().remove(newUser);
                                deleteNewUser(newUser);
                                System.out.println("Button clicked");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                 };

                 return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        return colBtn;
    }

    private static void insertNewUser(NewUser newUser) {
        int ID = newUser.getID();
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        System.out.println(ID);
        System.out.println(username);
        System.out.println(password);

        try
        {
            String sql = "INSERT INTO approved_user_account VALUES (" + ID + ", '" + username + "', '" + password + "')";
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private static void deleteNewUser(NewUser newUser) {
        int ID = newUser.getID();
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        System.out.println(username);
        System.out.println(password);

        try
        {
            //String sql1 = "SET FOREIGN_KEY_CHECKS = 0;";
            String sql2 = "DELETE FROM new_user_account WHERE User_ID LIKE BINARY " + ID + ";";
            //String sql3 = "SET FOREIGN_KEY_CHECKS = 1;";
            Connection connection = SetDatabaseConnection.getConnection();
            //PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            //PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);

            //preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            //preparedStatement3.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void refreshPage(Stage stage, int ID)
    {
        System.out.println("Page has been refreshed");
        TableViewScreen tableViewScreen = new TableViewScreen(ID);
        tableViewScreen.start(stage);
    }

    public static void returnToMenuPage(Stage stage)
    {
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.start(stage);
    }




}