package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Book;
import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.view.AddBook;
import assignment.thereadingroom.view.LoginScreen;
import assignment.thereadingroom.view.UpdateBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class AdminDashboardController {
    @FXML
    Button logoutButton;

    @FXML
    Button addBookButton;

    @FXML
    Button updateBookButton;

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TableColumn<Book, String> authorsCol;

    @FXML
    private TableColumn<Book, Integer> nPhysicalCopiesCol;

    @FXML
    private TableColumn<Book, Float> priceCol;

    @FXML
    private TableColumn<Book, Integer> nCopiesSoldCol;

    // Initialize the controller and populate the booksTable
    @FXML
    public void initialize() {
        setupTableColumns();
        loadBooks();
    }

    // Set up the table columns
    private void setupTableColumns() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        nPhysicalCopiesCol.setCellValueFactory(new PropertyValueFactory<>("nPhysicalCopies"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        nCopiesSoldCol.setCellValueFactory(new PropertyValueFactory<>("nSoldCopies"));
    }

    private void loadBooks() {
        try {
            List<Book> books = Model.getInstance().getBookDao().getAllBooks();
            ObservableList<Book> booksList = FXCollections.observableArrayList();
            for(Book book : books) {
                booksList.add(book);
            }
            booksTable.setItems(booksList);
        } catch (Exception e) {
            e.printStackTrace(); // handle as needed, e.g., log or show alert
        }
    }

    public void onLogoutButtonAction(ActionEvent event) {
        Model.getInstance().setCurrentUser(null);
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.hide();
        LoginScreen loginScreen = new LoginScreen(stage);
        loginScreen.show();
    }

    public void onAddBookButtonAction(ActionEvent event) {
        Stage stage = (Stage) addBookButton.getScene().getWindow();
        stage.hide();
        AddBook addBook = new AddBook(stage);
        addBook.show();
    }

    public void onUpdateBookButtonAction(ActionEvent event) {
        Stage stage = (Stage) updateBookButton.getScene().getWindow();
        stage.hide();
        UpdateBook updateBook = new UpdateBook(stage);
        updateBook.show();
    }

}
