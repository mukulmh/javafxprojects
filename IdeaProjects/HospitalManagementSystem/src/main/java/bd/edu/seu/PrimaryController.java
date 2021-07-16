package bd.edu.seu;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField typeField;
    @FXML private Label textLabel;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public PrimaryController() {
        passwordField = new TextField();
        usernameField = new TextField();
        typeField = new TextField();
        textLabel = new Label();
    }

    @FXML
    public void keyReleased(){
        textLabel.setText("");
    }

    @FXML
    private void selectAdminButton(){
        typeField.setText("Admin");
    }

    @FXML
    private void selectDoctorButton(){
        typeField.setText("Doctor");
    }

    @FXML
    private void selectReceptionistButton(){
        typeField.setText("Receptionist");
    }

    @FXML
    private void loginButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String type = typeField.getText();
        String admin = "Admin";
        String receptionist = "Receptionist";
        getConnection();
        String sql = "SELECT * FROM user WHERE username=? AND password=? AND user_type=?";
        try {
            PreparedStatement pst = getConnection().prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3,type);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                App.setRoot("admin");
                /*if(type == admin)
                    App.setRoot("admin");*/
            }
            else {
                textLabel.setText("Username or password did not matched!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connected to DB!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
