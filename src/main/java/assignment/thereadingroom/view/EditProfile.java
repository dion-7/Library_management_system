package assignment.thereadingroom.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditProfile {
    private Stage stage;

    public EditProfile(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assignment/thereadingroom/edit_profile.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TheReadingRoom: Edit Profile");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(){
        stage.setResizable(true);
        stage.show();
    }

    public void hide(){
        stage.hide();
        stage.setResizable(false);
    }
}
