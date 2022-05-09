module com.example.blerinakinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
     requires com.almasb.fxgl.all;
    requires java.logging;
    requires java.sql;
    requires java.desktop;
    requires de.jensd.fx.glyphs.materialicons;

    exports kinema.fiek;
    opens kinema.fiek to javafx.fxml;
    opens kinema.fiek.view to javafx.fxml;
}