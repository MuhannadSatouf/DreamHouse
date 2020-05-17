package Controller;


import Models.DataBaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    public Pane startPane;
    public JFXTextField addressTextField;
    public JFXTextField phoneTextField;
    public JFXTextField emailTextField;
    public JFXComboBox customerType;
    public JFXButton saveBtnTextField;
    public JFXTextField nameTextField;
    public JFXTextField customerSSNTextField;
    public JFXButton cancelBtn;
    public JFXTextField customerPasswordTextField;

    ObservableList<String> customerTypeList = FXCollections.observableArrayList("Buyer", "vendor", "tenant");
    DataBaseHandler dataBaseHandler;


    public void saveCustomer(ActionEvent actionEvent) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String check = dataBaseHandler.checkIfUserExist(customerSSNTextField.getText());

        if (!check.equals(customerSSNTextField.getText())) {

            dataBaseHandler.addUser(customerSSNTextField.getText(), customerPasswordTextField.getText(),
                    nameTextField.getText(), addressTextField.getText(), phoneTextField.getText(), emailTextField.getText());

            try {

                String qu = "INSERT INTO customer (Role,SSN)" +
                        "VALUES(?,?)";

                PreparedStatement pst;
                pst = DataBaseHandler.connection.prepareStatement(qu);
                pst.setString(1, customerType.getValue().toString());
                pst.setString(2, customerSSNTextField.getText());

                pst.execute();
                pst.close();

                createMessage("Thank you, The customer has been added to the system");

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else if (check.equals(customerSSNTextField.getText())) {
            createMessage("The user already exist");
        }
    }


    public void cancel(ActionEvent actionEvent) {
        customerPasswordTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
        customerSSNTextField.setText("");
        customerType.setValue("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeNameOnlyString();
        makeTheSaveButtonActive();
        dataBaseHandler = DataBaseHandler.getInstance();
        customerType.setItems(customerTypeList);

    }

    public void createMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void makeTheSaveButtonActive() {
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(customerSSNTextField.textProperty(), customerPasswordTextField.textProperty(), phoneTextField.textProperty()
                        , emailTextField.textProperty(), nameTextField.textProperty(), addressTextField.textProperty(),
                        nameTextField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (customerSSNTextField.getText().isEmpty() || customerPasswordTextField.getText().isEmpty()
                        || phoneTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || nameTextField.getText().isEmpty()
                        || addressTextField.getText().isEmpty() || nameTextField.getText().isEmpty());
            }
        };

        saveBtnTextField.disableProperty().bind(bb);
    }

    public void makeNameOnlyString() {
        nameTextField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\D*")) {
                    nameTextField.setText(newValue.replaceAll("[^\\D]", ""));
                }
            }
        });
    }

    private void makeSSNOnlyNumbers() {
        // make the textField ssn and phone take only numbers.
        customerSSNTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") && newValue.length() <= 12) {
                customerSSNTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        phoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                phoneTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
