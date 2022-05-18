package kinema.fiek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kinema.fiek.entity.UserRole;
import kinema.fiek.ndihmes.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PanelIKryesorController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private List<Button> menus = new ArrayList<>();
    ;

    @FXML
    private AreaChart<?, ?> chartPurchase;

    @FXML
    private AreaChart<?, ?> chartSale;

    @FXML
    private LineChart<?, ?> chartReceipt;

    @FXML
    private Button administrator;

    @FXML
    private Button editoFilma;

    @FXML
    private Button shtoAdminstrator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shfaqeButoniPerAdmin();
    }

    private void shfaqeButoniPerAdmin() {
        if (UserRole.isSystemmanger(Session.getUser().getUserRole())) {
            this.administrator.setVisible(true);
            this.editoFilma.setVisible(true);
            this.shtoAdminstrator.setVisible(true);
        } else if (UserRole.isAdminstrator(Session.getUser().getUserRole())) {
            this.administrator.setVisible(true);
            this.editoFilma.setVisible(true);
            this.shtoAdminstrator.setVisible(false);
        } else if (UserRole.isKlienti(Session.getUser().getUserRole())) {
            this.administrator.setVisible(false);
            this.editoFilma.setVisible(false);
            this.shtoAdminstrator.setVisible(false);
        }
    }

    private void changeButtonBackground(ActionEvent e) {

        Button clickedButton = (Button) e.getSource();
        this.menus.add(clickedButton);
        Iterator<Button> iteratorMenus = menus.iterator();

        while (iteratorMenus.hasNext()) {
            Button OtherButton = iteratorMenus.next();
            if (clickedButton == OtherButton) {
                clickedButton.setStyle("-fx-text-fill:white;-fx-background-color:black;-fx-background-radius: 8, 7, 6;-fx-background-insets: 0, 1, 2;" +
                        "-fx-effect: dropshadow( gaussian , #99efff, 10, 0.0, 0, 1);");
            } else {
                if (OtherButton != null) {
                    OtherButton.setStyle("-fx-text-fill:white;-fx-background-color:transparent;  -fx-font-size: 16px;");
                }
            }
        }
    }

    @FXML
    private void clear() {
        borderPane.setCenter(null);
    }

    @FXML
    private void loadFXML(String fileName) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("view/" + fileName + ".fxml"));
            borderPane.setCenter(parent);

        } catch (IOException ex) {
            Logger.getLogger(PanelIKryesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close() throws IOException {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        StageUtil.createStage(stage, "login-view.fxml", "images/kino-icon.png", "Identifikimi i përdoruesit");
    }

    @FXML
    private void loadShikoniRezerviminView(ActionEvent e) {
        loadFXML("shikoni-rezervimin-view");
        changeButtonBackground(e);
    }

    @FXML
    private void loadFilmatAktualView(ActionEvent e) {
        loadFXML("filmat-aktual-view");
        changeButtonBackground(e);
    }

    @FXML
    private void loadAdministratorView(ActionEvent e) {
        loadFXML("administrator-view");
        changeButtonBackground(e);
    }

    @FXML
    private void loadEditoFilmatView(ActionEvent e) {
        loadFXML("edito-filmat-view");
        changeButtonBackground(e);
    }

    @FXML
    private void loadShtoAdminstratorView(ActionEvent e) {
        loadFXML("shto-admin-view");
        changeButtonBackground(e);
    }

    @FXML
    private void loadRezervofilminView(ActionEvent e) {
        Session.setShfaqeButtonNeRezervimiFilmit(false);
        Session.setShfaqeDashboardButton(false);
        loadFXML("rezervimi-view");
        changeButtonBackground(e);
    }

    @FXML
    private void loadHomeView(ActionEvent e) {
        loadFXML("home-view");
        changeButtonBackground(e);
    }
}