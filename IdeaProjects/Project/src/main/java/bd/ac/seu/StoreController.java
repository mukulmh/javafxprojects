package bd.ac.seu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class StoreController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField batchField;
    @FXML
    private TextField binField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker dateField;

    public StoreController() {
        idField = new TextField();
        nameField = new TextField();
        batchField = new TextField();
        binField = new TextField();
        categoryField = new TextField();
        quantityField = new TextField();
        dateField = new DatePicker();
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void handleStoreButton() throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure want to add this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String batch = batchField.getText();
            int bin = Integer.parseInt(binField.getText());
            String category = categoryField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            LocalDate date = dateField.getValue();

            Parts part = new Parts( id, name, category, batch, bin, quantity,date);

            System.out.println(part);

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("The Part is been added!");

            alert1.showAndWait();
            App.setRoot("secondary");
        }

    }
}
