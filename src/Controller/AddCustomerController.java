package Controller;


import Models.*;
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
import javafx.stage.Stage;

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
    private boolean editMode = Boolean.FALSE;
    ObservableList<String> customerTypeList = FXCollections.observableArrayList("Buyer", "vendor", "tenant");
    DataBaseHandler dataBaseHandler;


    public void saveCustomer(ActionEvent actionEvent) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String check = dataBaseHandler.checkIfUserExist(customerSSNTextField.getText());

        String reg1 = "[1-9][0-9]{9}";
        String reg2 = "[1-9][0-9]{11}";
        if (customerSSNTextField.getText().matches(reg1) || customerSSNTextField.getText().matches(reg2) && phoneTextField.getText().matches(reg1)) {

            if (editMode) {
                userEdit ();
                return;
            }



            if (!check.equals(customerSSNTextField.getText())) {

                dataBaseHandler.addUser(customerSSNTextField.getText(), customerPasswordTextField.getText(),
                        nameTextField.getText(), addressTextField.getText(), phoneTextField.getText(), emailTextField.getText());







                try {

                    String qu = "INSERT INTO customer (Customer_type,SSN)" +
                            "VALUES(?,?)";

                    PreparedStatement pst;
                    pst = DataBaseHandler.connection.prepareStatement(qu);
                    pst.setString(1, customerType.getValue().toString());
                    pst.setString(2, customerSSNTextField.getText());

                    pst.execute();
                    pst.close();

                    createMessage("Thank you, The customer has been added to the system");
                    Stage stage = (Stage) startPane.getScene().getWindow();
                    stage.close();

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            } else if (check.equals(customerSSNTextField.getText())) {
                createMessage("The user already exist");

            }

        } else {
            createMessage("The customer SSN should be 10-12 numbers and the phone number should be 10 numbers");
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
        makeSSNAndPhoneOnlyNumbers();
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

    private void makeSSNAndPhoneOnlyNumbers() {
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


    public void userEdit() {
        Customer customer = new Customer (customerSSNTextField.getText(), nameTextField.getText(), addressTextField.getText(), phoneTextField.getText(),
                (emailTextField.getText()),customerType.getValue ().toString ());
        if (dataBaseHandler.editCustomer (customer)) {
            createMessage("Customer has been edited successfully!");

        } else {
            createMessage("FAILED");

        }
    }

    public void refreshCustomer(Customer customer) {
        customerType.setItems (customerTypeList);
        editMode = Boolean.TRUE;
    }

    public void refreshUser(User user) {
        customerSSNTextField.setText (user.getSsn ());
        addressTextField.setText (user.getAddress ());
        phoneTextField.setText (user.getPhone ());
        nameTextField.setText (user.getName ());
        emailTextField.setText (user.getEmail ());
        editMode = Boolean.TRUE;
        customerSSNTextField.setEditable(false);
    }


}
