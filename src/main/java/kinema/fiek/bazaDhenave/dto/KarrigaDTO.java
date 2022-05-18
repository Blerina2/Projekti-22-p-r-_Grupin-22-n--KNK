package kinema.fiek.bazaDhenave.dto;

import javafx.scene.control.Alert;
import javafx.stage.Window;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;
import kinema.fiek.ndihmes.PopUpInformacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * query ne tablen KINO dhe KARRIGE
 * DAO := Data Access Object
 */
public class KarrigaDTO {

    private static Connection connection;

    static {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        connection = dbc.getConnection();
    }


    public static int rezervojKarragen(String karriage, int userId, String dataRezervimit, String kohaFilmit, String oraFilmit, String kino_id, Window window) {
        try {
            PreparedStatement ps;
            String query = "insert into karrige(NUMRI_KARRIGES, USER_ID, DATA_REZERVIMIT, KOHA_FILMIT, ORA_FILMIT, KINO_ID) VALUES (?,?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, karriage);
            ps.setString(2, String.valueOf(userId));
            ps.setString(3, dataRezervimit);
            ps.setString(4, kohaFilmit);
            ps.setString(5, oraFilmit);
            ps.setString(6, kino_id);
            if (ps.executeUpdate() > 0) {
//                PopUpInformacion.showAlert(Alert.AlertType.INFORMATION, window, "Information",
//                        "Ju jeni regjistruar me sukses karriagen");
            } else {
                PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                        "Dicka shkoi keq.");
                return 0;
            }

        } catch (SQLException ex) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Dicka shkoi keq." + ex.getMessage());
            return 0;
        }
        return 1;
    }
}