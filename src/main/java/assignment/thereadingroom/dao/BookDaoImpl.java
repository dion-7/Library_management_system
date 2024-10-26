package assignment.thereadingroom.dao;

import assignment.thereadingroom.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final String TABLE_NAME = "books";

    public BookDaoImpl() {}

    @Override
    public void setup() throws SQLException {
        try (Connection connection = Database.getInstance().getConnection();
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
        try (Connection connection = Database.getInstance().getConnection();
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
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthors(resultSet.getString("authors"));
                    book.setNPhysicalCopies(resultSet.getInt("n_physical_copies"));
                    book.setPrice(resultSet.getFloat("price"));
                    book.setNSoldCopies(resultSet.getInt("n_sold_copies"));
                    books.add(book);
                }
            }
        }
        return books;
    }

    @Override
    public List<Book> getMostSoldBooks(int count) throws SQLException {
        String sql = "SELECT * FROM books ORDER BY n_sold_copies DESC LIMIT ?";
        List<Book> mostSoldBooks = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, count);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setTitle(rs.getString("title"));
                    book.setAuthors(rs.getString("authors"));
                    book.setNPhysicalCopies(rs.getInt("n_physical_copies"));
                    book.setPrice(rs.getFloat("price"));
                    book.setNSoldCopies(rs.getInt("n_sold_copies"));
                    mostSoldBooks.add(book);
                }
            }
        }
        return mostSoldBooks;
    }


    @Override
    public Book createBook(Book book) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME
                + " (title, authors, n_physical_copies, price, n_sold_copies)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Database.getInstance().getConnection();
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
                + " SET authors = ?,"
                + "n_physical_copies = ?,"
                + "price = ?,"
                + "n_sold_copies = ?"
                + " WHERE title = ?";

        try (Connection connection = Database.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getAuthors());
            statement.setInt(2, book.getNPhysicalCopies());
            statement.setFloat(3, book.getPrice());
            statement.setInt(4, book.getNSoldCopies());
            statement.setString(5, book.getTitle());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean isBookAvailable(String title, int quantity) throws SQLException {
        String sql = "SELECT n_physical_copies FROM " + TABLE_NAME + " WHERE title=?";
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    int available_copies = resultSet.getInt("n_physical_copies");
                    return available_copies >= quantity;
                }
            }
        }
        return false;
    }
}
