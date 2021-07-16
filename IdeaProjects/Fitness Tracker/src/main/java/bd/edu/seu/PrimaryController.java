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

import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML private TextField weightField;
    @FXML private TextField systolicField;
    @FXML private DatePicker datePicker;
    @FXML private  TextField diastolicField;
    @FXML private TableView<Fitness> fitnessTableView;
    @FXML private TableColumn<Fitness, String> weightColumn;
    @FXML private TableColumn<Fitness, String> dateColumn;
    @FXML private TableColumn<Fitness, String> systolicColumn;
    @FXML private TableColumn<Fitness, String> diastolicColumn;

    private ObservableList<Fitness> fitLog;

    public PrimaryController() {

        weightField = new TextField();
        systolicField = new TextField();
        datePicker = new DatePicker();
        diastolicField = new TextField();

        fitnessTableView = new TableView<>();
        weightColumn = new TableColumn<>();
        dateColumn = new TableColumn<>();
        systolicColumn = new TableColumn<>();
        diastolicColumn = new TableColumn<>();

        fitLog = FXCollections.observableArrayList();
    }

    @FXML
    private void handleSaveButton(){
        double weight = Double.parseDouble(weightField.getText());
        int sys = Integer.parseInt(systolicField.getText());
        int dia = Integer.parseInt(diastolicField.getText());
        LocalDate date = datePicker.getValue();

        Fitness logs = new Fitness(date,weight,sys,dia);
        System.out.println("Data Inserted!");
            fitLog.add(logs);
    }

    @FXML
    private void switchToAbout() throws IOException {
        App.setRoot("about");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        systolicColumn.setCellValueFactory(new PropertyValueFactory<>("sysBp"));
        diastolicColumn.setCellValueFactory(new PropertyValueFactory<>("diaBp"));

        fitnessTableView.setItems(fitLog);
    }
}
