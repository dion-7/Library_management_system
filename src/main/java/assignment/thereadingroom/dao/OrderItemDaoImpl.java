package assignment.thereadingroom.dao;

import assignment.thereadingroom.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoImpl implements OrderitemDao {
    private static final String TABLE_NAME = "order_items"; // Change to your actual table name

    @Override
    public void setup() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id VARCHAR(36) PRIMARY KEY, " +
                "orderId VARCHAR(36), " +
                "book_title VARCHAR(255), " +
                "quantity INT, " +
                "price DOUBLE)";
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public OrderItem getOrderItemById(String id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderItem(
                            rs.getString("id"),
                            rs.getString("orderId"),
                            rs.getString("bookTitle"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE order_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderItems.add(new OrderItem(
                            rs.getString("id"),
                            rs.getString("orderId"),
                            rs.getString("bookTitle"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    ));
                }
            }
        }
        return orderItems;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderIds(String[] orderIds) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE orderId IN (" + String.join(",", orderIds) + ")";
        try (Connection connection = Database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getString("id"),
                        rs.getString("orderId"),
                        rs.getString("bookTitle"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }
        }
        return orderItems;
    }
}
