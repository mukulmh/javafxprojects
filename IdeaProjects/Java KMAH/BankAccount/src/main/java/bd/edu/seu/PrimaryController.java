package bd.edu.seu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML
    private TextField numberField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField balanceField;
    @FXML
    private TextArea addressArea;

    public PrimaryController(){
        numberField = new TextField();
        nameField = new TextField();
        balanceField = new TextField();
        addressArea = new TextArea();
    }


    @FXML
    private void createAction() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberField.setText("12345");
        nameField.setText("John Doe");
        addressArea.setText("Nikunja-2\nDhaka, Bangladesh");
        balanceField.setText("50000");
    }
}
