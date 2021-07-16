package seu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {

    @FXML private TextField noField;
    @FXML private TextField rTypeField;
    @FXML private TextField bTypeField;
    @FXML private TextField priceField;

    @FXML private TableView<Room> roomTableView;
    @FXML private TableColumn<Room, String> noColumn;
    @FXML private TableColumn<Room, String> rTypeColumn;
    @FXML private TableColumn<Room, String> bTypeColumn;
    @FXML private TableColumn<Room, String> priceColumn;

    private ObservableList<Room> rooms;

    private final String DB_URL = "jdbc:mysql://localhost/hotel_reservation";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public RoomsController() {
        noField = new TextField();
        rTypeField = new TextField();
        bTypeField = new TextField();
        priceField = new TextField();

        roomTableView = new TableView<>();
        noColumn = new TableColumn<>();
        rTypeColumn = new TableColumn<>();
        bTypeColumn = new TableColumn<>();
        priceColumn = new TableColumn<>();

        rooms = FXCollections.observableArrayList();
    }

    @FXML
    private void backButton() throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    private void backToAdmin() throws IOException{
        App.setRoot("admin");
    }

    @FXML
    private void handleUpdate(){
        int roomNo = Integer.parseInt(noField.getText());
        String roomType = rTypeField.getText();
        String bedType = bTypeField.getText();
        int price = Integer.parseInt(priceField.getText());

        Room roomInfo = new Room(roomNo,roomType,bedType,price);

        if (updateDB(roomInfo) != null) {
            rooms.add(roomInfo);
            clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't insert data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddButton(){
        int roomNo = Integer.parseInt(noField.getText());
        String roomType = rTypeField.getText();
        String bedType = bTypeField.getText();
        int price = Integer.parseInt(priceField.getText());

        Room roominfo = new Room(roomNo,roomType,bedType,price);

        if (storeIntoDB(roominfo) != null) {
            rooms.add(roominfo);
            clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't insert data!");

            alert.showAndWait();
        }
    }

    private void clear(){
        noField.setText("");
        rTypeField.setText("");
        bTypeField.setText("");
        priceField.setText("");
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

    private Room storeIntoDB(Room room){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO room VALUES(\"%s\", \"%s\", \"%s\", \"%s\")",
                    room.getRoomNo(),
                    room.getRoomType(),
                    room.getBedType(),
                    room.getPrice());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data inserted!");

            return room;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Room updateDB(Room room){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE room SET r_type = \"%s\",b_type = \"%s\",price = \"%s\" " +
                            "where room_no = \"%s\"",
                    room.getRoomType(),
                    room.getBedType(),
                    room.getPrice(),
                    room.getRoomNo());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data updated!");

            return room;
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
                String roomType = resultSet.getString("r_type");
                String bedType = resultSet.getString("b_type");
                int price = resultSet.getInt("price");

                Room room = new Room(roomNo,roomType,bedType,price);
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  roomList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        rTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        bTypeColumn.setCellValueFactory(new PropertyValueFactory<>("bedType"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        List<Room>roomInfoList = retrieveData();
        rooms.addAll(roomInfoList);
        roomTableView.setItems(rooms);
    }
}