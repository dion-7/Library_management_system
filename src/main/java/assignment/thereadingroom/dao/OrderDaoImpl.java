package assignment.thereadingroom.dao;

import assignment.thereadingroom.dto.OrderCreateDto;
import assignment.thereadingroom.dto.OrderItemCreateDto;
import assignment.thereadingroom.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final String TABLE_NAME = "orders";
    private final String ORDER_ITEM_TABLE_NAME = "order_items";
    private final String BOOKS_TABLE_NAME = "books";

    public OrderDaoImpl() {}

    @Override
    public void setup() throws SQLException {
        try(Connection connection = Database.getInstance().getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "id TEXT PRIMARY KEY,"
                    + "username TEXT NOT NULL,"
                    + "created_at TEXT NOT NULL,"
                    + "total_amount REAL NOT NULL)";
            statement.executeUpdate(sql);
        }
    }

    @Override
    public List<Order> getOrdersByUsername(String username) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getString("id"));
                    order.setUsername(resultSet.getString("username"));
                    order.setTotalPrice(resultSet.getDouble("total_amount"));
                    order.setCreatedAt(resultSet.getString("created_at"));
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    @Override
    public Order getOrder(String id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = Database.getInstance().getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getString("id"));
                    order.setUsername(resultSet.getString("username"));
                    order.setTotalPrice(resultSet.getDouble("total_amount"));
                    order.setCreatedAt(resultSet.getString("created_at"));
                    return order;
                }
            }
        }
        return null;
    }

    @Override
    public Order createOrder(OrderCreateDto orderCreateDto, List<OrderItemCreateDto> orderItemCreateDtoList) throws SQLException {
        String orderSql = "INSERT INTO " + TABLE_NAME + " (id, username, created_at, total_amount) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();){
            connection.setAutoCommit(false);
            try (PreparedStatement orderStatement = connection.prepareStatement(orderSql)) {
                orderStatement.setString(1, orderCreateDto.getId());
                orderStatement.setString(2, orderCreateDto.getUsername());
                orderStatement.setString(3, orderCreateDto.getCreatedAt());
                orderStatement.setDouble(4, orderCreateDto.getTotalPrice());

                // Check inventory before placing the order
                for(OrderItemCreateDto orderItemCreateDto : orderItemCreateDtoList) {
                    if(!this.checkBookAvailability(connection, orderItemCreateDto.getBookTitle(), orderItemCreateDto.getQuantity())) {
                        throw new SQLException("Insufficient stock for book: " + orderItemCreateDto.getBookTitle());
                    }
                }

                orderStatement.executeUpdate();

                // Insert each order item
                for (OrderItemCreateDto orderItemCreateDto : orderItemCreateDtoList) {
                    String orderItemSql = "INSERT INTO " + ORDER_ITEM_TABLE_NAME + " (id, order_id, book_title, price, quantity) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement orderItemStatement = connection.prepareStatement(orderItemSql)) {
                        orderItemStatement.setString(1, orderCreateDto.getId());
                        orderItemStatement.setString(2, orderItemCreateDto.getOrderId());
                        orderItemStatement.setString(3, orderItemCreateDto.getBookTitle());
                        orderItemStatement.setDouble(4, orderItemCreateDto.getPrice());
                        orderItemStatement.setInt(5, orderItemCreateDto.getQuantity());
                        orderItemStatement.executeUpdate();

                        // Update the sold copies and physical copies for the book
                        updateBookCopies(connection, orderItemCreateDto.getBookTitle(), orderItemCreateDto.getQuantity());
                    }

                }
            }
            connection.commit();
        }
        Order order = new Order();
        order.setId(orderCreateDto.getId());
        order.setUsername(orderCreateDto.getUsername());
        order.setTotalPrice(orderCreateDto.getTotalPrice());
        order.setCreatedAt(orderCreateDto.getCreatedAt());
        return order;
    }

    // Check if the book is available in sufficient quantity
    private boolean checkBookAvailability(Connection connection, String bookTitle, int requestedQuantity) throws SQLException {
        String sql = "SELECT n_physical_copies FROM " + BOOKS_TABLE_NAME + " WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bookTitle);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int availableCopies = rs.getInt("nPhysicalCopies");
                    return availableCopies >= requestedQuantity; // Check if requested quantity is available
                }
            }
        }
        return false; // Book not found or insufficient stock
    }

    // Update sold copies of the book
    private void updateBookCopies(Connection connection, String bookTitle, int quantity) throws SQLException {
        // Update the sold copies
        String updateSoldSql = "UPDATE " + BOOKS_TABLE_NAME + " SET n_sold_copies = n_sold_copies + ? WHERE title = ?";
        try (PreparedStatement updateStmt = connection.prepareStatement(updateSoldSql)) {
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, bookTitle);
            updateStmt.executeUpdate();
        }

        // Update the physical copies
        String updatePhysicalSql = "UPDATE " + BOOKS_TABLE_NAME + " SET n_physical_copies = n_physical_copies + ? WHERE title = ?";
        try (PreparedStatement updateStmt = connection.prepareStatement(updatePhysicalSql)) {
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, bookTitle);
            updateStmt.executeUpdate();
        }
    }
}
