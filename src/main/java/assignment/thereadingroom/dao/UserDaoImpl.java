package assignment.thereadingroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import assignment.thereadingroom.model.User;

public class UserDaoImpl implements UserDao {
    private final String TABLE_NAME = "users";

    public UserDaoImpl() {
    }

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
             Statement stmt = connection.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (username VARCHAR(10) NOT NULL,"
                    + "password VARCHAR(8) NOT NULL,"
                    + "first_name VARCHAR(10) NOT NULL,"
                    + "last_name VARCHAR(10) NOT NULL,"
                    + "PRIMARY KEY (username))";
            stmt.executeUpdate(sql);

            // Insert the admin user if it doesn't already exist
            String insertAdminSql = "INSERT INTO " + TABLE_NAME + " (username, password, first_name, last_name) "
                    + "SELECT 'admin', 'reading_admin', 'Admin', 'Admin' "
                    + "WHERE NOT EXISTS (SELECT 1 FROM " + TABLE_NAME + " WHERE username = 'admin')";
            stmt.executeUpdate(insertAdminSql);
        }
    }

    @Override
    public User getUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    return user;
                }
                return null;
            }
        }
    }

    @Override
    public User createUser(String username, String password, String firstName, String lastName) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);

            stmt.executeUpdate();
            return new User(username, password, firstName, lastName);
        }
    }

    @Override
    public boolean updateUser(String username, String password, String firstName, String lastName) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET password = ?, first_name = ?, last_name = ? WHERE username = ?";
        try(Connection connection = Database.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, password);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, username);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}
