package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetNewUserList {

    public static ObservableList<NewUser> retrieveNewUserList(int adminID)
    {
        //refresh button todo
        try
        {
            String sql = "SELECT User_ID, Username, User_password FROM new_user_account WHERE Admin_ID = " + adminID;
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ObservableList<NewUser> dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            return dbData;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    private static ArrayList<NewUser> dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<NewUser> data =  new ArrayList<>();
        while (resultSet.next()) {
            NewUser newUser = new NewUser(resultSet.getInt("User_ID"), resultSet.getString("Username"), resultSet.getString("User_password"));
            data.add(newUser);
        }
        return data;
    }

}
