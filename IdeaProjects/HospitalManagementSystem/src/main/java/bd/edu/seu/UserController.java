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

public class UserController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField typeField;
    @FXML private Label statusLabel;

    @FXML private TableView<User> userTableView;
    @FXML private TableColumn<User,String> usernameTableColumn;
    @FXML private TableColumn<User,String> passwordTableColumn;
    @FXML private TableColumn<User,String> typeTableColumn;

    private ObservableList<User> users;

    private final String DB_URL = "jdbc:mysql://localhost/hospital_sys";
    private final String DB_USER = "root";
    private final String DB_PASS ="mh.Mukul";

    public UserController() {
        usernameField = new TextField();
        passwordField = new TextField();
        typeField = new TextField();
        statusLabel = new Label();

        userTableView = new TableView<>();
        usernameTableColumn = new TableColumn<>();
        passwordTableColumn = new TableColumn<>();
        typeTableColumn = new TableColumn<>();

        users = FXCollections.observableArrayList();
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

    @FXML
    private void selectAdmin(){
        typeField.setText("Admin");
    }

    @FXML
    private void selectDoc(){
        typeField.setText("Doctor");
    }

    @FXML
    private void selectReceptionist(){
        typeField.setText("Receptionist");
    }

    @FXML
    private void keyReleased(){
        statusLabel.setText("");
    }

    @FXML
    private void clickClear(){
        clearForm();
    }

    private void clearForm(){
        usernameField.setText("");
        passwordField.setText("");
        typeField.setText("");
    }

    @FXML
    private void handleSelect(){
        User selectedItems =userTableView.getSelectionModel().getSelectedItem();
        selectItems(selectedItems);
    }

    private void selectItems(User selectedItems){
        usernameField.setText(selectedItems.getUsername());
        passwordField.setText(selectedItems.getPassword());
        typeField.setText(selectedItems.getUserType());
    }

    @FXML
    private void clickAdd(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String type = typeField.getText();

        User userInfo = new User(username,password,type);
        if(storeData(userInfo) != null){
            users.add(userInfo);
            statusLabel.setText("User added successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't add user!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickUpdate(){
        String userName = usernameField.getText();
        String password = passwordField.getText();
        String type = typeField.getText();

        User userInfo = new User(userName,password,type);
        if(updateData(userInfo) != null){
            users.add(userInfo);
            statusLabel.setText("User updated successfully!");
            clearForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Couldn't update user!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickDelete(){
        String userName = usernameField.getText();
        String password = passwordField.getText();
        String type = typeField.getText();

        User userInfo = new User(userName,password,type);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteData(userInfo);
            statusLabel.setText("User deleted successfully!");
            clearForm();
        }
    }

    private User storeData(User user) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO user VALUES(\"%s\", \"%s\", \"%s\")",
                    user.getUsername(),
                    user.getPassword(),
                    user.getUserType());

            System.out.println(query);
            statement.executeUpdate(query);

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User updateData(User user){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE user SET username = \"%s\", password = \"%s\", user_type = \"%s\" where username = \"%s\"",
                    user.getUsername(),
                    user.getPassword(),
                    user.getUserType(),
                    user.getUsername());

            System.out.println(query);
            statement.executeUpdate(query);

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User deleteData(User user){
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM user where username = \"%s\"",
                    user.getUsername());

            System.out.println(query);
            statement.executeUpdate(query);

            return null;
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
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String type = resultSet.getString("user_type");

                User user = new User(username,password,type);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  userList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameTableColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordTableColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

        List<User> userInfoList = retrieveData();
        users.addAll(userInfoList);
        userTableView.setItems(users);
    }
}