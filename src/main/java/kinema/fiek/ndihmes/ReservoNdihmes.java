package kinema.fiek.ndihmes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kinema.fiek.entity.Film;

import java.util.List;

public class ReservoNdihmes {

    public static ObservableList<String> getFilmatPerComboBox() {
        List<Film> filmList = Session.getFilmList();
        // me duhet sepse javafx ComboBox kerkon qe elemente te jen ne ObservableList
        ObservableList<String> filmTitles = FXCollections.observableArrayList();

        for (Film film : filmList) {
            filmTitles.add(film.getTitle());
        }
        return filmTitles;
    }

    public static ObservableList<String> getKohatFilmit(Film film) {
        // me duhet sepse javafx ComboBox kerkon qe elemente te jen ne ObservableList
        ObservableList<String> filmTitles = FXCollections.observableArrayList();
        filmTitles.add(film.getTime1());
        filmTitles.add(film.getTime2());
        return filmTitles;
    }
}
