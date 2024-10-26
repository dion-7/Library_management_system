package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Book;
import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.view.AdminDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateBookController {
    @FXML
    Button backButton;

    @FXML
    Button updateBookButton;

    @FXML
    Button searchButton;

    @FXML
    Label searchErrorLabel;

    @FXML
    Label errorLabel;

    @FXML
    Label successLabel;

    @FXML
    TextField titleInput;

    @FXML
    TextField authorsInput;

    @FXML
    TextField nPhysicalCopiesInput;

    @FXML
    TextField nCopiesSoldInput;

    @FXML
    TextField priceInput;

    private Book book = null;

    public void onBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();
        AdminDashboard adminDashboard = new AdminDashboard(stage);
        adminDashboard.show();
    }

    public void onSearchButtonAction(ActionEvent event) {
        String title = titleInput.getText();

        searchErrorLabel.setText("");

        try {
            this.book = Model.getInstance().getBookDao().getBook(title);
            if (book == null) {
                searchErrorLabel.setText("Book not found");
                return;
            }

            authorsInput.setText(book.getAuthors());
            nPhysicalCopiesInput.setText(Integer.toString(book.getNPhysicalCopies()) );
            nCopiesSoldInput.setText(Integer.toString(book.getNSoldCopies()));
            priceInput.setText(Float.toString(book.getPrice()));

        }catch (Exception e) {
            searchErrorLabel.setText("Error finding the book");
        }
    }

    public void onUpdateBookButtonAction(ActionEvent event) {
        String nPhysicalCopies = nPhysicalCopiesInput.getText();
        String nCopiesSold = nCopiesSoldInput.getText();
        String price = priceInput.getText();

        errorLabel.setText("");

        try {
            int nPhysicalCopiesInt = Integer.parseInt(nPhysicalCopies);
            int nCopiesSoldInt = Integer.parseInt(nCopiesSold);
            float priceFloat = Float.parseFloat(price);

            this.book.setNPhysicalCopies(nPhysicalCopiesInt);
            this.book.setNSoldCopies(nCopiesSoldInt);
            this.book.setPrice(priceFloat);

            boolean done = Model.getInstance().getBookDao().updateBook(this.book);

            if (done) {
                successLabel.setText("Book updated");
            }else {
                errorLabel.setText("Failed to update the book");
            }
        }catch (Exception e) {
            errorLabel.setText("Error updating the book. Try again");
            e.printStackTrace();
        }
    }
}
