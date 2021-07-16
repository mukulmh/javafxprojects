package bd.ac.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class ShipOutController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField batchField;
    @FXML
    private TextField binField;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker dateField;

    public ShipOutController() {
        idField = new TextField();
        nameField = new TextField();
        batchField = new TextField();
        binField = new TextField();
        quantityField = new TextField();
        dateField = new DatePicker();
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void handleConfirmButton() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Shipping out in process!");

            alert1.showAndWait();
            App.setRoot("secondary");

        }
    }
}

