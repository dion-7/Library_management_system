package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.model.User;
import assignment.thereadingroom.view.UserDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProfileController {

    @FXML
    TextField usernameInput;

    @FXML
    PasswordField passwordInput;

    @FXML
    TextField firstNameInput;

    @FXML
    TextField lastNameInput;

    @FXML
    Label errorLabel;

    @FXML
    Button updateButton;

    @FXML
    Button backButton;

    @FXML
    public void initialize() {
        User user = Model.getInstance().getCurrentUser();
        usernameInput.setText(user.getUsername());
        passwordInput.setText(user.getPassword());
        firstNameInput.setText(user.getFirstName());
        lastNameInput.setText(user.getLastName());
    }

    public void onBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();
        UserDashboard userDashboard = new UserDashboard(stage);
        userDashboard.show();
    }

    public void onUpdateButtonAction(ActionEvent event) {
        User user = Model.getInstance().getCurrentUser();
        String password = passwordInput.getText();
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        if(password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            errorLabel.setText("All fields are must be non empty");
            return;
        }

        try {
            Model.getInstance().getUserDao().updateUser(user.getUsername(), password, firstName, lastName);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            Model.getInstance().setCurrentUser(user);

            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.hide();
            UserDashboard userDashboard = new UserDashboard(stage);
            userDashboard.show();
        } catch (Exception e) {
            errorLabel.setText("Updating profile failed");
        }
    }
}
