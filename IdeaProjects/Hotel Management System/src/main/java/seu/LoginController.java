package seu;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label textLabel;

    private final String DB_URL = "jdbc:mysql://localhost/hotel_reservation";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public LoginController() {
        passwordField = new TextField();
        usernameField = new TextField();
        textLabel = new Label();
    }

    @FXML
    public void keyReleased(){
        textLabel.setText("");
    }

    @FXML
    private void loginButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        getConnection();
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                App.setRoot("homepage");
            }
            else {
                textLabel.setText("Incorrect username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection ok!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
