package bd.edu.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class InventoryController {

    @FXML
    private void clickBack() throws IOException {
        App.setRoot("admin");
    }
}