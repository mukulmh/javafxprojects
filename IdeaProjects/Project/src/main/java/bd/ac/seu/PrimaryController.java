package bd.ac.seu;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;

    @FXML
    private void handleLoginButton() throws IOException {

        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();

        App.setRoot("secondary");
    }

}
