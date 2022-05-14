package kinema.fiek.ndihmes;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KontrolluesiHyrjesNdihmes {

    public static boolean isValidated(Window window, TextField username, TextField password) {
        if (username.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emrit të përdoruesit nuk mund të jetë bosh.");
            username.requestFocus();
        } else if (username.getText().length() < 5 || username.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emrit të përdoruesit nuk mund të jetë më pak se 5 dhe më e madhe se 25 karaktere.");
            username.requestFocus();
        } else if (password.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të fjalëkalimit nuk mund të jetë bosh.");
            password.requestFocus();
        } else if (password.getText().length() < 5 || password.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të fjalëkalimit nuk mund të jetë më pak se 5 dhe më e madhe se 25 karaktere.");
            password.requestFocus();
        } else {
            return true;
        }
        return false;
    }


    public static boolean isValidated(Connection connection, Window window, TextField firstName, TextField lastName, TextField email,
                                      TextField username, TextField password, TextField confirmPassword) {

        if (firstName.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emrit nuk mund të jetë bosh.");
            firstName.requestFocus();
        } else if (firstName.getText().length() < 2 || firstName.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emrit nuk mund të jetë më pak se 2 dhe më e madhe se 25 karaktere.");
            firstName.requestFocus();
        } else if (lastName.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të mbiemrit nuk mund të jetë bosh.");
            lastName.requestFocus();
        } else if (lastName.getText().length() < 2 || lastName.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të mbiemrit nuk mund të jetë më e vogël se 2 dhe më e madhe se 25 karaktere.");
            lastName.requestFocus();
        } else if (email.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emailit nuk mund të jetë bosh.");
            email.requestFocus();
        } else if (email.getText().length() < 5 || email.getText().length() > 45) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emailit nuk mund të jetë më e vogël se 5 dhe më e madhe se 45 karaktere.");
            email.requestFocus();
        } else if (username.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emrit të përdoruesit nuk mund të jetë bosh.");
            username.requestFocus();
        } else if (username.getText().length() < 5 || username.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të emrit të përdoruesit nuk mund të jetë më pak se 5 dhe më e madhe se 25 karaktere.");
            username.requestFocus();
        } else if (password.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të fjalëkalimit nuk mund të jetë bosh.");
            password.requestFocus();
        } else if (password.getText().length() < 5 || password.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të fjalëkalimit nuk mund të jetë më e vogël se 5 dhe më e madhe se 25 karaktere.");
            password.requestFocus();
        } else if (confirmPassword.getText().equals("")) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të konfirmimit të fjalëkalimit nuk mund të jetë bosh.");
            confirmPassword.requestFocus();
        } else if (confirmPassword.getText().length() < 5 || password.getText().length() > 25) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fusha e tekstit të konfirmimit të fjalëkalimit nuk mund të jetë më e vogël se 5 dhe më e madhe se 25 karaktere.");
            confirmPassword.requestFocus();
        } else if (!password.getText().equals(confirmPassword.getText())) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Fushat e tekstit të fjalëkalimit dhe fjalëkalimit të konfirmimit nuk përputhen.");
            password.requestFocus();
        } else if (isAlreadyRegistered(connection, username)) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Emri i përdoruesit është marrë tashmë nga dikush tjetër.");
            username.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    private static boolean isAlreadyRegistered(Connection connection, TextField username) {
        PreparedStatement ps;
        ResultSet rs;
        boolean usernameExist = false;

        String query = "select * from users WHERE user_name = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username.getText());
            rs = ps.executeQuery();
            if (rs.next()) {
                usernameExist = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return usernameExist;
    }


}