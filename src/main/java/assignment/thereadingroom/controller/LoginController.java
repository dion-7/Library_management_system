package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.model.User;
import assignment.thereadingroom.view.AdminDashboard;
import assignment.thereadingroom.view.SignupScreen;
import assignment.thereadingroom.view.UserDashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label errorLabel;

    @FXML
    private Button signupLink;

    public void onSignupLinkAction(ActionEvent event) {
        Stage stage = (Stage) signupLink.getScene().getWindow();
        stage.hide();
        SignupScreen signupScreen = new SignupScreen(stage);
        signupScreen.show();
    }

    public void loginButtonOnAction(ActionEvent event) {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        if(username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username or Password is empty");
            return;
        }

        try{
            User user = Model.getInstance().getUserDao().getUser(username, password);
            if(user == null) {
                errorLabel.setText("Please check your username and password");
                return;
            }
            Model.getInstance().setCurrentUser(user);

            Stage stage = (Stage) usernameInput.getScene().getWindow();
            stage.hide();
            stage.setMaximized(true);
            if(user.isAdmin()){
                // Go to admin dashboard
                AdminDashboard adminDashboard = new AdminDashboard(stage);
                adminDashboard.show();
            }
            else {
                // Go to user dashboard
                UserDashboard userDashboard = new UserDashboard(stage);
                userDashboard.show();
            }
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }
    }
}
