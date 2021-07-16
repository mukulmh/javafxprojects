package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class AppInfo {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}