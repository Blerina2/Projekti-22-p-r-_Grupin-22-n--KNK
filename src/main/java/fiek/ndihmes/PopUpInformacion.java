package kinema.fiek.ndihmes;


import javafx.scene.control.Alert;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class PopUpInformacion {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {


        if (alertType.name() == null ? alertType.INFORMATION.name() == null : alertType.name().equals(alertType.INFORMATION.name())) {
            Notifications.create()
              .title(title)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showInformation();
          } else if (alertType.name() == null ? Alert.AlertType.ERROR.name() == null : alertType.name().equals(Alert.AlertType.ERROR.name())) {
            Notifications.create()
                    .title(title)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showError();
        }
      else if (alertType.name() == null ? Alert.AlertType.CONFIRMATION.name() == null : alertType.name().equals(Alert.AlertType.CONFIRMATION.name())) {
            Notifications.create()
                    .title(title)
                    .owner(owner)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showConfirm();
        }
    }
}
