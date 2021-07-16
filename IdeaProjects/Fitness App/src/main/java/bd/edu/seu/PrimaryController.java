package bd.edu.seu;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable{

    @FXML private DatePicker datePicker;
    @FXML private TextField timeField;
    @FXML private TextField weightField;
    @FXML private TextField systolicField;
    @FXML private  TextField diastolicField;
    @FXML private Label statusLabel;

    @FXML private TableView<FitnessLog> fitnessTableView;
    @FXML private TableColumn<FitnessLog, String> dateColumn;
    @FXML private TableColumn<FitnessLog, String> timeColumn;
    @FXML private TableColumn<FitnessLog, String> weightColumn;
    @FXML private TableColumn<FitnessLog, String> systolicColumn;
    @FXML private TableColumn<FitnessLog, String> diastolicColumn;

    private ObservableList<FitnessLog> fitnessLog;

    private final String DB_URL = "jdbc:mysql://localhost/fitness_app";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    private Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            //System.out.println("Connection ok!");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PrimaryController() {

        datePicker = new DatePicker();
        timeField = new TextField();
        weightField = new TextField();
        systolicField = new TextField();
        diastolicField = new TextField();
        statusLabel = new Label();

        fitnessTableView = new TableView<>();
        dateColumn = new TableColumn<>();
        timeColumn = new TableColumn<>();
        weightColumn = new TableColumn<>();
        systolicColumn = new TableColumn<>();
        diastolicColumn = new TableColumn<>();

        fitnessLog = FXCollections.observableArrayList();
    }

    @FXML
    private void handleSaveButton(){
        LocalDate date = datePicker.getValue();
        String time = timeField.getText();
        double weight = Double.parseDouble(weightField.getText());
        int sys = Integer.parseInt(systolicField.getText());
        int dia = Integer.parseInt(diastolicField.getText());


        FitnessLog fitness = new FitnessLog(date,time,weight,sys,dia);

        if (storeIntoDB(fitness) != null) {
            fitnessLog.add(fitness);
            clearForm();
            statusLabel.setText("Fitness Updated!");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't insert data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelButton(){
        clearForm();
    }

    private void clearForm() {
        datePicker.setValue(null);
        timeField.clear();
        weightField.clear();
        systolicField.clear();
        diastolicField.clear();
    }

    private FitnessLog storeIntoDB(FitnessLog fitness){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO fitness_log VALUES(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    fitness.getDate().toString(),
                    fitness.getTime(),
                    fitness.getWeight(),
                    fitness.getSystolic(),
                    fitness.getDiastolic());
            //System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data inserted!");

            return fitness;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    private void handleSelectAction(){
        FitnessLog selectedItem = fitnessTableView.getSelectionModel().getSelectedItem();
        copyTemp(selectedItem);
    }

    private void copyTemp(FitnessLog selectedItem) {
        datePicker.setValue(selectedItem.getDate());
        timeField.setText("" + selectedItem.getTime());
        weightField.setText("" + selectedItem.getWeight());
        systolicField.setText("" + selectedItem.getSystolic());
        diastolicField.setText("" + selectedItem.getDiastolic());
    }

    @FXML
    private void switchToInfo() throws IOException {
        App.setRoot("about");
    }

    public List<FitnessLog> retrieveData(){
        Connection connection = getConnection();
        List<FitnessLog> fitnessList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM fitness_log";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String dateString = resultSet.getString("date");
                String time = resultSet.getString("time");
                double weight = resultSet.getDouble("weight");
                int sys = resultSet.getInt("systolic");
                int dia = resultSet.getInt("diastolic");
                FitnessLog fitness = new FitnessLog(LocalDate.parse(dateString),time,weight,sys,dia);
            fitnessList.add(fitness);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  fitnessList;
    }

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        systolicColumn.setCellValueFactory(new PropertyValueFactory<>("systolic"));
        diastolicColumn.setCellValueFactory(new PropertyValueFactory<>("diastolic"));

        List<FitnessLog> fitnessLogList = retrieveData();

        fitnessLog.addAll(fitnessLogList);

        fitnessTableView.setItems(fitnessLog);
    }
}
