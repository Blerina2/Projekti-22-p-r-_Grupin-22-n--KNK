package kinema.fiek.ndihmes;

import kinema.fiek.entity.Film;
import kinema.fiek.entity.Kino;
import kinema.fiek.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private static User user;
    private static Film film;

    private static List<Film> filmList;

    private static List<Kino> kinoList;

    public static List<Kino> getKinoList() {
        return kinoList;
    }

    public static void setKinoList(List<Kino> kinoList) {
        Session.kinoList = kinoList;
    }

    private static List<String> vendetZgjedhura = new ArrayList<>();

    public static List<String> getVendetZgjedhura() {
        return vendetZgjedhura;
    }

    public static void setVendetZgjedhuraList(List<String> vendetZgjedhur) {
        vendetZgjedhura = vendetZgjedhur;
    }

    public static void shotVendinZgjedhurList(String vendetZgjedhur) {
        vendetZgjedhura.add(vendetZgjedhur);
    }

    public static List<Film> getFilmList() {
        return filmList;
    }

    public static void setFilmList(List<Film> filmList) {
        Session.filmList = filmList;
    }

    public static boolean isSetShfaqeDashboardButton() {
        return setShfaqeDashboardButton;
    }

    public static void setSetShfaqeDashboardButton(boolean setShfaqeDashboardButton) {
        Session.setShfaqeDashboardButton = setShfaqeDashboardButton;
    }

    private static boolean shfaqeButtonNeRezervimiFilmit;

    private static boolean setShfaqeDashboardButton;

    public static boolean isShfaqeDashboardButton() {
        return setShfaqeDashboardButton;
    }

    public static void setShfaqeDashboardButton(boolean setShfaqeDashboardButton) {
        Session.setShfaqeDashboardButton = setShfaqeDashboardButton;
    }

    public static boolean isShfaqeButtonNeRezervimiFilmit() {
        return shfaqeButtonNeRezervimiFilmit;
    }

    public static void setShfaqeButtonNeRezervimiFilmit(boolean shfaqeButtonNeRezervimiFilmit) {
        Session.shfaqeButtonNeRezervimiFilmit = shfaqeButtonNeRezervimiFilmit;
    }

    public static User getUser() {
        return Session.user;
    }

    public static void setUser(User user) {
        Session.user = user;
    }

    public static Film getFilm() {
        return Session.film;
    }

    public static void setFilm(Film film) {
        Session.film = film;
    }
}
