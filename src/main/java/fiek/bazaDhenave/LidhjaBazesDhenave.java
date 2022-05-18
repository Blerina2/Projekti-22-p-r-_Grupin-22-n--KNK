package fiek.bazaDhenave;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class LidhjaBazesDhenave {
    private Connection connection;
    private static LidhjaBazesDhenave dbc;

    private LidhjaBazesDhenave() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/kino", "root", "");
            System.out.print("Database is connected !");
        } catch (Exception ex) {
            Logger.getLogger(LidhjaBazesDhenave.class.getName()).log(SEVERE, null, ex);
        }
    }

    public static LidhjaBazesDhenave getDatabaseConnection() {
        if (dbc == null) {
            dbc = new LidhjaBazesDhenave();
        }
        return dbc;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        new LidhjaBazesDhenave();
    }
}
