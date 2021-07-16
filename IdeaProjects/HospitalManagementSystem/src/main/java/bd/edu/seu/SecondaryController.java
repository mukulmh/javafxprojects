package bd.edu.seu;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SecondaryController {

    @FXML
    private void logOut() throws IOException {
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
    private void clickDoctor() throws IOException {
        App.setRoot("doctor");
    }

    @FXML
    private void clickRoom() throws IOException {
        App.setRoot("room");
    }

    @FXML
    private void clickStaff() throws IOException {
        App.setRoot("staff");
    }

    @FXML
    private void clickPatient() throws IOException {
        App.setRoot("patient");
    }

    @FXML
    private void clickBed() throws IOException {
        App.setRoot("bed");
    }

    @FXML
    private void clickAppointment() throws IOException {
        App.setRoot("appointment");
    }

    @FXML
    private void clickUser() throws IOException {
        App.setRoot("user");
    }

}