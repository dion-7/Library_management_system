package assignment.thereadingroom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    private Database(){}

    // Get the singleton instance.
    public static Connection getConnection(){
        if (instance == null) {
            try {
                instance = DriverManager.getConnection(URL);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static void closeConnection(){
        if (instance != null) {
            try{
                instance.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
