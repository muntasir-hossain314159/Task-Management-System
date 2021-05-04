package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.sql.Connection;
import java.sql.PreparedStatement;

//ApprovedUserTableViewHelper class is used to create TableColumns that are added to the TableView
public class ApprovedUserTableViewHelper {

    //Username Column
    public static TableColumn<ApprovedUser, String> getUsernameColumn()
    {
        TableColumn<ApprovedUser, String> usernameCol = new TableColumn<>("Username");
        PropertyValueFactory<ApprovedUser, String> usernameCellValueFactory = new PropertyValueFactory<>("username");
        usernameCol.setCellValueFactory(usernameCellValueFactory);
        return usernameCol;
    }

    //Password Column
    public static TableColumn<ApprovedUser, String> getPasswordColumn() {
        TableColumn<ApprovedUser, String> passwordCol = new TableColumn<>("Password");
        PropertyValueFactory<ApprovedUser, String> passwordCellValueFactory = new PropertyValueFactory<>("password");
        passwordCol.setCellValueFactory(passwordCellValueFactory);
        return passwordCol;
    }

    //Delete Button Column (adds a delete button to each row of data)
    public static TableColumn<ApprovedUser, Void> addDeleteButtonToTable()
    {
        TableColumn<ApprovedUser, Void> colBtn = new TableColumn<>("Delete User");
        Callback<TableColumn<ApprovedUser, Void>, TableCell<ApprovedUser, Void>> cellFactory = new Callback<>()
        {
            @Override
            public TableCell<ApprovedUser, Void> call(final TableColumn<ApprovedUser, Void> param)
            {
                TableCell<ApprovedUser, Void> cell = new TableCell<>() {

                    final Button btn = new Button("Delete");
                    {
                        btn.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent event) {
                                TableView<ApprovedUser> tableView = getTableView();
                                ApprovedUser approvedUser = tableView.getItems().get(getIndex());
                                tableView.getItems().remove(approvedUser);
                                deleteApprovedUser(approvedUser);
                                System.out.println("Delete Button clicked");
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

    //Deletes an approved user by removing their data from the approved_user_account table
    private static void deleteApprovedUser(ApprovedUser approvedUser) {
        int ID = approvedUser.getID();
        String username = approvedUser.getUsername();
        String password = approvedUser.getPassword();
        System.out.println(username);
        System.out.println(password);

        try
        {
            String sql = "DELETE FROM approved_user_account WHERE Approved_user_ID LIKE BINARY " + ID + ";";
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //Refreshes page by reloading the ApprovedUserTableViewScreen
    public static void refreshPage(Stage stage, int ID)
    {
        System.out.println("Page has been refreshed");
        ApprovedUserTableViewScreen approvedUserTableViewScreen = new ApprovedUserTableViewScreen(ID);
        approvedUserTableViewScreen.start(stage);
    }

    //Returns to Admin Menu Screen
    public static void returnToMenuPage(Stage stage, int adminID)
    {
        AdminMenu adminMenu = new AdminMenu(adminID);
        adminMenu.start(stage);
    }

}
