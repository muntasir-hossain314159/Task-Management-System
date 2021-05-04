package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Executes a SQL query to obtain all the users in the new_user_account table corresponding to the logged in admin and stores it in an ObservableList
public class GetNewUserList {

    public static ObservableList<NewUser> retrieveNewUserList(int adminID)
    {
        try
        {
            Connection connection = SetDatabaseConnection.getConnection();

            String sql = "SELECT User_ID, Username, User_password FROM new_user_account WHERE Admin_ID = " + adminID;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            //Converts the ArrayList to an ObservableList and returns the List
            ObservableList<NewUser> dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            return dbData;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    private static ArrayList<NewUser> dataBaseArrayList(ResultSet resultSet) throws SQLException
    {
        ArrayList<NewUser> data =  new ArrayList<>();

        //Loops through the ResultSet and stores the data in each row in a (new) NewUser object
        while (resultSet.next()) {
            NewUser newUser = new NewUser(resultSet.getInt("User_ID"), resultSet.getString("Username"), resultSet.getString("User_password"));

            //Adds the (new) NewUser object to the data ArrayList
            data.add(newUser);
        }
        return data;
    }

}
