package assignment.thereadingroom.dao;

import assignment.thereadingroom.model.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderitemDao {
    void setup() throws SQLException;
    OrderItem getOrderItemById(String id) throws SQLException;
    List<OrderItem> getOrderItemsByOrderId(String orderId) throws SQLException;
    List<OrderItem> getOrderItemsByOrderIds(String[] orderIds) throws SQLException;
}
