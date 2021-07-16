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
import java.nio.file.attribute.UserPrincipalLookupService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML private TextField mailField;
    @FXML private TextField userField;
    @FXML private TextField passField;
    @FXML private TextField typeField;

    @FXML private TableView<User> userTableView;
    @FXML private TableColumn<User,String> mailColumn;
    @FXML private TableColumn<User,String> userColumn;
    @FXML private TableColumn<User,String> passColumn;
    @FXML private TableColumn<User,String> typeColumn;

    private ObservableList<User> users;

    private final String DB_URL = "jdbc:mysql://localhost/hotel_reservation";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public UserController() {
        mailField = new TextField();
        userField = new TextField();
        passField = new TextField();
        typeField = new TextField();

        userTableView = new TableView<>();
        mailColumn = new TableColumn<>();
        userColumn = new TableColumn<>();
        passColumn = new TableColumn<>();
        typeColumn = new TableColumn<>();

        users = FXCollections.observableArrayList();
    }

    @FXML
    private void backButton() throws IOException {
        App.setRoot("admin");
    }

    @FXML
    private void adminButton(){
        typeField.setText("Admin");
    }

    @FXML
    private void receptionistButton(){
        typeField.setText("Receptionist");
    }

    @FXML
    private void handleCreateButton(){
        String mail = mailField.getText();
        String username = userField.getText();
        String pass = passField.getText();
        String type = typeField.getText();

        User userInfo = new User(username,mail,pass,type);
        if (storeIntoDB(userInfo) != null) {
            clearForm();
            users.add(userInfo);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't insert data!");

            alert.showAndWait();
        }
    }

    private void clearForm(){
        mailField.setText("");
        userField.setText("");
        passField.setText("");
        typeField.setText("");
    }

    @FXML
    private void handleClear(){
        clearForm();
    }

    @FXML
    private void handleSelect(){
        User selectedItems =userTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(User selectedItems){
        mailField.setText(selectedItems.getEmail());
        userField.setText(selectedItems.getUsername());
        passField.setText(selectedItems.getPassword());
        typeField.setText(selectedItems.getUserType());
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

    @FXML
    private void handleUpdate(){
        String username = userField.getText();
        String email = mailField.getText();
        String pass = passField.getText();
        String type = typeField.getText();

        User userInfo = new User(username,email,pass,type);

        if (updateDB(userInfo) != null) {
            users.add(userInfo);
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't update data!");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDelete(){
        String userName = userField.getText();
        String mail = mailField.getText();
        String pass = passField.getText();
        String type = typeField.getText();

        User userInfo = new User(userName,mail,pass,type);

        if (deleteFromDB(userInfo) != null) {
            users.add(userInfo);
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Sorry, couldn't delete user!");

            alert.showAndWait();
        }
    }

    private User storeIntoDB(User user){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO user VALUES(\"%s\", \"%s\", \"%s\", \"%s\")",
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserType());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data inserted!");

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User updateDB(User user){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE user SET e_mail = \"%s\",password = \"%s\",user_type = \"%s\" " +
                            "where username = \"%s\"",
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserType(),
                    user.getUsername());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data updated!");

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User deleteFromDB(User user){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM user where username = \"%s\"",
                    user.getUsername());

            System.out.println(query);
            statement.executeUpdate(query);
            System.out.println("Data updated!");

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> retrieveData(){
        Connection connection = getConnection();
        List<User> userList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String email = resultSet.getString("e_mail");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String type = resultSet.getString("user_type");

                User user = new User(username,email,password,type);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  userList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

        List<User> userInfoList = retrieveData();
        users.addAll(userInfoList);
        userTableView.setItems(users);
    }
}