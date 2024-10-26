package assignment.thereadingroom.model;

import java.sql.SQLException;

import assignment.thereadingroom.dao.*;

public class Model {
    private static Model instance;
    private UserDao userDao;
    private User currentUser;
    private BookDao bookDao;
    private OrderDao orderDao;
    private OrderitemDao orderitemDao;

    private Model() {
        userDao = new UserDaoImpl();
        bookDao = new BookDaoImpl();
        orderDao = new OrderDaoImpl();
        orderitemDao = new OrderItemDaoImpl();
    }

    public static Model getInstance() {
        if (instance == null){
            instance = new Model();
        }
        return instance;
    }

    public void setup() throws SQLException {
        userDao.setup();
        bookDao.setup();
        orderDao.setup();
        orderitemDao.setup();
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

    public Cart getCart() {
        return Cart.getCart();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public OrderitemDao getOrderitemDao() {
        return orderitemDao;
    }
}
