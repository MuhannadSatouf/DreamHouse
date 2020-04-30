package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForAddCustomer implements Initializable {
    public JFXTextField address;
    public JFXTextField phone;
    public JFXTextField email;
    public JFXComboBox customerType;
    public JFXButton saveBtn;
    public JFXTextField name;
    public JFXTextField customerSSN;
    public JFXButton cancelBtn;
    public JFXTextField customerPassword;

    ObservableList<String> customerTypeList = FXCollections.observableArrayList ("BUYER","VENDOR","TENANT");



    public void saveCustomer(ActionEvent actionEvent) {

    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     customerType.setValue ("Buyer");
     customerType.setItems (customerTypeList);
    }
}
