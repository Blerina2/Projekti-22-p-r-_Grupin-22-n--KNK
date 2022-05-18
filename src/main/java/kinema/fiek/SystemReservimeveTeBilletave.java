package kinema.fiek;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SystemReservimeveTeBilletave extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        StageUtil.createStage(stage, "login-view.fxml", "images/kino-icon.png", "KinoX grupit 22");
    }

    public static void main(String[] args) {
        launch();
    }
}
