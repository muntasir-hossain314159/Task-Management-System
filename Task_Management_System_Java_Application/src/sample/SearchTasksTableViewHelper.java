package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
