package kinema.fiek;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import kinema.fiek.bazaDhenave.dto.UserDto;
import kinema.fiek.entity.User;
import kinema.fiek.ndihmes.KontrolluesiHyrjesNdihmes;
import kinema.fiek.ndihmes.PopUpInformacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static kinema.fiek.entity.UserRole.*;

public class KontrolluesiHyrjes implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    Window window;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    private void login() throws Exception {
//        Stage stage = (Stage) loginButton.getScene().getWindow();
//        stage.close();
//        StageUtil.createStage(stage, "main-panel-view.fxml", "images/kino-icon.png", "");


        if (KontrolluesiHyrjesNdihmes.isValidated(loginButton.getScene().getWindow(), this.username, this.password)) {

            User user = UserDto.getUser(this.username.getText(), this.password.getText(), this.window);
            if (user != null) {
                if (isSystemmanger(user.getUserRole())) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    StageUtil.createStage(stage, "main-panel-view.fxml", "images/kino-icon.png", "");
                } else if (isAdminstrator(user.getUserRole())) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    StageUtil.createStage(stage, "main-panel-view.fxml", "images/kino-icon.png", "");
                } else if (isKlienti(user.getUserRole())) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    StageUtil.createStage(stage, "main-panel-view.fxml", "images/kino-icon.png", "");
                }

            } else {
                PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                        "Emër përdoruesi dhe fjalëkalimi nuk jane gjetur ne db");
                username.requestFocus();
            }
        } else {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Emër përdoruesi dhe fjalëkalimi i pavlefshëm.");
            username.requestFocus();
        }
    }

    @FXML
    private void showRegisterStage() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
        StageUtil.createStage(stage, "regjistrohu-view.fxml", "images/kino-icon.png", "Regjistrimi i përdoruesit");
    }
}