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

public class SearchTasksTableViewHelper {

    public static TableColumn<Task, String> getTitleColumn()
    {
        TableColumn<Task, String> title = new TableColumn<>("Title");
        PropertyValueFactory<Task, String> titleCellValueFactory = new PropertyValueFactory<>("title");
        title.setCellValueFactory(titleCellValueFactory);
        return title;
    }

    public static TableColumn<Task, String> getStartTime() {
        TableColumn<Task, String> startTimeCol = new TableColumn<>("Start Time");
        PropertyValueFactory<Task, String> startTimeCellValueFactory = new PropertyValueFactory<>("start_date_time");
        startTimeCol.setCellValueFactory(startTimeCellValueFactory);
        return startTimeCol;
    }

    public static TableColumn<Task, String> getEndTime() {
        TableColumn<Task, String> endTimeCol = new TableColumn<>("End Time");
        PropertyValueFactory<Task, String> endTimeCellValueFactory = new PropertyValueFactory<>("end_date_time");
        endTimeCol.setCellValueFactory(endTimeCellValueFactory);
        return endTimeCol;
    }

    public static TableColumn<Task, Integer> getDuration() {
        TableColumn<Task, Integer> durationCol = new TableColumn<>("Duration");
        PropertyValueFactory<Task, Integer> durationCellValueFactory = new PropertyValueFactory<>("duration");
        durationCol.setCellValueFactory(durationCellValueFactory);
        return durationCol;
    }

    public static TableColumn<Task, String> getDescriptionOfTask() {
        TableColumn<Task, String> descriptionOfTaskCol = new TableColumn<>("Description of Task");
        PropertyValueFactory<Task, String> descriptionOfTaskCellValueFactory = new PropertyValueFactory<>("description_of_task");
        descriptionOfTaskCol.setCellValueFactory(descriptionOfTaskCellValueFactory);
        return descriptionOfTaskCol;
    }

    public static TableColumn<Task, Void> addDeleteButtonToTable()
    {
        TableColumn<Task, Void> colBtn = new TableColumn<>("Delete Task");

        Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<>()
        {
            @Override
            public TableCell<Task, Void> call(final TableColumn<Task, Void> param)
            {
                TableCell<Task, Void> cell = new TableCell<>() {

                    final Button btn = new Button("Delete");
                    {
                        btn.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent event) {
                                TableView<Task> tableView = getTableView();
                                Task task = tableView.getItems().get(getIndex());
                                tableView.getItems().remove(task);
                                deleteTask(task);
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

    private static void deleteTask(Task task) {
        int taskID = task.getTask_ID();
        String title = task.getTitle();
        System.out.println(title);

        try
        {
            //String sql1 = "SET FOREIGN_KEY_CHECKS = 0;";
            String sql2 = "DELETE FROM task WHERE Task_ID LIKE BINARY " + taskID + ";";
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

    public static void refreshPage(Stage stage, int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description)
    {
        System.out.println("Page has been refreshed");
        SearchTasksTableViewScreen searchTasksTableViewScreen = new SearchTasksTableViewScreen(userID, startDate, startTime, endDate, endTime, duration, title, description);
        searchTasksTableViewScreen.start(stage);
    }

    public static void returnToMenuPage(Stage stage, int userID)
    {
        UserMenu userMenu = new UserMenu(userID);
        userMenu.start(stage);
    }

}
