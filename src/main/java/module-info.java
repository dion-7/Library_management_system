module assignment.thereadingroom {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    exports assignment.thereadingroom;
    exports assignment.thereadingroom.controller;

    opens assignment.thereadingroom to javafx.fxml;
    opens assignment.thereadingroom.controller to javafx.fxml;

}