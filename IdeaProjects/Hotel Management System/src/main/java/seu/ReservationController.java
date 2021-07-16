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

public class ReservationController implements Initializable {

    @FXML private TextField roomNoField;
    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;
    @FXML private TextField custIdField;

    @FXML private TableView<Reservation> reservationTableView;
    @FXML private TableColumn<Reservation, String> roomNoColumn;
    @FXML private TableColumn<Reservation, String> fromColumn;
    @FXML private TableColumn<Reservation, String> toColumn;
    @FXML private TableColumn<Reservation, String> custIdColumn;

    private ObservableList<Reservation> reservations;

    private final String DB_URL = "jdbc:mysql://localhost/hotel_reservation";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public ReservationController() {
        roomNoField = new TextField();
        fromDate = new DatePicker();
        toDate = new DatePicker();
        custIdField = new TextField();

        reservationTableView = new TableView<>();
        roomNoColumn = new TableColumn<>();
        fromColumn = new TableColumn<>();
        toColumn = new TableColumn<>();
        custIdColumn = new TableColumn<>();

        reservations = FXCollections.observableArrayList();
    }

    @FXML
    private void backButton() throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    private void handleReserveButton(){
        int roomNo = Integer.parseInt(roomNoField.getText());
        LocalDate from = fromDate.getValue();
        LocalDate to = toDate.getValue();
        int id = Integer.parseInt(custIdField.getText());

        Reservation reservationInfo = new Reservation(roomNo,from,to,id);

        if (storeIntoDB(reservationInfo) != null) {
            reservations.add(reservationInfo);
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't insert data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleUpdate(){
        int rNo = Integer.parseInt(roomNoField.getText());
        LocalDate fDate = fromDate.getValue();
        LocalDate tDate = toDate.getValue();
        int id = Integer.parseInt(custIdField.getText());

        Reservation reservationInfo = new Reservation(rNo,fDate,tDate,id);

        if (updateDB(reservationInfo) != null) {
            reservations.add(reservationInfo);
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update data!");

            alert.showAndWait();
        }
    }

    private void clearForm(){
        roomNoField.setText("");
        fromDate.setValue(null);
        toDate.setValue(null);
        custIdField.setText("");
    }

    private Reservation storeIntoDB(Reservation reservation){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO reservation VALUES(%d, \"%s\", \"%s\", %d)",
                    reservation.getRoomNo(),
                    reservation.getFromDate().toString(),
                    reservation.getToDate().toString(),
                    reservation.getCustId());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data inserted!");

            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Reservation updateDB(Reservation reservation){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE reservation SET from_date = \"%s\",to_date = \"%s\",cust_id = \"%s\" " +
                            "where room_no = \"%s\"",
                    reservation.getFromDate(),
                    reservation.getToDate(),
                    reservation.getCustId(),
                    reservation.getRoomNo());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data inserted!");

            return reservation;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

    public List<Reservation> retrieveData(){
        Connection connection = getConnection();
        List<Reservation> reservationList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM reservation";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int roomNo = resultSet.getInt("room_no");
                String from = resultSet.getString("from_date");
                String to = resultSet.getString("to_date");
                int id = resultSet.getInt("cust_id");

                Reservation reservation = new Reservation(roomNo,LocalDate.parse(from),LocalDate.parse(to),id);
                reservationList.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  reservationList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));

        List<Reservation>reservationInfoList = retrieveData();
        reservations.addAll(reservationInfoList);
        reservationTableView.setItems(reservations);
    }
}