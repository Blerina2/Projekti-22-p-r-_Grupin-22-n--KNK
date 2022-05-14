package fiek.bazaDhenave.dto;

import fiek.LidhjaBazesDhenave;
import fiek.entity.Film;
import javafx.scene.image.Image;
import kinema.fiek.bazaDhenave.LidhjaBazesDhenave;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilmDTO {


    private static Connection connection;

    static {
        LidhjaBazesDhenave dbc = LidhjaBazesDhenave.getDatabaseConnection();
        connection = dbc.getConnection();
    }

    /**
     * @param objects qe perban reshtat e tables movie
     * @return list me objekti te filmave.
     */
    public static List<Film> convertoNeObjekteFilmit(List<Object[]> objects) {
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

            film.setKinoID((int) object[9]);

            // shto filmi ne list
            films.add(film);
        }
        // ne fund sortoj permese titles (a-z)
        return films.stream().sorted(Comparator.comparing(Film::getTitle)).collect(Collectors.toList());
    }

    /**
     * load all films from database.
     *
     * @return a list of film object.
     */
    public static List<Film> loadFilmatPrejDB() {
        List<Film> films = new ArrayList<>();
        try {
            PreparedStatement ps;
            String query = "select ID, TITLE, PERSHKRIMI, TRAILER, STARTDATE, ENDATE, TIME1, TIME2, IMAGE, KINO_ID from movies";
            ps = connection.prepareStatement(query);
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

            return FilmDTO.convertoNeObjekteFilmit(objects);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;
    }
}