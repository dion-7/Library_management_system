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

public class AddBookController {

    @FXML
    Button backButton;

    @FXML
    Button addBookButton;

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

    public void onBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();
        AdminDashboard adminDashboard = new AdminDashboard(stage);
        adminDashboard.show();
    }

    public void onAddBookButtonAction(ActionEvent event) {
        String title = titleInput.getText();
        String authors = authorsInput.getText();
        String nPhysicalCopies = nPhysicalCopiesInput.getText();
        String nCopiesSold = nCopiesSoldInput.getText();
        String price = priceInput.getText();

        // Clear previous error message
        errorLabel.setText("");


        if(title.isEmpty() || authors.isEmpty() || nPhysicalCopies.isEmpty() || nCopiesSold.isEmpty() || price.isEmpty()) {
            errorLabel.setText("Please fill all the fields");
            return;
        }

        try {
            int nPhysicalCopiesInt = Integer.parseInt(nPhysicalCopies);
            int nCopiesSoldInt = Integer.parseInt(nCopiesSold);
            float priceFloat = Float.parseFloat(price);

            Book book = new Book();
            book.setTitle(title);
            book.setAuthors(authors);
            book.setNSoldCopies(nCopiesSoldInt);
            book.setNPhysicalCopies(nPhysicalCopiesInt);
            book.setPrice(priceFloat);

            Model.getInstance().getBookDao().createBook(book);

            clearInputs();
            successLabel.setText("Book added successfully");
        } catch (NumberFormatException e){
            errorLabel.setText("Please enter valid numbers for physical copies, sold copies, and price.");
        }
        catch (Exception e) {
            errorLabel.setText("Failed to add book");
        }
    }

    private void clearInputs() {
        titleInput.clear();
        authorsInput.clear();
        nPhysicalCopiesInput.clear();
        nCopiesSoldInput.clear();
        priceInput.clear();
    }
}
