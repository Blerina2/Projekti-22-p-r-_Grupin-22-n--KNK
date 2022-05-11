package fiek.bazaDhenave.dto.UserDTO.java;

import fiek.bazaDhenave.dto.FilmDTO;
import fiek.bazaDhenave.dto.KinoDTO;
import fiek.bazaDhenave.entity.Film;
import fiek.bazaDhenave.entity.Kino;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;
import kinema.fiek.entity.Rezervim;
import kinema.fiek.entity.User;
import kinema.fiek.ndihmes.PopUpInformacion;
import kinema.fiek.ndihmes.Session;
import kinema.fiek.ndihmes.UserNdihmes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private static Connection connection;

    static {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        connection = dbc.getConnection();
    }


    public static User getUser(String username, String password, Window window) {

        try {
            String query = "select  ID, FIRST_NAME, LAST_NAME, EMAIL, USER_NAME, PASSWORD, USER_ROLE from users WHERE user_name = ? and password = ?";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();


            if (resultSet.next()) {
                List<Object[]> objects = new ArrayList<>();
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = resultSet.getObject(i);
                }
                objects.add(values);

                List<User> users = UserNdihmes.convertoNeObjekteTeUser(objects);
                User user = users.get(0);
                // Gjate perdorimit te appit klient eshte i nevojshme te perdoret
                Session.setUser(user);
                // Gjate perdorimit te appit klienti i ka filma gati
                List<Film> films = FilmDTO.loadFilmatPrejDB();
                Session.setFilmList(films);
                List<Kino> kinos = KinoDTO.getALlKinos();
                Session.setKinoList(kinos);
                return user;
            }
        } catch (SQLException ex) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Problem me bazen e dhenave");
        }

        return null;
    }

    public static User getRezervimet(int userId, Window window) {

        try {
            String query = "select ka.NUMRI_KARRIGES, ka.DATA_REZERVIMIT, ka.KOHA_FILMIT, ka.ORA_FILMIT, ki.KINO_NAME, ki.ID\n" +
                    "from users u join karrige ka on u.ID = ka.USER_ID join movies m on ka.KINO_ID = m.KINO_ID join kino ki on ki.ID =ka.KINO_ID where u.ID=? order by ka.KINO_ID asc;";
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(userId));

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            final int columnCount = resultSetMetaData.getColumnCount();

            List<Rezervim> rezervimList = new ArrayList<>();

            while (resultSet.next()) {
                List<Object[]> objects = new ArrayList<>();
                Object[] values = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    values[i - 1] = resultSet.getObject(i);
                }
                objects.add(values);

                rezervimList.add(UserNdihmes.converToRezervimetObjekt(objects));
            }
            User user = Session.getUser();
            user.setRezervim(rezervimList);
            // Gjate perdorimit te appit klient eshte i nevojshme te perdoret
            Session.setUser(user);
            return user;
        } catch (SQLException ex) {
            PopUpInformacion.showAlert(Alert.AlertType.ERROR, window, "Gabim",
                    "Problem me bazen e dhenave");
        }
        return null;
    }
}