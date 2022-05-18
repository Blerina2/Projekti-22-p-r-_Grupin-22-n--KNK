package kinema.fiek;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import kinema.fiek.bazaDhenave.dto.UserDto;
import kinema.fiek.entity.Rezervim;
import kinema.fiek.entity.Table;
import kinema.fiek.ndihmes.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ShikoniRezerviminController implements Initializable {

    @FXML
    private AnchorPane anchorPaneRezervimet;
    @FXML
    private TableView<Table> tableView;
    @FXML
    private TableColumn<Table, String> userName;
    @FXML
    private TableColumn<Table, String> numriKinos;
    @FXML
    private TableColumn<Table, String> kinoName;
    @FXML
    private TableColumn<Table, String> numriKarriges;
    @FXML
    private TableColumn<Table, String> dataRezervimit;
    @FXML
    private TableColumn<Table, String> kohaFilmit;
    @FXML
    private TableColumn<Table, String> oraFilmit;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        UserDto.getRezervimet(Session.getUser().getUserId(), null);
        if(this.userName!=null) {
            this.userName.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getUserName()));
            this.numriKinos.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getNumriKinos()));
            this.kinoName.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKinoName()));
            this.numriKarriges.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getNumriKarriges()));
            this.dataRezervimit.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getDataRezervimit()));
            this.kohaFilmit.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKohaFilmit()));
            this.oraFilmit.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getOraFilmit()));

            this.tableView.setItems(this.getRezervimObservableList());
        }
    }


    private ObservableList<Table> getRezervimObservableList() {
        ObservableList<Table> tableObservableList = FXCollections.observableArrayList();
        List<Table> tables = new ArrayList<>();

        for (Rezervim rezervim : Session.getUser().getRezervim()) {

            Table table = new Table(Session.getUser().getFirstName(),
                    String.valueOf(rezervim.getKino().getKinoID()),
                    rezervim.getKino().getKinoName(),
                    rezervim.getKarriage().getNumriKarriges(),
                    rezervim.getKarriage().getKohaFilmit(),
                    rezervim.getKarriage().getOraFilmit(),
                    rezervim.getKarriage().getDataRezervimit()
            );

            tables.add(table);


        }
        tableObservableList.addAll(tables.stream().sorted(Comparator.comparing(Table::getNumriKinos)).collect(Collectors.toList()));
        return tableObservableList;
    }
}
