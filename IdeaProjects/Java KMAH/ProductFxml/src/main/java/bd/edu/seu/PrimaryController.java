package bd.edu.seu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextArea addressArea;
    @FXML
    private TextField balanceField;

    @FXML
    private TableView<BankAccount> tableView;
    @FXML
    private TableColumn<BankAccount, String> nameColumn;
    @FXML
    private TableColumn<BankAccount, Number> numberColumn;
    @FXML
    private TableColumn<BankAccount, String> addressColumn;
    @FXML
    private TableColumn<BankAccount, Number> balanceColumn;

    public PrimaryController() {
        nameField = new TextField();
        numberField = new TextField();
        addressArea = new TextArea();
        balanceField = new TextField();

        tableView = new TableView<>();
        nameColumn = new TableColumn<>();
        numberColumn = new TableColumn<>();
        addressColumn = new TableColumn<>();
        balanceColumn = new TableColumn<>();
        readFile();
    }

    public void readFile(){
        try {
            RandomAccessFile inputFile = new RandomAccessFile("Accounts.txt","r");
            String line;
            ArrayList<BankAccount> accounts= new ArrayList<>();
            while ((line = inputFile.readLine())!= null){
                String[] tokens = line.split(",");
                int number = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String address = tokens[2];
                double balance = Double.parseDouble(tokens[3]);
                BankAccount bankAccount = new BankAccount(number, name, address, balance);
                accounts.add(bankAccount);
            }
            for(BankAccount account: accounts)
                System.out.println(account);

        } catch (FileNotFoundException e) {
            System.err.printf("File not found!");
        } catch (IOException e) {
            System.err.printf("Couldn't read file!");
        }
    }

    @FXML
    private void handleAddButton(){
        String name = nameField.getText();
        String address = addressArea.getText();
        int number = Integer.parseInt(numberField.getText());
        double balance = Double.parseDouble(balanceField.getText());
        BankAccount newAccount = new BankAccount(number, name, address, balance);
        App.setNewBankAccount(newAccount);

        System.out.println(newAccount);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<BankAccount> bankAccount = FXCollections.observableArrayList();

        bankAccount.add(new BankAccount(12, "John Doe", "Banani", 5000));
        bankAccount.add(new BankAccount(13, "Jane Doe", "Uttara", 6000));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        tableView.setItems(bankAccount);
    }
}
