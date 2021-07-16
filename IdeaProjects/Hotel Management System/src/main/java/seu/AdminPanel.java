package seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class AdminPanel {

    @FXML
    private void logoutButton() throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    private void addRoomButton() throws IOException {
        App.setRoot("addroom");
    }

    @FXML
    private void createUserButton() throws IOException {
        App.setRoot("createuser");
    }
}