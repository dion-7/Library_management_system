package assignment.thereadingroom;

import assignment.thereadingroom.model.Model;
import assignment.thereadingroom.view.LoadingScreen;
import assignment.thereadingroom.view.LoginScreen;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage loadingStage) throws IOException {
        loadingStage.setResizable(false);
        loadingStage.initStyle(StageStyle.UNDECORATED);
        LoadingScreen loadingScreen = new LoadingScreen(loadingStage);
        loadingScreen.show();

        new Thread(() -> {
            Model model = Model.getInstance();
            boolean[] setupSuccessfull = {false};
            try {
                model.setup();
                setupSuccessfull[0] = true;
            }catch (Exception e){
                e.printStackTrace();
            }

            javafx.application.Platform.runLater(()->{
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> {
                    loadingScreen.hide();

                    if(setupSuccessfull[0]){
                        // show main stage
                        showMainStage(loadingStage);
                    }else {
                        showErrorDialog("Setup failed", "An error occurred while initializing the application.");
                    }

                });
                pause.play();
            });
        }).start();
    }

    private void showMainStage(Stage loadingStage){
        Stage mainStage = new Stage();
        mainStage.initStyle(StageStyle.DECORATED);
        mainStage.setResizable(false);

        LoginScreen loginScreen = new LoginScreen(mainStage);
        loginScreen.show();

        loadingStage.close();
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // You can set a header text or leave it as null
        alert.setContentText(message);
        alert.showAndWait(); // This will block until the user closes the dialog
    }

    public static void main(String[] args) {
        launch(args);
    }
}
