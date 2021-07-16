package bd.ac.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class PartsController {
    @FXML
    private void handleOkButton() throws IOException {
        App.setRoot("secondary");
    }
}
