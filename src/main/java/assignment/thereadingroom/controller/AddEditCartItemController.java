package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Book;
import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.view.UserDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditCartItemController {

    @FXML
    Button backButton;

    @FXML
    TextField titleInput;

    @FXML
    Label searchErrorLabel;

    @FXML
    Label searchResultLabel;

    @FXML
    Label errorLabel;

    @FXML
    TextField quantityInput;

    @FXML
    private Button clearCartButton;

    Book book;

    public void onBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();
        UserDashboard userDashboard = new UserDashboard(stage);
        userDashboard.show();
    }

    public void onClearCartButtonAction(ActionEvent event) {
        Model.getInstance().getCart().clearCart();
    }

    public void onSearchButtonAction(ActionEvent event) {
        String title = titleInput.getText();

        searchErrorLabel.setText("");
        searchResultLabel.setText("");

        try {
            this.book = Model.getInstance().getBookDao().getBook(title);
            if (book == null) {
                searchErrorLabel.setText("Book not found");
                return;
            }

            searchResultLabel.setText("Found " + book.getTitle() + "(" + book.getPrice() + "AUD )");

        }catch (Exception e) {
            searchErrorLabel.setText("Error finding the book");
        }
    }

    public void onUpdateCartButtonAction(ActionEvent event) {
        String quantity = quantityInput.getText();

        int quantityInt = Integer.parseInt(quantity);
        Model.getInstance().getCart().updateBookQuantity(book, quantityInt);
    }
}
