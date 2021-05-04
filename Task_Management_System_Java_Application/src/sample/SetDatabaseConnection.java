package sample;

import java.sql.Connection;
import java.sql.DriverManager;

//Connects to local Database
public class SetDatabaseConnection {

    public static Connection getConnection() throws Exception
    {
        try
        {
            String url = "jdbc:mysql://127.0.0.1:3306/Task_Management_System_DB"; //set url equal to the JDBC url of the MySQL connection
            String username = "root";           //set username equal to the particular username set for the MySQL connection
            String password = "Uchchal@2()23";  //set password equal to the particular password set for the MySQL connection

            //Gets a connection to the database
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
