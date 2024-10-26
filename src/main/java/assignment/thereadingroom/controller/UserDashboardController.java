package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Book;
import assignment.thereadingroom.model.Cart;
import assignment.thereadingroom.model.CartItem;
import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.view.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class UserDashboardController {
    @FXML
    Button editProfileButton;

    @FXML
    Label greetingLabel;

    @FXML
    Button logoutButton;

    @FXML
    Button orderHistoryButton;

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

    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, String> cartTitleCol;

    @FXML
    private TableColumn<CartItem, String> cartPriceCol;

    @FXML
    private TableColumn<CartItem, Integer> cartQuantityCol;

    @FXML
    private Button addOrEditCartItemButton;

    @FXML
    private Button checkoutButton;

    @FXML
    private Label totalAmountLabel;

    @FXML
    public void initialize() {
        greetingLabel.setText("Welcome "+ Model.getInstance().getCurrentUser().getFullName());

        setupTableColumns();
        loadBooks();
        loadCartItems();

        double totalPrice = Model.getInstance().getCart().getTotalPrice();
        totalAmountLabel.setText(String.format("%.2f", totalPrice) + " AUD");
    }

    // Set up the table columns
    private void setupTableColumns() {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsCol.setCellValueFactory(new PropertyValueFactory<>("authors"));
        nPhysicalCopiesCol.setCellValueFactory(new PropertyValueFactory<>("nPhysicalCopies"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        nCopiesSoldCol.setCellValueFactory(new PropertyValueFactory<>("nSoldCopies"));

        cartTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        cartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        cartQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadBooks() {
        try {
            List<Book> books = Model.getInstance().getBookDao().getMostSoldBooks();
            ObservableList<Book> booksList = FXCollections.observableArrayList();
            for(Book book : books) {
                booksList.add(book);
            }
            booksTable.setItems(booksList);
        } catch (Exception e) {
            e.printStackTrace(); // handle as needed, e.g., log or show alert
        }
    }

    private void loadCartItems() {
        try {
            List<CartItem> cartItems = Model.getInstance().getCart().getItems();
            ObservableList<CartItem> cartItemList = FXCollections.observableArrayList();
            for(CartItem cartItem : cartItems) {
                cartItemList.add(cartItem);
            }
            cartTable.setItems(cartItemList);
        } catch (Exception e) {
            e.printStackTrace(); // handle as needed, e.g., log or show alert
        }
    }

    public void onEditProfileButtonAction(ActionEvent event) {
        Stage stage = (Stage) editProfileButton.getScene().getWindow();
        stage.hide();
        EditProfile editProfile = new EditProfile(stage);
        editProfile.show();
    }

    public void onLogoutButtonAction(ActionEvent event) {
        Model.getInstance().setCurrentUser(null);
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.hide();
        LoginScreen loginScreen = new LoginScreen(stage);
        loginScreen.show();
    }

    public void onOrderHistoryButtonAction(ActionEvent event) {
        Stage stage = (Stage) orderHistoryButton.getScene().getWindow();
        stage.hide();
        OrderScreen orderScreen = new OrderScreen(stage);
        orderScreen.show();
    }

    public void onAddOrEditCartItemButton(ActionEvent event) {
        Stage stage = (Stage) addOrEditCartItemButton.getScene().getWindow();
        stage.hide();
        AddEditCartItem addEditCartItem = new AddEditCartItem(stage);
        addEditCartItem.show();
    }

    public void onCheckoutButtonAction(ActionEvent event) {
        if(Model.getInstance().getCart().isEmpty()){
            return;
        }

        Stage stage = (Stage) checkoutButton.getScene().getWindow();
        stage.hide();
        CheckoutScreen checkoutScreen = new CheckoutScreen(stage);
        checkoutScreen.show();
    }

}
