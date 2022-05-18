package kinema.fiek;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StageUtil {

    public static void createStage(Stage stage, String viewName, String iconPath, String stageTitle) throws IOException {
        Parent root = FXMLLoader.load(StageUtil.class.getResource("view/" + viewName));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(StageUtil.class.getResource(iconPath).toString()));
        stage.setTitle(stageTitle);
        stage.show();
    }

}
