module bd.edu.seu {
    requires javafx.controls;
    requires javafx.fxml;

    opens bd.edu.seu to javafx.fxml;
    exports bd.edu.seu;
}