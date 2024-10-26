package assignment.thereadingroom.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance = null;
    private static final String URL = "jdbc:sqlite:mydb.db";

    private Database(){}

    // Get the singleton instance.
    public static synchronized Database getInstance(){
        if (instance == null) {
           instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        try {
            return DriverManager.getConnection(URL);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public void closeConnection(Connection connection){
        if (connection != null) {
            try{
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
