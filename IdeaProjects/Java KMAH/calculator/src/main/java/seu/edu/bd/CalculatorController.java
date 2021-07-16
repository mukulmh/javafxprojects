package seu.edu.bd;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CalculatorController {

    private Double number1;
    private Double number2;
    private String operation;
    private int point = 0;

    @FXML
    private TextField displayField;

    public CalculatorController (){
        displayField = new TextField();
        displayField.setText("0");
    }

  /*  @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    */

    @FXML
    private void ClickOne(){
        String oldText = displayField.getText();
        String newText = oldText + "1";
        displayField.setText(newText);
    }

    @FXML
    private void ClickTwo(){
        String oldText = displayField.getText();
        String newText = oldText + "2";
        displayField.setText(newText);
    }

    @FXML
    private void ClickThree(){
        String oldText = displayField.getText();
        String newText = oldText + "3";
        displayField.setText(newText);
    }

    @FXML
    private void ClickFour(){
        String oldText = displayField.getText();
        String newText = oldText + "4";
        displayField.setText(newText);
    }

    @FXML
    private void ClickFive(){
        String oldText = displayField.getText();
        String newText = oldText + "5";
        displayField.setText(newText);
    }

    @FXML
    private void ClickSix(){
        String oldText = displayField.getText();
        String newText = oldText + "6";
        displayField.setText(newText);
    }

    @FXML
    private void ClickSeven(){
        String oldText = displayField.getText();
        String newText = oldText + "7";
        displayField.setText(newText);
    }

    @FXML
    private void ClickEight(){
        String oldText = displayField.getText();
        String newText = oldText + "8";
        displayField.setText(newText);
    }

    @FXML
    private void ClickNine(){
        String oldText = displayField.getText();
        String newText = oldText + "9";
        displayField.setText(newText);
    }

    @FXML
    private void ClickZero(){
        String oldText = displayField.getText();
        if(!oldText.equals("")) {
            String newText = oldText + "0";
            displayField.setText(newText);
        }
    }

    @FXML
    private void ClickPoint(){
        if (point == 0){
            String oldText = displayField.getText();
            String newText = oldText + ".";
            displayField.setText(newText);
            point++;
        }
    }

    @FXML
    private void ClickAdd(){
        point = 0;
        String oldText = displayField.getText();
        number1 = Double.parseDouble(oldText);
        displayField.clear();
        operation = "ADD";
    }

    @FXML
    private void ClickSub(){
        point = 0;
        String oldText = displayField.getText();
        number1 = Double.parseDouble(oldText);
        displayField.clear();
        operation = "SUB";
    }

    @FXML
    private void ClickMul() {
        point = 0;
        String oldText = displayField.getText();
        number1 = Double.parseDouble(oldText);
        displayField.clear();
        operation = "MUL";
    }

    @FXML
    private void ClickDiv() {
        point = 0;
        String oldText = displayField.getText();
        number1 = Double.parseDouble(oldText);
        displayField.clear();
        operation = "DIV";
    }

    @FXML
    private void clickOpenBracket(){
        displayField.setText("(");
    }

    @FXML
    private void clickCloseBracket(){
        displayField.setText(")");
    }

    @FXML
    private void clear(){
        displayField.setText("");
    }

    @FXML
    private void ClickEqual(){
        String oldText = displayField.getText();
        if(!oldText.equals("")) {
            point = 0;
            number2 = Double.parseDouble(oldText);
            Double result = 0.0;
            switch (operation) {
                case "ADD":
                    result = (number1 + number2);
                    break;
                case "SUB":
                    result = (number1 - number2);
                    break;
                case "MUL":
                    result = (number1 * number2);
                    break;
                case "DIV":
                    result = (number1 / number2);
                    break;
            }
            String newText = String.format("%.2f", result);
            displayField.setText(newText);
        }
    }

}
