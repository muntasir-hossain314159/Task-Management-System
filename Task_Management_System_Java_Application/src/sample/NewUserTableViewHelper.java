package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.Connection;
import java.sql.PreparedStatement;

//NewUserTableViewHelper class is used to create TableColumns that are added to the TableView
public class NewUserTableViewHelper {

    //Username Column
    public static TableColumn<NewUser, String> getUsernameColumn()
    {
        TableColumn<NewUser, String> usernameCol = new TableColumn<>("Username");
        PropertyValueFactory<NewUser, String> usernameCellValueFactory = new PropertyValueFactory<>("username");
        usernameCol.setCellValueFactory(usernameCellValueFactory);
        return usernameCol;
    }

    //Password Column
    public static TableColumn<NewUser, String> getPasswordColumn() {
        TableColumn<NewUser, String> passwordCol = new TableColumn<>("Password");
        PropertyValueFactory<NewUser, String> passwordCellValueFactory = new PropertyValueFactory<>("password");
        passwordCol.setCellValueFactory(passwordCellValueFactory);
        return passwordCol;
    }

    //Approve Button column (adds an approve button to each row of data)
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
                                System.out.println("Approve button clicked");
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

    //Reject Button column (adds a reject button to each row of data)
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
                                System.out.println("Reject button clicked");
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

    //Inserts a new user into the approved_user_account table when an admin approves that user
    private static void insertNewUser(NewUser newUser) {
        int ID = newUser.getID();
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        System.out.println(ID);
        System.out.println(username);
        System.out.println(password);

        try
        {
            Connection connection = SetDatabaseConnection.getConnection();

            String sql = "INSERT INTO approved_user_account VALUES (" + ID + ", '" + username + "', '" + password + "')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //Deletes a new user by removing their data from the new_user_account table when an admin rejects that user
    private static void deleteNewUser(NewUser newUser) {
        int ID = newUser.getID();
        String username = newUser.getUsername();
        String password = newUser.getPassword();

        System.out.println(username);
        System.out.println(password);

        try
        {
            Connection connection = SetDatabaseConnection.getConnection();

            String sql = "DELETE FROM new_user_account WHERE User_ID LIKE BINARY " + ID + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //Refreshes page by reloading the NewUserTableViewScreen
    public static void refreshPage(Stage stage, int ID)
    {
        System.out.println("Page has been refreshed");
        NewUserTableViewScreen newUserTableViewScreen = new NewUserTableViewScreen(ID);
        newUserTableViewScreen.start(stage);
    }

    //Returns to Admin Menu
    public static void returnToMenuPage(Stage stage, int adminID)
    {
        AdminMenu adminMenu = new AdminMenu(adminID);
        adminMenu.start(stage);
    }




}