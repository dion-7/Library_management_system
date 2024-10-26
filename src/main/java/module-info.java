module assignment.thereadingroom {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    exports assignment.thereadingroom;
    exports assignment.thereadingroom.controller;
    exports assignment.thereadingroom.model;

    opens assignment.thereadingroom to javafx.fxml;
    opens assignment.thereadingroom.controller to javafx.fxml;
    opens assignment.thereadingroom.model to javafx.fxml;

}