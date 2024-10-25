package assignment.thereadingroom.dao;

import assignment.thereadingroom.model.Book;

import java.sql.*;

public class BookDaoImpl implements BookDao {
    private final String TABLE_NAME = "books";

    public BookDaoImpl() {}

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + "title VARCHAR(255) NOT NULL,"
                    + "authors TEXT NOT NULL,"
                    + "n_physical_copies INTEGER NOT NULL,"
                    + "price REAL NOT NULL,"
                    + "n_sold_copies INTEGER NOT NULL,"
                    + "PRIMARY KEY (title))";
            statement.execute(sql);
        }
    }

    @Override
    public Book getBook(String title) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE title=?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);

            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthors(resultSet.getString("authors"));
                    book.setNPhysicalCopies(resultSet.getInt("n_physical_copies"));
                    book.setPrice(resultSet.getFloat("price"));
                    book.setNSoldCopies(resultSet.getInt("n_sold_copies"));

                    return book;
                }
                return null;
            }
        }
    }

    @Override
    public Book createBook(Book book) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME
                + " (title, authors, n_physical_copies, price, n_sold_copies)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors());
            statement.setInt(3, book.getNPhysicalCopies());
            statement.setFloat(4, book.getPrice());
            statement.setInt(5, book.getNSoldCopies());
            statement.execute();
            return book;
        }
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME
                + "SET authors = ?,"
                + "n_physical_copies = ?,"
                + "price = ?,"
                + "n_sold_copies = ?"
                + " WHERE title = ?";

        try (Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getAuthors());
            statement.setInt(2, book.getNPhysicalCopies());
            statement.setFloat(3, book.getPrice());
            statement.setInt(4, book.getNSoldCopies());
            statement.setString(5, book.getTitle());
            return statement.executeUpdate() > 0;
        }
    }
}
