package seu;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void logoutButton() throws IOException {
        App.setRoot("login");
    }
    @FXML
    private void reserveButton() throws IOException {
        App.setRoot("reservation");
    }
    @FXML
    private void roomsButton() throws IOException {
        App.setRoot("rooms");
    }
    @FXML
    private void customerButton() throws IOException {
        App.setRoot("customerinfo");
    }
    @FXML
    private void adminButton() throws IOException {
        App.setRoot("admin");
    }
}