package fiek.bazaDhenave.dto;

import fiek.entity.Kino;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;
import kinema.fiek.ndihmes.PopUpInformacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KinoDTO {

    private static Connection connection;

    static {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        connection = dbc.getConnection();
    }

    public static boolean akutalizoKinon(int kinoId, String karriageList, int karraiageveReservuar, Window window) {
        try {
            PreparedStatement ps;
            String query = "update kino Set KARRIGE_LIST = ?, KARRIAGEVE_RESERVUAR = ? where ID=?";
            ps = connection.prepareStatement(query);
            ps.setString(1, karriageList);
            ps.setString(2, String.valueOf(karraiageveReservuar));
            ps.setString(3, String.valueOf(kinoId));
            if (ps.executeUpdate() > 0) {
//                PopUpInformacion.showAlert(Alert.AlertType.INFORMATION, window, "Information",
//                        "Ju jeni regjistruar me sukses ne kino");
            } else {
                PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                        "Dicka shkoi keq.");
                return false;
            }

        } catch (SQLException ex) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Dicka shkoi keq.");
            return false;
        }
        return true;
    }


    public static List<Kino> getALlKinos() {
        List<Kino> kinos = new ArrayList<>();
        try {
            String query = "select ID, KINO_NAME,SASIA_KARRIAGEVE, KARRIGE_LIST, KARRIAGEVE_RESERVUAR from Kino";
            PreparedStatement ps = connection.prepareStatement(query);
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

            return convertoNeObjekteKino(objects);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kinos;
    }

    private static List<Kino> convertoNeObjekteKino(List<Object[]> objects) {
        List<Kino> kinos = new ArrayList<>();

        for (Object[] object : objects) {
            Kino kino = new Kino();
            kino.setKinoID((int) object[0]);
            kino.setKinoName((String) object[1]);
            kino.setSasiaKarriageve((int) object[2]);
            kino.setKarrigeList((String) object[3]);
            kino.setKarriageveReservuar((int) object[4]);
            // shto filmi ne list
            kinos.add(kino);
        }
        return kinos;
    }
}