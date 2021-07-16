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

public class AppointmentController implements Initializable {
    @FXML private TableView<Appointment> appointmentTableView;
    @FXML private TableColumn<Appointment,String> pIDColumn;
    @FXML private TableColumn<Appointment,String> pNameColumn;
    @FXML private TableColumn<Appointment,String> dNameColumn;
    @FXML private TableColumn<Appointment,String> roomNoColumn;
    @FXML private TableColumn<Appointment,String> dateColumn;

    private ObservableList<Appointment> appointments;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public AppointmentController() {
        appointmentTableView = new TableView<>();
        pIDColumn = new TableColumn<>();
        pNameColumn = new TableColumn<>();
        dNameColumn = new TableColumn<>();
        roomNoColumn = new TableColumn<>();
        dateColumn = new TableColumn<>();

        appointments = FXCollections.observableArrayList();
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
    private void clickBack() throws IOException {
        App.setRoot("admin");
    }

    public List<Appointment> retrieveData(){
        Connection connection = getConnection();
        List<Appointment> appointmentList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = " SELECT p.id, p.name, d.name, d.room_no, p.date FROM patient p, doctor d WHERE p.assigned_to = d.id";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String pId = resultSet.getString("id");
                String pName = resultSet.getString("name");
                String dName = resultSet.getString("name");
                int roomNo = resultSet.getInt("room_no");
                String date = resultSet.getString("date");

                Appointment appointment = new Appointment(pId,pName,dName,roomNo,LocalDate.parse(date));
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  appointmentList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        dNameColumn.setCellValueFactory(new PropertyValueFactory<>("docName"));
        roomNoColumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        List<Appointment> appointmentInfoList = retrieveData();
        appointments.addAll(appointmentInfoList);
        appointmentTableView.setItems(appointments);
    }
}
