package kinema.fiek;

import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kinema.fiek.bazaDhenave.dto.FilmDTO;
import kinema.fiek.bazaDhenave.dto.KarrigaDAO;
import kinema.fiek.bazaDhenave.dto.KinoDTO;
import kinema.fiek.entity.ColorSeat;
import kinema.fiek.entity.Film;
import kinema.fiek.entity.Kino;
import kinema.fiek.ndihmes.PopUpInformacion;
import kinema.fiek.ndihmes.ReservoNdihmes;
import kinema.fiek.ndihmes.Session;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReservoController implements Initializable {


    /**
     * Ne kete dependency (bilotek) i kemi marre icon me duke sikur karriga
     * <p>
     * https://github.com/code-mc/material-icon-lib
     * https://materialdesignicons.com/
     */

    @FXML
    AnchorPane anchorPaneBookingId;
    @FXML
    Button logout;

    int bookedSeatsCount;

    @FXML
    static Stage stage;
    @FXML
    GridPane gridSeats;
    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox<String> filmDropDownList, timeDropDownList;
    @FXML
    Label bookedSeatsLabel, availableSeatsLabel, totalSeatsLabel;

    @FXML
    Button dashboard;

    @FXML
    Text filmiText;

    @FXML
    Text rezervimiFilmitTitle;

    /**
     * ulset ne kino
     */
    @FXML
    MaterialIconView A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.setFilmList(FilmDTO.loadFilmatPrejDB());
        shfaqeGjeratRezerviminFilmit();
        shfaqeGjeratPerComboBox();
        colorSeats();

    }

    @FXML
    private void logOut() throws IOException {
        Stage stage = (Stage) anchorPaneBookingId.getScene().getWindow();
        stage.close();
        StageUtil.createStage(stage, "login-view.fxml", "images/kino-icon.png", "Identifikimi i përdoruesit");
    }

    /**
     * Neser perdoures e selected seating
     *
     * @param mouseEvent
     */
    public void selectSeat(MouseEvent mouseEvent) {
        Node mouseEventSource = (Node) mouseEvent.getSource();
        if (mouseEventSource.getStyle().equals(ColorSeat.RED_SEAT)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vendi tashmë " + mouseEventSource.getId() + " është i rezervuar!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();

                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            }
        } else {

            if (mouseEventSource.getStyle().equals(ColorSeat.YELLOW_SEAT)) {
                mouseEventSource.setStyle(ColorSeat.GREEN_SEAT);
                Session.getVendetZgjedhura().remove(mouseEventSource.getId());
            } else {
                mouseEventSource.setStyle(ColorSeat.YELLOW_SEAT);
                Session.shotVendinZgjedhurList(mouseEventSource.getId());
            }
        }
    }

    public void comboxDataSelected(ActionEvent actionEvent) {
    }

    public void comboBoxFilmsSelected(ActionEvent actionEvent) {
        this.resetToDefaultColor();
        colorSeats();
    }

    private void colorSeats() {
        String selectedItem = this.filmDropDownList.getSelectionModel().getSelectedItem();
        List<Film> films = Session.getFilmList().stream().filter(f -> f.getTitle().equals(selectedItem)).collect(Collectors.toList());

        if (!films.isEmpty()) {
            List<Kino> kinos = Session.getKinoList().stream().filter(k -> k.getKinoID() == films.get(0).getKinoID()).collect(Collectors.toList());
            Session.setFilm(films.get(0));
            this.filmDropDownList.getSelectionModel().select(Session.getFilm().getTitle());
            this.timeDropDownList.setItems(ReservoNdihmes.getKohatFilmit(Session.getFilm()));
            this.timeDropDownList.getSelectionModel().selectFirst();

            for (Node seatsChild : this.gridSeats.getChildren()) {
                if (kinos.get(0).getKarrigeList() != null) {
                    for (String ulsjaId : kinos.get(0).getKarrigeList().split(",")) {
                        if (ulsjaId.replace(" ", "").equals(seatsChild.getId())) {
                            seatsChild.setStyle(ColorSeat.RED_SEAT);
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void bookSeat(MouseEvent mouseEvent) {
        Session.setKinoList(KinoDTO.getALlKinos());

        if (this.datePicker.getValue() != null && this.timeDropDownList.getSelectionModel().getSelectedItem() != null && this.filmDropDownList.getSelectionModel().getSelectedItem() != null && !Session.getVendetZgjedhura().isEmpty()) {
            int insertuar = 0;
            for (String ulsjaSelektuar : Session.getVendetZgjedhura()) {
                int userId = Session.getUser().getUserId();
                Calendar now = Calendar.getInstance();
                String pattern = "MM-dd-yyyy";
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                // kontroll a eshte ulsja e reverzuar ne database
                boolean ulsjaNukEshteRezevuar = Session.getKinoList().stream()
                        .filter(kino -> Session.getFilm().getKinoID() == kino.getKinoID())
                        .filter(kino -> kino.getKarrigeList().contains(ulsjaSelektuar))
                        .collect(Collectors.toList()).isEmpty();

                if (ulsjaNukEshteRezevuar) {
                    insertuar += KarrigaDAO.rezervojKarragen(ulsjaSelektuar, userId, now.getTime().toString(),
                            dateFormatter.format(this.datePicker.getValue()), this.timeDropDownList.getSelectionModel().getSelectedItem(),
                            String.valueOf(Session.getFilm().getKinoID()), this.anchorPaneBookingId.getScene().getWindow());
                }
            }

            // Nese karriage jane rezervuar me sukses atehere te aktualisum ne kino
            if (insertuar == Session.getVendetZgjedhura().size()) {

                String[] splitVendetZgjedhuarUser = Session.getVendetZgjedhura().toString().split(",");
                boolean aktualizoKinon = false;
                for (Film film : Session.getFilmList()) {
                    Kino kinoAktualisu = new Kino();
                    if (film.getTitle().equals(this.filmDropDownList.getSelectionModel().getSelectedItem())) {
                        for (Kino kino : Session.getKinoList()) {
                            if (kino.getKinoID() == film.getKinoID()) {
                                for (String uljsa : splitVendetZgjedhuarUser) {
                                    uljsa = uljsa.replace("[", "");
                                    uljsa = uljsa.replace("]", "");

                                    if (kino.getKarrigeList() == null) {
                                        aktualizoKinon = true;
                                        kino.setKarrigeList(uljsa);
                                    } else {
                                        if (!kino.getKarrigeList().contains(uljsa)) {
                                            aktualizoKinon = true;
                                            kino.setKarrigeList(kino.getKarrigeList() + "," + uljsa);
                                        }
                                    }


                                }
                                ;
                                kinoAktualisu = kino;
                            }

                        }

                        if (aktualizoKinon) {
                            if (KinoDTO.akutalizoKinon(film.getKinoID(), kinoAktualisu.getKarrigeList(),
                                    Session.getVendetZgjedhura().size(),
                                    this.anchorPaneBookingId.getScene().getWindow())) {
                                Session.setKinoList(KinoDTO.getALlKinos());
                            }
                        }
                        this.colorSeats();
                        this.shfaqeGjeratPerComboBox();
                        Session.getVendetZgjedhura().clear();
                    }
                }

            } else {
                PopUpInformacion.showAlert(Alert.AlertType.ERROR, this.anchorPaneBookingId.getScene().getWindow(), "Gabim", "Dicka shkoi keq gjate aktualisimit te dhenat ne kino.");
                this.resetToDefaultColor();
                this.shfaqeGjeratPerComboBox();
            }
        } else {
            PopUpInformacion.showAlert(Alert.AlertType.CONFIRMATION, this.anchorPaneBookingId.getScene().getWindow(), "Informacion", "Fushat e tekstit të dates dhe filmit dhe te kohes nuk mund të jetë bosh. Gjithashtu selektoni nje karriage.");
            this.resetToDefaultColor();
            this.shfaqeGjeratPerComboBox();
            Session.getVendetZgjedhura().clear();
        }
    }

    private void shfaqeGjeratPerComboBox() {

        if (Session.getFilm() == null) {
            Session.setFilm(Session.getFilmList().get(0));
        }

        this.filmDropDownList.setItems(ReservoNdihmes.getFilmatPerComboBox());
        this.filmDropDownList.getSelectionModel().select(Session.getFilm().getTitle());
        this.timeDropDownList.setItems(ReservoNdihmes.getKohatFilmit(Session.getFilm()));
        this.timeDropDownList.getSelectionModel().selectFirst();

        List<Kino> kinos = Session.getKinoList().stream().filter(k -> k.getKinoID() == Session.getFilm().getKinoID()).collect(Collectors.toList());

        if (kinos.isEmpty()) {
            totalSeatsLabel.setText("Totali i vendeve: " + 45);
            bookedSeatsLabel.setText("Vendet e rezervuara: " + 0);
            availableSeatsLabel.setText("Vendet e disponueshme: " + 45);
        } else {
            totalSeatsLabel.setText("Totali i vendeve: " + kinos.get(0).getSasiaKarriageve());
            bookedSeatsLabel.setText("Vendet e rezervuara: " + kinos.get(0).getKarriageveReservuar());
            availableSeatsLabel.setText("Vendet e disponueshme: " + (kinos.get(0).getSasiaKarriageve() -
                    kinos.get(0).getKarriageveReservuar()));
        }


    }

    private void shfaqeGjeratRezerviminFilmit() {
        if (Session.isShfaqeButtonNeRezervimiFilmit()) {
            this.logout.setVisible(true);
            this.dashboard.setVisible(true);
            this.rezervimiFilmitTitle.setVisible(true);

        } else {
            this.logout.setVisible(false);
            this.dashboard.setVisible(false);
            this.rezervimiFilmitTitle.setVisible(false);
        }
    }


    @FXML
    public void shkoTekDashboardi(MouseEvent mouseEvent) throws IOException {
        this.resetToDefaultColor();
        Stage stage = (Stage) this.anchorPaneBookingId.getScene().getWindow();
        StageUtil.createStage(stage, "main-panel-view.fxml", "images/kino-icon.png", "");
    }

    private void resetToDefaultColor() {
        for (Node child : this.gridSeats.getChildren()) {
            child.setStyle(ColorSeat.GREEN_SEAT);
        }
    }
}
