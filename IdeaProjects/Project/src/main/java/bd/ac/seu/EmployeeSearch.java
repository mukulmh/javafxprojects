package bd.ac.seu;

import javafx.fxml.FXML;

import java.io.IOException;

public class EmployeeSearch {
    @FXML
    private void handleBackButton() throws IOException{
        App.setRoot("secondary");
    }
}
