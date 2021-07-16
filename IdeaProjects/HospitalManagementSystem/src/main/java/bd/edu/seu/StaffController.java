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

public class StaffController implements Initializable {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private  TextField roleField;
    @FXML private Label statusLabel;

    @FXML private TableView<Staff> staffTableView;
    @FXML private TableColumn<Staff,String> idColumn;
    @FXML private TableColumn<Staff,String> nameColumn;
    @FXML private TableColumn<Staff,String> phoneColumn;
    @FXML private TableColumn<Staff,String> roleColumn;

    private ObservableList<Staff> staffs;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public StaffController() {
        idField = new TextField();
        nameField = new TextField();
        phoneField = new TextField();
        roleField = new TextField();
        statusLabel = new Label();

        staffTableView = new TableView<>();
        idColumn = new TableColumn<>();
        nameColumn = new TableColumn<>();
        phoneColumn = new TableColumn<>();
        roleColumn = new TableColumn<>();

        staffs = FXCollections.observableArrayList();
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
        phoneField.setText("");
        roleField.setText("");
    }

    @FXML
    private void handleSelect(){
        Staff selectedItems =staffTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(Staff selectedItems){
        idField.setText(""+ selectedItems.getId());
        nameField.setText(selectedItems.getName());
        phoneField.setText(selectedItems.getPhone());
        roleField.setText(selectedItems.getRole());
    }

    @FXML
    private void clickAdd(){
        String id = idField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String role = roleField.getText();

        Staff staffInfo = new Staff(id,name,phone,role);
        if(storeData(staffInfo) != null){
            staffs.add(staffInfo);
            statusLabel.setText("Staff added successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't add staff!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickUpdate(){
        String  id = idField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String role = roleField.getText();

        Staff staffInfo = new Staff(id,name,phone,role);
        if(updateData(staffInfo) != null){
            staffs.add(staffInfo);
            statusLabel.setText("Staff updated successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update staff!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickDelete(){
        String id = idField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        String role = roleField.getText();

        Staff staffInfo = new Staff(id,name,phone,role);
        deleteData(staffInfo);
        statusLabel.setText("Staff deleted successfully!");
        clearForm();
         /*else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't delete room!");
            alert.showAndWait();
        }*/
    }

    private Staff storeData(Staff staff) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO staff VALUES(\"%s\", \"%s\", \"%s\", \"%s\")",
                    staff.getId(),
                    staff.getName(),
                    staff.getPhone(),
                    staff.getRole());

            System.out.println(query);
            statement.executeUpdate(query);

            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Staff updateData(Staff staff){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE staff SET name = \"%s\", phone_no = \"%s\", staff_role = \"%s\"" +
                            " where id = \"%s\"",
                    staff.getName(),
                    staff.getPhone(),
                    staff.getRole(),
                    staff.getId());

            System.out.println(query);
            statement.executeUpdate(query);

            return staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Staff deleteData(Staff staff){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM staff where id = \"%s\"",
                    staff.getId());

            System.out.println(query);
            statement.executeUpdate(query);

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Staff> retrieveData(){
        Connection connection = getConnection();
        List<Staff> staffList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM staff";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone_no");
                String role = resultSet.getString("staff_role");

                Staff staff = new Staff(id,name,phone,role);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  staffList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        List<Staff> staffInfoList = retrieveData();
        staffs.addAll(staffInfoList);
        staffTableView.setItems(staffs);
    }
}