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
import java.sql.Timestamp;

//SearchTasksTableViewHelper class is used to create TableColumns that are added to the TableView
public class SearchTasksTableViewHelper {

    //Title Column
    public static TableColumn<Task, String> getTitleColumn()
    {
        TableColumn<Task, String> title = new TableColumn<>("Title");
        PropertyValueFactory<Task, String> titleCellValueFactory = new PropertyValueFactory<>("title");
        title.setCellValueFactory(titleCellValueFactory);
        return title;
    }

    //Start Date and Time Column
    public static TableColumn<Task, String> getStartTime() {
        TableColumn<Task, String> startTimeCol = new TableColumn<>("Start Time (YYYY-MM-DD HH:MM:SS)");
        PropertyValueFactory<Task, String> startTimeCellValueFactory = new PropertyValueFactory<>("start_date_time");
        startTimeCol.setCellValueFactory(startTimeCellValueFactory);
        return startTimeCol;
    }

    //End Date and Time Column
    public static TableColumn<Task, String> getEndTime() {
        TableColumn<Task, String> endTimeCol = new TableColumn<>("End Time (YYYY-MM-DD HH:MM:SS)");
        PropertyValueFactory<Task, String> endTimeCellValueFactory = new PropertyValueFactory<>("end_date_time");
        endTimeCol.setCellValueFactory(endTimeCellValueFactory);
        return endTimeCol;
    }

    //Duration Column
    public static TableColumn<Task, Integer> getDuration() {
        TableColumn<Task, Integer> durationCol = new TableColumn<>("Duration (Minutes)");
        PropertyValueFactory<Task, Integer> durationCellValueFactory = new PropertyValueFactory<>("duration");
        durationCol.setCellValueFactory(durationCellValueFactory);
        return durationCol;
    }

    //Description Column
    public static TableColumn<Task, String> getDescriptionOfTask() {
        TableColumn<Task, String> descriptionOfTaskCol = new TableColumn<>("Description of Task");
        PropertyValueFactory<Task, String> descriptionOfTaskCellValueFactory = new PropertyValueFactory<>("description_of_task");
        descriptionOfTaskCol.setCellValueFactory(descriptionOfTaskCellValueFactory);
        return descriptionOfTaskCol;
    }

    //Edit Button column (adds an edit button to each row of data)
    public static TableColumn<Task, Void> addEditButtonToTable()
    {
        TableColumn<Task, Void> colBtn = new TableColumn<>("Edit Task");

        Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<>()
        {
            @Override
            public TableCell<Task, Void> call(final TableColumn<Task, Void> param)
            {
                TableCell<Task, Void> cell = new TableCell<>() {

                    final Button btn = new Button("Edit");
                    {
                        btn.setOnAction(new EventHandler<ActionEvent>(){
                            public void handle(ActionEvent event) {
                                TableView<Task> tableView = getTableView();
                                Task task = tableView.getItems().get(getIndex());
                                tableView.getItems().remove(task);
                                editTask(task);
                                System.out.println("Edit button clicked");
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

    //Delete Button column (adds a delete button to each row of data)
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

    //Edits task by passing the current value of that task to the EditTask class
    private static void editTask(Task task)
    {
        int taskID = task.getTask_ID();
        int userID = task.getUser_ID();
        String title = task.getTitle();
        Timestamp startTime = task.getStart_date_time();
        Timestamp endTime = task.getEnd_date_time();
        String description = task.getDescription_of_task();

        EditTask editTask = new EditTask(taskID, userID, title, startTime, endTime, description);
        editTask.start(new Stage());
    }

    //Deletes a task by removing all the corresponding data of task from the task table
    private static void deleteTask(Task task) {
        int taskID = task.getTask_ID();

        //Prints the title of the task being deleted
        String title = task.getTitle();
        System.out.println(title);

        try
        {
            Connection connection = SetDatabaseConnection.getConnection();

            String sql = "DELETE FROM task WHERE Task_ID LIKE BINARY " + taskID + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    //Refreshes page by reloading the SearchTasksTableViewScreen
    public static void refreshPage(Stage stage, int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description)
    {
        System.out.println("Page has been refreshed");
        SearchTasksTableViewScreen searchTasksTableViewScreen = new SearchTasksTableViewScreen(userID, startDate, startTime, endDate, endTime, duration, title, description);
        searchTasksTableViewScreen.start(stage);
    }

    //Returns to Search Task page
    public static void returnToSearchTasksPage(Stage stage, int userID)
    {
        SearchTasks searchTasks = new SearchTasks(userID);
        searchTasks.start(stage);
    }

}
