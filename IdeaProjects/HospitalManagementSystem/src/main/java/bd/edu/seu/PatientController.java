package bd.edu.seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField phoneNoField;
    @FXML private TextArea addressArea;
    @FXML private TextField assignedToField;
    @FXML private DatePicker datePicker;
    @FXML private Label statusLabel;

    @FXML private TableView<Patient> patientTableView;
    @FXML private TableColumn<Patient,String> idColumn;
    @FXML private TableColumn<Patient,String> nameColumn;
    @FXML private TableColumn<Patient,String> phoneColumn;
    @FXML private TableColumn<Patient,String> addressColumn;
    @FXML private TableColumn<Patient,String> assignColumn;
    @FXML private TableColumn<Patient,String> dateColumn;

    private ObservableList<Patient> patients;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public PatientController() {
        idField = new TextField();
        nameField = new TextField();
        phoneNoField = new TextField();
        addressArea = new TextArea();
        assignedToField = new TextField();
        datePicker = new DatePicker();
        statusLabel = new Label();

        patientTableView = new TableView<>();
        idColumn = new TableColumn<>();
        nameColumn = new TableColumn<>();
        phoneColumn = new TableColumn<>();
        addressColumn = new TableColumn<>();
        assignColumn = new TableColumn<>();
        dateColumn = new TableColumn<>();

        patients = FXCollections.observableArrayList();
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

    @FXML
    private void keyReleased(){
        statusLabel.setText("");
    }

    @FXML
    private void clickBack() throws IOException {
        App.setRoot("admin");
    }

    @FXML
    private void clickClear(){
        clearForm();
    }

    private void clearForm(){
        idField.setText("");
        nameField.setText("");
        addressArea.setText("");
        phoneNoField.setText("");
        assignedToField.setText("");
        datePicker.setValue(null);
    }

    @FXML
    private void handleSelect(){
        Patient selectedItems = patientTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(Patient selectedItems){
        idField.setText(""+ selectedItems.getId());
        nameField.setText(selectedItems.getName());
        phoneNoField.setText(selectedItems.getPhoneNo());
        assignedToField.setText(""+ selectedItems.getAssignedTo());
        addressArea.setText(selectedItems.getAddress());
        datePicker.setValue(selectedItems.getDate());
    }

    @FXML
    private void clickAdd(){
        String id = idField.getText();
        String name = nameField.getText();
        String phone = phoneNoField.getText();
        String assigned = assignedToField.getText();
        String address  = addressArea.getText();
        LocalDate date = datePicker.getValue();

        Patient patientInfo = new Patient(id,name,phone,address,assigned,date);
        if(storeData(patientInfo) != null){
            patients.add(patientInfo);
            statusLabel.setText("Patient added successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't add patient!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickUpdate(){
        String id = idField.getText();
        String name = nameField.getText();
        String phone = phoneNoField.getText();
        String assigned = assignedToField.getText();
        String address  = addressArea.getText();
        LocalDate date = datePicker.getValue();

        Patient patientInfo = new Patient(id,name,phone,address,assigned,date);
        if(updateData(patientInfo) != null){
            patients.add(patientInfo);
            statusLabel.setText("Patient updated successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update patient!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickDelete(){
        String id = idField.getText();
        String name = nameField.getText();
        String phone = phoneNoField.getText();
        String assigned = assignedToField.getText();
        String address  = addressArea.getText();
        LocalDate date = datePicker.getValue();

        Patient patientInfo = new Patient(id,name,phone,address,assigned,date);
        deleteData(patientInfo);
            statusLabel.setText("Patient deleted successfully!");
            clearForm();
    }

    private Patient storeData(Patient patient) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO patient VALUES(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    patient.getId(),
                    patient.getName(),
                    patient.getPhoneNo(),
                    patient.getAddress(),
                    patient.getAssignedTo(),
                    patient.getDate());
            System.out.println(query);
            statement.executeUpdate(query);

            return patient;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Patient updateData(Patient patient){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE patient SET name = \"%s\",phone_no = \"%s\",address = \"%s\", " +
                            "assigned_to = \"%s\", date = \"%s\" where id = \"%s\"",
                    patient.getName(),
                    patient.getPhoneNo(),
                    patient.getAddress(),
                    patient.getAssignedTo(),
                    patient.getDate(),
                    patient.getId());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data updated!");

            return patient;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Patient deleteData(Patient patient){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM patient where id = \"%s\"",
                    patient.getId());

            System.out.println(query);
            statement.executeUpdate(query);

            return patient;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> retrieveData(){
        Connection connection = getConnection();
        List<Patient> patientList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM patient";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String phoneNo = resultSet.getString("phone_no");
                String address = resultSet.getString("address");
                String  assigned = resultSet.getString("assigned_to");
                String date = resultSet.getString("date");

                Patient patient = new Patient(id,name,phoneNo,address,assigned,LocalDate.parse(date));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  patientList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        assignColumn.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        List<Patient> patientInfoList = retrieveData();
        patients.addAll(patientInfoList);
        patientTableView.setItems(patients);
    }
}