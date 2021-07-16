module bd.ac.seu {
    requires javafx.controls;
    requires javafx.fxml;

    opens bd.ac.seu to javafx.fxml;
    exports bd.ac.seu;
}