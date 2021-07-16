module seu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens seu to javafx.fxml;
    exports seu;
}