package seu;

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

public class CustomerController implements Initializable {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextArea addressArea;
    @FXML private TextField nidField;
    @FXML private TextField sexField;

    @FXML private TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer, String> idColumn;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> phoneColumn;
    @FXML private TableColumn<Customer, String> addressColumn;
    @FXML private TableColumn<Customer, String> nidColumn;
    @FXML private TableColumn<Customer, String> sexColumn;

    private ObservableList<Customer> customer;

    private final String DB_URL = "jdbc:mysql://localhost/hotel_reservation";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public CustomerController() {
        idField = new TextField();
        nameField = new TextField();
        phoneField = new TextField();
        addressArea = new TextArea();
        nidField = new TextField();
        sexField = new TextField();

        customerTableView = new TableView<>();
        idColumn = new TableColumn<>();
        nameColumn = new TableColumn<>();
        phoneColumn = new TableColumn<>();
        addressColumn = new TableColumn<>();
        nidColumn = new TableColumn<>();
        sexColumn = new TableColumn<>();

        customer = FXCollections.observableArrayList();
    }

    @FXML
    private void handleSaveButton(){
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressArea.getText();
        String nidno = nidField.getText();
        String sex = sexField.getText();

        Customer customerinfo = new Customer(id,name,phone,address,nidno,sex);

        if (storeIntoDB(customerinfo) != null) {
            customer.add(customerinfo);
            clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't save data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleUpdate(){
        String name = nameField.getText();
        String phone = phoneField.getText();
        String address = addressArea.getText();
        String nidno = nidField.getText();
        String sex = sexField.getText();
        int id = Integer.parseInt(idField.getText());

        Customer customerinfo = new Customer(id,name,phone,address,nidno,sex);

        if (updateDB(customerinfo) != null) {
            customer.add(customerinfo);
            clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't update data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void backButton() throws IOException {
        App.setRoot("homepage");
    }

    private void clear(){
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        addressArea.setText("");
        nidField.setText("");
        sexField.setText("");
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

    private Customer storeIntoDB(Customer customer){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO customer_info VALUES(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    customer.getId(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getNidno(),
                    customer.getSex());
            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data inserted!");

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Customer updateDB(Customer customer){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE customer_info SET name = \"%s\",phone_no = \"%s\",address = \"%s\"" +
                            ",nid_no = \"%s\",sex = \"%s\" WHERE id = \"%s\"",
                    customer.getName(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getNidno(),
                    customer.getSex(),
                    customer.getId());
            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data updated!");

            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Customer> retrieveData(){
        Connection connection = getConnection();
        List<Customer> customerList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM customer_info";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone_no");
                String address = resultSet.getString("address");
                String nidno = resultSet.getString("nid_no");
                String sex = resultSet.getString("sex");
                Customer customer = new Customer(id,name,phone,address,nidno,sex);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  customerList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        nidColumn.setCellValueFactory(new PropertyValueFactory<>("nidno"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));

        List<Customer>customerInfoList = retrieveData();
        customer.addAll(customerInfoList);
        customerTableView.setItems(customer);
    }
}