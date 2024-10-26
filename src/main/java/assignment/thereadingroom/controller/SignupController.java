package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.model.User;
import assignment.thereadingroom.view.LoginScreen;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignupController {

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
    Button signupButton;

    @FXML
    Button loginLink;

    @FXML
    Label successLabel;

    public void onLoginLinkAction(ActionEvent event) {
        Stage stage = (Stage) loginLink.getScene().getWindow();
        stage.hide();

        LoginScreen loginScreen = new LoginScreen(stage);
        loginScreen.show();
    }

    public void onSignupButtonAction(ActionEvent event) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            errorLabel.setText("All fields are required");
            return;
        }

        try{
            User user = Model.getInstance().getUserDao().createUser(username, password, firstName, lastName);
            if(user == null) {
                errorLabel.setText("Signup failed. Please try again!");
            }

            successLabel.setText("Signup successful. Redirecting to Login...");

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e->{
                Stage stage = (Stage) usernameInput.getScene().getWindow();
                stage.hide();

                LoginScreen loginScreen = new LoginScreen(stage);
                loginScreen.show();
            });
            pause.play();
            // Go to login screen

        }catch (Exception e){
            // TODO: Improve this to be more specific
            errorLabel.setText("Error signing up, check the username is unique and try again!");
        }
    }
}
