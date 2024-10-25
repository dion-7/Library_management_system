package assignment.thereadingroom.dao;

import java.sql.SQLException;

import assignment.thereadingroom.model.User;

public interface UserDao {
    void setup() throws SQLException;
    User getUser(String username, String password) throws SQLException;
    User createUser(String username, String password, String firstName, String lastName) throws SQLException;
    boolean updateUser(String username, String password, String firstName, String lastName) throws SQLException;
}
