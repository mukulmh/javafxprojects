package bd.ac.seu;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SecondaryController {

    @FXML
    private void handleLogoffButton() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            App.setRoot("primary");
        }
    }
    @FXML
    private void switchToStore() throws IOException {
        App.setRoot("storeParts");
    }
    @FXML
    private void switchToShipOut() throws IOException {
        App.setRoot("shipOut");
    }
    @FXML
    private void handleViewPartsButton() throws IOException {
        App.setRoot("parts");
    }
    @FXML
    private void handleDetailsButton() throws IOException{
        App.setRoot("details");
    }
    @FXML
    private void handleSearchButton() throws IOException{
        App.setRoot("search");
    }
}