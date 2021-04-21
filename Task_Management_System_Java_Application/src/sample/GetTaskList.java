package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class GetTaskList {

    public static ObservableList<Task> retrieveTaskList(int userID, String startDate, String startTime, String endDate, String endTime, String duration, String title, String description)
    {
        //todo description field does not work!!!
        //todo make sure to change title so that user does not have to put in exact title
        String sql;

        if(!title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%'";

        else if(title.isEmpty() &&  !startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) = '" + startDate + "'";

        else if(title.isEmpty() &&  startDate.isEmpty() && !startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND TIME(Start_date_time) = '" + startTime + "'";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && !endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(End_date_time) = '" + endDate + "'";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && !endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(End_date_time) = '" + endTime + "'";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && !duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Duration = '" + duration + "'";

        else if(title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && !description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Description_of_task LIKE '%" + description + "%'";

        else if(!title.isEmpty() &&  !startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' AND DATE(Start_date_time) = '" + startDate + "'";

        else if(!title.isEmpty() &&  !startDate.isEmpty() && !startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' AND DATE(Start_date_time) = '" + startDate + "' AND TIME(Start_date_time) = '" + startTime + "'";

        else if(title.isEmpty() &&  !startDate.isEmpty() && !startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = '" + userID + "' AND DATE(Start_date_time) = '" + startDate + "' AND TIME(Start_date_time) = '" + startTime + "'";

        else if(title.isEmpty() &&  !startDate.isEmpty() && !startTime.isEmpty() && !endDate.isEmpty() && !endTime.isEmpty() && duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) = '" + startDate + "' AND TIME(Start_date_time) = '" + startTime + "' AND DATE(End_date_time) = '" + endDate + "' AND TIME(End_date_time) = '" + endTime + "'";

        else if(title.isEmpty() &&  !startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && !duration.isEmpty() && description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND DATE(Start_date_time) = '" + startDate + "' AND Duration = '" + duration + "'";

        else if(!title.isEmpty() &&  startDate.isEmpty() && startTime.isEmpty() && endDate.isEmpty() && endTime.isEmpty() && duration.isEmpty() && !description.isEmpty())
            sql = "SELECT * FROM task WHERE User_ID = " + userID + " AND Title LIKE '%" + title + "%' AND Description_of_task LIKE '%" + description + "%'";

        else
            sql = "SELECT * FROM task WHERE User_ID = " + userID;

        try
        {

            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
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
        while (resultSet.next()) {
            Task task = new Task(resultSet.getInt("Task_ID"), resultSet.getInt("User_ID"), resultSet.getTimestamp("Start_date_time"), resultSet.getTimestamp("End_date_time"), resultSet.getInt("Duration"), resultSet.getString("Title"), resultSet.getString("Description_of_task"));
            data.add(task);
        }
        return data;
    }

}
