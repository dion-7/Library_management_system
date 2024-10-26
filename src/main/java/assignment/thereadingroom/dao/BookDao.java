package assignment.thereadingroom.dao;

import assignment.thereadingroom.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    void setup() throws SQLException;

    Book getBook(String title) throws SQLException;

    List<Book> getAllBooks() throws SQLException;

    List<Book> getMostSoldBooks(int count) throws SQLException;

    Book createBook(Book book) throws SQLException;

    boolean updateBook(Book book) throws SQLException;

    boolean isBookAvailable(String title, int quantity) throws SQLException;
}