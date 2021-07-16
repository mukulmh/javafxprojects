package bd.edu.seu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField roomField;
    @FXML
    private LocalDate fromField;
    @FXML
    private LocalDate toField;

    public PrimaryController(){
        nameField = new TextField();
        roomField = new TextField();
    }
}
