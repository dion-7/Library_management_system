package assignment.thereadingroom.model;

import java.sql.SQLException;

import assignment.thereadingroom.dao.UserDao;
import assignment.thereadingroom.dao.UserDaoImpl;

public class Model {
    private UserDao userDao;
    private User currentUser;

    public Model() {
        userDao = new UserDaoImpl();
    }

    public void setup() throws SQLException {
        userDao.setup();
    }
    public UserDao getUserDao() {
        return userDao;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
