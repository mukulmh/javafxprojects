package bd.ac.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class detailsController {
    @FXML
    private void handleOkButton() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void handleClearButton() throws IOException {
        App.setRoot("secondary");
    }
}
