package assignment.thereadingroom.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoadingScreen {
    private Stage stage;

    public LoadingScreen(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assignment/thereadingroom/splash.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("The Reading Room");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void show() {
        stage.show();
    }

    public void hide() {
        stage.hide();
    }
}
