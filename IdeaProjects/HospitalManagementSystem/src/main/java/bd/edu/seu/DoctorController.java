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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField roomField;
    @FXML private TextField timeField;
    @FXML private TextField feeField;
    @FXML private TextField phoneField;
    @FXML private Label statusLabel;

    @FXML private TableView<Doctor> doctorTableView;
    @FXML private TableColumn<Doctor,String> idColumn;
    @FXML private TableColumn<Doctor,String> nameColumn;
    @FXML private TableColumn<Doctor,String> roomColumn;
    @FXML private TableColumn<Doctor,String> timeColumn;
    @FXML private TableColumn<Doctor,String> feeColumn;
    @FXML private TableColumn<Doctor,String> phoneColumn;

    private ObservableList<Doctor> doctors;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public DoctorController() {
        idField = new TextField();
        nameField = new TextField();
        roomField = new TextField();
        timeField = new TextField();
        feeField = new TextField();
        phoneField = new TextField();
        statusLabel = new Label();

        doctorTableView = new TableView<>();
        idColumn = new TableColumn<>();
        nameColumn = new TableColumn<>();
        roomColumn = new TableColumn<>();
        timeColumn = new TableColumn<>();
        feeColumn = new TableColumn<>();
        phoneColumn = new TableColumn<>();

        doctors = FXCollections.observableArrayList();
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

    @FXML
    private void handleSelect(){
        Doctor selectedItems =doctorTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(Doctor selectedItems){
        idField.setText("" + selectedItems.getId());
        nameField.setText(selectedItems.getName());
        roomField.setText("" + selectedItems.getRoomNo());
        timeField.setText(selectedItems.getVisitingHour());
        feeField.setText("" + selectedItems.getFee());
        phoneField.setText(selectedItems.getPhoneNo());
    }

    @FXML
    private void clickAdd(){
        String id = idField.getText();
        String name = nameField.getText();
        int room = Integer.parseInt(roomField.getText());
        String time = timeField.getText();
        int fee = Integer.parseInt(feeField.getText());
        String phone = phoneField.getText();

        Doctor doctorInfo = new Doctor(id,name,room,time,fee,phone);
        if(storeData(doctorInfo) != null){
            doctors.add(doctorInfo);
            statusLabel.setText("Doctor added successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't add doctor!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickUpdate(){
        String name = nameField.getText();
        int roomNo = Integer.parseInt(roomField.getText());
        String time = timeField.getText();
        int fee = Integer.parseInt(feeField.getText());
        String phoneNo = phoneField.getText();
        String id = idField.getText();

        Doctor doctorInfo = new Doctor(id,name,roomNo,time,fee,phoneNo);
        if(updateData(doctorInfo) != null){
        doctors.add(doctorInfo);
        statusLabel.setText("Doctor updated successfully!");
        clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void clickDelete(){
        int fee = Integer.parseInt(feeField.getText());
        int room = Integer.parseInt(roomField.getText());
        String time = timeField.getText();
        String id = idField.getText();
        String phone = phoneField.getText();
        String name = nameField.getText();

        Doctor doctorInfo = new Doctor(id,name,room,time,fee,phone);
        deleteData(doctorInfo);
        //doctors.add(doctorInfo);
        clearForm();
        statusLabel.setText("Doctor deleted successfully!");
    }

    private void clearForm(){
        idField.setText("");
        nameField.setText("");
        roomField.setText("");
        timeField.setText("");
        feeField.setText("");
        phoneField.setText("");
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

    private Doctor storeData(Doctor doctor) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO doctor VALUES(\"%s\", \"%s\", %d, \"%s\", %d, \"%s\")",
            doctor.getId(),
            doctor.getName(),
            doctor.getRoomNo(),
            doctor.getVisitingHour(),
            doctor.getFee(),
            doctor.getPhoneNo());
            System.out.println(query);
            statement.executeUpdate(query);

            return doctor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Doctor updateData(Doctor doctor){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE doctor SET name = \"%s\",room_no = %d,visiting_hour = \"%s\", " +
                            "visiting_fee = %d, phone_no = \"%s\" where id = \"%s\"",
            doctor.getName(),
            doctor.getRoomNo(),
            doctor.getVisitingHour(),
            doctor.getFee(),
            doctor.getPhoneNo(),
            doctor.getId());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data updated!");

            return doctor;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Doctor deleteData(Doctor doctor){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM doctor where id = \"%s\"",
                    doctor.getId());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Doctor deleted!");

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Doctor> retrieveData(){
        Connection connection = getConnection();
        List<Doctor> doctorList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM doctor";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String  id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int room = resultSet.getInt("room_no");
                String time = resultSet.getString("visiting_hour");
                int fee = resultSet.getInt("visiting_fee");
                String phone = resultSet.getString("phone_no");

                Doctor doctor = new Doctor(id,name,room,time,fee,phone);
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  doctorList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("visitingHour"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        List<Doctor> doctorInfoList = retrieveData();
        doctors.addAll(doctorInfoList);
        doctorTableView.setItems(doctors);
    }
}