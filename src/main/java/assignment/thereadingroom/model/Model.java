package assignment.thereadingroom.model;

import java.sql.SQLException;

import assignment.thereadingroom.dao.*;

public class Model {
    private UserDao userDao;
    private User currentUser;
    private Cart cart;
    private BookDao bookDao;
    private OrderDao orderDao;
    private OrderitemDao orderitemDao;

    public Model() {
        cart = Cart.getCart();

        userDao = new UserDaoImpl();
        bookDao = new BookDaoImpl();
        orderDao = new OrderDaoImpl();
        orderitemDao = new OrderItemDaoImpl();

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
        return cart;
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
