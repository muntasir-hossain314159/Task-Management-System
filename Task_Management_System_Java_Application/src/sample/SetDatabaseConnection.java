package sample;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;

public class SetDatabaseConnection {

    public static Connection getConnection() throws Exception
    {
        try
        {
            String url = "jdbc:mysql://127.0.0.1:3306/Task_Management_System_DB";
            String username = "root";
            String password = "Uchchal@2()23";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return null;
    }
}
