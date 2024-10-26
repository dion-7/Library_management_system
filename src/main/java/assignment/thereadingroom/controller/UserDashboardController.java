package assignment.thereadingroom.controller;

import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.view.EditProfile;
import assignment.thereadingroom.view.LoginScreen;
import assignment.thereadingroom.view.OrderScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    public void initialize() {
        greetingLabel.setText("Welcome "+ Model.getInstance().getCurrentUser().getFullName());
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

}
