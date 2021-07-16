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

public class RoomController implements Initializable {
    @FXML private TextField roomNoField;
    @FXML private TextArea descriptionArea;
    @FXML private TextField typeField;
    @FXML private Label statusLabel;

    @FXML private TableView<Room> roomTableView;
    @FXML private TableColumn<Room,String> roomNoColumn;
    @FXML private TableColumn<Room,String> descColumn;
    @FXML private TableColumn<Room,String> typeColumn;

    private ObservableList<Room> rooms;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public RoomController() {
        roomNoField = new TextField();
        descriptionArea = new TextArea();
        typeField = new TextField();
        statusLabel = new Label();

        roomTableView = new TableView<>();
        roomNoColumn = new TableColumn<>();
        descColumn = new TableColumn<>();
        typeColumn = new TableColumn<>();

        rooms = FXCollections.observableArrayList();
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
    private void selectDRoom(){
        typeField.setText("Doctor's Room");
    }

    @FXML
    private void selectWRoom(){
        typeField.setText("Waiting Room");
    }

    @FXML
    private void selectORoom(){
        typeField.setText("Office Room");
    }

    @FXML
    private void selectIRoom(){
        typeField.setText("Inventory Room");
    }

    @FXML
    private void clickClear(){
        clearForm();
    }

    private void clearForm(){
        roomNoField.setText("");
        descriptionArea.setText("");
        typeField.setText("");
    }

    @FXML
    private void handleSelect(){
        Room selectedItems =roomTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(Room selectedItems){
        roomNoField.setText(""+ selectedItems.getRoomNo());
        descriptionArea.setText(selectedItems.getDescription());
        typeField.setText(selectedItems.getRoomType());
    }

    @FXML
    private void clickAdd(){
        int roomNo = Integer.parseInt(roomNoField.getText());
        String desc = descriptionArea.getText();
        String type = typeField.getText();

        Room roomInfo = new Room(roomNo,desc,type);
        if(storeData(roomInfo) != null){
            rooms.add(roomInfo);
            statusLabel.setText("Room added successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't add room!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickUpdate(){
        int roomNo = Integer.parseInt(roomNoField.getText());
        String desc = descriptionArea.getText();
        String type = typeField.getText();

        Room roomInfo = new Room(roomNo,desc,type);
        if(updateData(roomInfo) != null){
            rooms.add(roomInfo);
            statusLabel.setText("Room updated successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update room!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickDelete(){
        int roomNo = Integer.parseInt(roomNoField.getText());
        String desc = descriptionArea.getText();
        String type = typeField.getText();

        Room roomInfo = new Room(roomNo,desc,type);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteData(roomInfo);
            statusLabel.setText("Room deleted successfully!");
            clearForm();
        }
    }

    private Room storeData(Room room) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO room VALUES(%d, \"%s\", \"%s\")",
                    room.getRoomNo(),
                    room.getDescription(),
                    room.getRoomType());

            System.out.println(query);
            statement.executeUpdate(query);

            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Room updateData(Room room){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE room SET description = \"%s\", room_type = \"%s\" where room_no = %d",
                    room.getDescription(),
                    room.getRoomType(),
                    room.getRoomNo());

            System.out.println(query);
            statement.executeUpdate(query);

            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Room deleteData(Room room){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM room where room_no = %d",
                    room.getRoomNo());

            System.out.println(query);
            statement.executeUpdate(query);

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> retrieveData(){
        Connection connection = getConnection();
        List<Room> roomList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM room";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int roomNo = resultSet.getInt("room_no");
                String desc = resultSet.getString("description");
                String type = resultSet.getString("room_type");

                Room room = new Room(roomNo,desc,type);
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  roomList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));

        List<Room> roomInfoList = retrieveData();
        rooms.addAll(roomInfoList);
        roomTableView.setItems(rooms);
    }
}