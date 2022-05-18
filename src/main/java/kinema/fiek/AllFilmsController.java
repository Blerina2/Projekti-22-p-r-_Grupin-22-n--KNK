package kinema.fiek;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;
import kinema.fiek.entity.Film;
import kinema.fiek.ndihmes.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllFilmsController implements Initializable {

    private final Connection connection;

    List<Film> fileList = new ArrayList<>();

    HBox hbChildren = new HBox();

    @FXML
    ScrollPane scrollPane;
    @FXML
    GridPane grid;
    @FXML
    ImageView imageView;
    @FXML
    Image image;
    @FXML
    String id;

    public AllFilmsController() {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        connection = dbc.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fileList = Session.getFilmList();

        // gridpane settings
        // setting exterior grid padding
        grid.setPadding(new Insets(7, 7, 7, 7));
        // setting interior grid padding
        grid.setHgap(10);
        grid.setVgap(10);

        // reshtat dhe shtylla ne all filma
        int rows = (fileList.size() / 4) + 1;
        int columns = 7;
        int imageIndex = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (imageIndex < fileList.size()) {
                    // shtoj ne cdo resht dhe shtylle nje foto
                    shtojeFoton(imageIndex, j, i);
                    imageIndex++;
                }
            }
        }
    }

    /**
     * @param index    ne listen fileList
     * @param colIndex ne listen fileList
     * @param rowIndex
     */
    private void shtojeFoton(int index, int colIndex, int rowIndex) {
        // konverto int ne string
        String id = String.valueOf(fileList.get(index).getId());
        this.image = fileList.get(index).getIMAGE();
        this.imageView = new ImageView();
        this.imageView.setFitWidth(160);
        this.imageView.setFitHeight(220);
        this.imageView.setImage(image);
        this.imageView.setId(id);
        this.hbChildren.getChildren().add(this.imageView);
        GridPane.setConstraints(this.imageView, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        this.grid.getChildren().addAll(this.imageView);

        this.imageView.setOnMouseClicked(e -> {
            try {
                // ruajtja e filmit të zgjedhur për të personalizuar skenën e krijuar rishtazi
                Film film = new Film();
                film.setTitle(id);
                Session.setFilm(film);

                // Pasi të keni shtypur imazhin me maus, hidheni në film
                Stage stage = (Stage) this.scrollPane.getScene().getWindow();
                StageUtil.createStage(stage, "ViewSelectedFilmScene.fxml", "images/kino-icon.png", "");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
