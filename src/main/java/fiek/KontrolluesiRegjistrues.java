package kinema.fiek;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;
import kinema.fiek.entity.UserRole;
import kinema.fiek.ndihmes.KontrolluesiHyrjesNdihmes;
import kinema.fiek.ndihmes.PopUpInformacion;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KontrolluesiRegjistrues implements Initializable {

    private final Connection connection;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Button registerButton;

    Window window;


    public KontrolluesiRegjistrues() {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        connection = dbc.getConnection();
    }

    @FXML
    private void register() {
        window = registerButton.getScene().getWindow();


        if (KontrolluesiHyrjesNdihmes.isValidated(connection, window, firstName, lastName, email,
                username, password, confirmPassword)) {
            try {
                PreparedStatement ps;
                String query = "insert into users (first_name,last_name,email,user_name,password, user_role)values (?,?,?,?,?,?)";
                ps = connection.prepareStatement(query);
                ps.setString(1, firstName.getText());
                ps.setString(2, lastName.getText());
                ps.setString(3, email.getText());
                ps.setString(4, username.getText());
                ps.setString(5, password.getText());
                ps.setString(6, String.valueOf(UserRole.KLIENTI.getUserRole()));
                if (ps.executeUpdate() > 0) {
                    this.clearForm();
                    PopUpInformacion.showAlert(Alert.AlertType.INFORMATION, window, "Information",
                            "Ju jeni regjistruar me sukses");
                } else {
                    PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                            "Dicka shkoi keq.");
                }

            } catch (SQLException ex) {
                PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                        "Dicka shkoi keq.");
            }
        }
    }


    private boolean clearForm() {
        firstName.clear();
        lastName.clear();
        email.clear();
        username.clear();
        password.clear();
        confirmPassword.clear();
        return true;
    }

    @FXML
    private void showLoginStage() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        StageUtil.createStage(stage, "login-view.fxml", "images/kino-icon.png", "Sistemi i prenotimeve të kinemasë nga grupit 22");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}