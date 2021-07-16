module seu.edu.bd {
    requires javafx.controls;
    requires javafx.fxml;

    opens seu.edu.bd to javafx.fxml;
    exports seu.edu.bd;
}