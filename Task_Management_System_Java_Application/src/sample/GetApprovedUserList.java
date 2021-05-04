package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Executes a SQL query to obtain all the users in the approved_user_account table and stores it in an ObservableList
public class GetApprovedUserList {

    public static ObservableList<ApprovedUser> retrieveApprovedUserList()
    {
        try
        {
            Connection connection = SetDatabaseConnection.getConnection();

            String sql = "SELECT * FROM approved_user_account";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //Converts the ArrayList to an ObservableList and returns the List
            ObservableList<ApprovedUser> dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            return dbData;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    private static ArrayList<ApprovedUser> dataBaseArrayList(ResultSet resultSet) throws SQLException
    {
        ArrayList<ApprovedUser> data =  new ArrayList<>();

        //Loops through the ResultSet and stores the data in each row in a new ApprovedUser object
        while (resultSet.next())
        {
            ApprovedUser approvedUser = new ApprovedUser(resultSet.getInt("Approved_user_ID"), resultSet.getString("Approved_username"), resultSet.getString("Approved_user_password"));

            //Adds the object to the data ArrayList
            data.add(approvedUser);
        }
        return data;
    }

}
