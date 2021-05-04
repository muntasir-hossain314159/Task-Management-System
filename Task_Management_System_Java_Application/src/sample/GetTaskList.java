package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;

public class GetTaskList {

    //Search for Tasks function call to retrieve task list
    public static ObservableList<Task> retrieveTaskList(int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description)
    {
        String sql;

        if(!title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  !startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) = '" + startDate + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  startDate.isEmpty() && !startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND TIME(Start_date_time) = '" + startTime + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && !endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(End_date_time) = '" + endDate + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && !endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(End_date_time) = '" + endTime + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && !duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Duration = '" + duration + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && !description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Description_of_task LIKE '%" + description + "%' ORDER BY Start_date_time DESC";

        else if(!title.isEmpty() &&  !startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' AND DATE(Start_date_time) = '" + startDate + "' ORDER BY Start_date_time DESC";

        else if(!title.isEmpty() &&  !startDate.isEmpty() && !startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' AND DATE(Start_date_time) = '" + startDate + "' AND TIME(Start_date_time) = '" + startTime + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  !startDate.isEmpty() && !startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = '" + userID + "' AND DATE(Start_date_time) = '" + startDate + "' AND TIME(Start_date_time) = '" + startTime + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  !startDate.isEmpty() && !startTime.isEmpty() && !endDate.isEmpty() && !endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) = '" + startDate + "' AND TIME(Start_date_time) = '" + startTime + "' AND DATE(End_date_time) = '" + endDate + "' AND TIME(End_date_time) = '" + endTime + "' ORDER BY Start_date_time DESC";

        else if(title.isEmpty() &&  !startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && !duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) = '" + startDate + "' AND Duration = '" + duration + "' ORDER BY Start_date_time DESC";

        else if(!title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && !description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' AND Description_of_task LIKE '%" + description + "%' ORDER BY Start_date_time DESC";

        else
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " ORDER BY Start_date_time DESC";

        try
        {
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //Converts the ArrayList to an ObservableList and returns the List
            ObservableList<Task> dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            return dbData;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;

    }

    //Tasks for this Week function call to retrieve task list
    public static ObservableList<Task> retrieveTaskList(int userID, String startDate, String weekFromStartDate)
    {
        String sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) >= '" + startDate + "' AND DATE(Start_date_time) <= '" + weekFromStartDate + "' ORDER BY Start_date_time";

        try
        {
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //Converts the ArrayList to an ObservableList and returns the List
            ObservableList<Task> dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            return dbData;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;

    }

    private static ArrayList<Task> dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<Task> data =  new ArrayList<>();

        //Loops through the ResultSet and stores the data in each row in a new Task object
        while (resultSet.next())
        {
            Task task = new Task(resultSet.getInt("Task_ID"), resultSet.getInt("User_ID"), resultSet.getTimestamp("Start_date_time"), resultSet.getTimestamp("End_date_time"), resultSet.getInt("Duration"), resultSet.getString("Title"), resultSet.getString("Description_of_task"));

            //Adds the new Task object to the data ArrayList
            data.add(task);
        }
        return data;
    }
}
