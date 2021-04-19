package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetApprovedUserList {

    public static ObservableList<ApprovedUser> retrieveApprovedUserList()
    {
        //refresh button todo
        try
        {
            String sql = "SELECT * FROM approved_user_account";
            Connection connection = SetDatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ObservableList<ApprovedUser> dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            return dbData;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    private static ArrayList<ApprovedUser> dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<ApprovedUser> data =  new ArrayList<>();
        while (resultSet.next()) {
            ApprovedUser approvedUser = new ApprovedUser(resultSet.getInt("Approved_user_ID"), resultSet.getString("Approved_username"), resultSet.getString("Approved_user_password"));
            data.add(approvedUser);
        }
        return data;
    }

}
