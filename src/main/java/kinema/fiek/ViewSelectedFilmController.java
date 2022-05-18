package kinema.fiek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;
import kinema.fiek.entity.Film;
import kinema.fiek.ndihmes.Session;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSelectedFilmController implements Initializable {

    private final Connection connection;

    @FXML
    WebView webViewYoutube;
    @FXML
    AnchorPane anchorPaneId;

    @FXML
    ImageView filmiSelektuarNeImage;
    @FXML
    Text title;
    @FXML
    Text description;
    @FXML
    Text startDate;
    @FXML
    Text endDate;
    @FXML
    Text time;
    @FXML
    Button backButton, bookButton, deleteFilmButton;

    public ViewSelectedFilmController() {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        this.connection = dbc.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // mos te duket ne fill, kur perdoruesi e shtyy imagin athere video do te del
        this.webViewYoutube.setVisible(false);

        Film film = loadFilmatPrejDB(Session.getFilm().getTitle());
        this.filmiSelektuarNeImage.setImage(film.getIMAGE());
        this.title.setText(film.getTitle());
        this.description.setText(film.getPershkrimi());
        this.description.setTextAlignment(TextAlignment.JUSTIFY);
        this.startDate.setText(film.getStartdate());
        this.endDate.setText(film.getEndate());
        this.time.setText(film.getTime1() + ", " + film.getTime2());

        Session.setFilm(film);

        this.filmiSelektuarNeImage.setOnMouseClicked((event) -> {

            if (this.webViewYoutube.isVisible()) {
                this.webViewYoutube.setVisible(false);
            } else {
                this.webViewYoutube.setVisible(true);
            }
            // Ketu e kam marre shembullin https://www.youtube.com/watch?v=1cGmWPI5TaI
            String embedUrl = film.getTrailer().replace("watch?v=", "embed/");

            // mbasi perdoresu e shty button atehere mundet me pare tralerin ne youtuben.
            this.webViewYoutube.getEngine().load(embedUrl);

        });

    }


    private Film loadFilmatPrejDB(String id) {
        Film film = new Film();
        try {
            String query = "select ID, TITLE, PERSHKRIMI, TRAILER, STARTDATE, ENDATE, TIME1, TIME2, IMAGE from movies where ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();

            List<Object[]> objects = new ArrayList<>();

            while (resultSet.next()) {
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = resultSet.getObject(i);
                }
                objects.add(values);
            }

            return this.convertoNeObjekteFilmit(objects);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;
    }

    private Film convertoNeObjekteFilmit(List<Object[]> objects) {
        List<Film> films = new ArrayList<>();

        for (Object[] object : objects) {
            Film film = new Film();
            film.setId((int) object[0]);
            film.setTitle((String) object[1]);
            film.setPershkrimi((String) object[2]);
            film.setTrailer((String) object[3]);
            film.setStartdate((String) object[4]);
            film.setEndate((String) object[5]);
            film.setTime1((String) object[6]);
            film.setTime2((String) object[7]);

            // convertje byte array ne objekt te image
            byte[] bytes = (byte[]) object[8];
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Image image = new Image(byteArrayInputStream);
            film.setIMAGE(image);

            // shto filmi ne list
            films.add(film);
        }

        return films.get(0);
    }


    /**
     * Vetem per adminstratorin
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void deleteFilm(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Dëshiron ta fshish këtë film?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {


        } else {
            return;
        }
    }

    @FXML
    public void goToBookingScene(ActionEvent event) throws IOException {
        Session.setShfaqeButtonNeRezervimiFilmit(true);
        Session.setShfaqeDashboardButton(true);
        Stage stage = (Stage) this.anchorPaneId.getScene().getWindow();
        StageUtil.createStage(stage, "rezervimi-view.fxml", "images/kino-icon.png", "");
    }

    @FXML
    public void backToDashboard(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.anchorPaneId.getScene().getWindow();
        StageUtil.createStage(stage, "main-panel-view.fxml", "images/kino-icon.png", "");
    }
}
