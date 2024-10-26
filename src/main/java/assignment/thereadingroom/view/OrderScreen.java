package assignment.thereadingroom.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderScreen {
    private Stage stage;

    public OrderScreen(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assignment/thereadingroom/orders.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("TheReadingRoom: Order History");
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
