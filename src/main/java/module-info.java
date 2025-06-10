module com.example.snapshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.snapshop to javafx.fxml;
    exports com.example.snapshop;

    opens com.example.snapshop.controller to javafx.fxml;
}