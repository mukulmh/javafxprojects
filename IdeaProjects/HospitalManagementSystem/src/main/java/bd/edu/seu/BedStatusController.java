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
import java.util.Optional;
import java.util.ResourceBundle;

public class BedStatusController implements Initializable {
    @FXML private TextField bedNoField;
    @FXML private TextField roomNoField;
    @FXML private TextField statusField;
    @FXML private Label statusLabel;

    @FXML private TableView<BedStatus> bedStatusTableView;
    @FXML private TableColumn<BedStatus,String> bedNoColumn;
    @FXML private TableColumn<BedStatus,String> roomNoColumn;
    @FXML private TableColumn<BedStatus,String> statusColumn;

    private ObservableList<BedStatus> bedStatuses;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public BedStatusController() {
        bedNoField = new TextField();
        roomNoField = new TextField();
        statusField = new TextField();
        statusLabel = new Label();

        bedStatusTableView = new TableView<>();
        bedNoColumn = new TableColumn<>();
        roomNoColumn = new TableColumn<>();
        statusColumn = new TableColumn<>();

        bedStatuses = FXCollections.observableArrayList();
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
        bedNoField.setText("");
        roomNoField.setText("");
        statusField.setText("");
    }

    @FXML
    private void handleOp1(){
        statusField.setText("Occupied");
    }

    @FXML
    private void handleOp2(){
        statusField.setText("Released");
    }

    @FXML
    private void handleSelect(){
        BedStatus selectedItems =bedStatusTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(BedStatus selectedItems){
        bedNoField.setText(selectedItems.getBedNo());
        roomNoField.setText(""+ selectedItems.getRoomNo());
        statusField.setText(selectedItems.getStatus());
    }

    @FXML
    private void clickAdd(){
        String bedNo = bedNoField.getText();
        int roomNo = Integer.parseInt(roomNoField.getText());
        String status = statusField.getText();

        BedStatus bedStatusInfo = new BedStatus(bedNo,roomNo,status);
        if(storeData(bedStatusInfo) != null){
            bedStatuses.add(bedStatusInfo);
            statusLabel.setText("Bed added successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't add bed!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickUpdate(){
        String bedNo = bedNoField.getText();
        int roomNo = Integer.parseInt(roomNoField.getText());
        String status = statusField.getText();

        BedStatus bedStatusInfo = new BedStatus(bedNo,roomNo,status);
        if(updateData(bedStatusInfo) != null){
            bedStatuses.add(bedStatusInfo);
            statusLabel.setText("Bed updated successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update bed!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickDelete(){
        String bedNo = bedNoField.getText();
        int roomNo = Integer.parseInt(roomNoField.getText());
        String status = statusField.getText();

        BedStatus bedStatusInfo = new BedStatus(bedNo,roomNo,status);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteData(bedStatusInfo);
            statusLabel.setText("Bed deleted successfully!");
            clearForm();
        }
    }

    private BedStatus storeData(BedStatus bedStatus) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO bed_status VALUES(\"%s\", %d, \"%s\")",
                    bedStatus.getBedNo(),
                    bedStatus.getRoomNo(),
                    bedStatus.getStatus());


            System.out.println(query);
            statement.executeUpdate(query);

            return bedStatus;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BedStatus updateData(BedStatus bedStatus){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE bed_status SET room_no = \"%s\", status = \"%s\" " +
                            "where bed_no = \"%s\"",
                    bedStatus.getRoomNo(),
                    bedStatus.getStatus(),
                    bedStatus.getBedNo());

            System.out.println(query);
            statement.executeUpdate(query);

            return bedStatus;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private BedStatus deleteData(BedStatus bedStatus){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM bed_status where bed_no = \"%s\"",
                    bedStatus.getBedNo());

            System.out.println(query);
            statement.executeUpdate(query);

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BedStatus> retrieveData(){
        Connection connection = getConnection();
        List<BedStatus> bedStatusList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM bed_status";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String bedNo = resultSet.getString("bed_no");
                int roomNo = resultSet.getInt("room_no");
                String status = resultSet.getString("status");

                BedStatus bedStatus = new BedStatus(bedNo,roomNo,status);
                bedStatusList.add(bedStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  bedStatusList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bedNoColumn.setCellValueFactory(new PropertyValueFactory<>("bedNo"));
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        List<BedStatus> bedStatusInfoList = retrieveData();
        bedStatuses.addAll(bedStatusInfoList);
        bedStatusTableView.setItems(bedStatuses);
    }
}