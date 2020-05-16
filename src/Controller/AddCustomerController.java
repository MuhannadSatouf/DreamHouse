package Controller;


import Models.DataBaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    public Pane startPane;
    public JFXTextField address;
    public JFXTextField phone;
    public JFXTextField email;
    public JFXComboBox customerType;
    public JFXButton saveBtn;
    public JFXTextField name;
    public JFXTextField customerSSN;
    public JFXButton cancelBtn;
    public JFXTextField customerPassword;

    ObservableList<String> customerTypeList = FXCollections.observableArrayList ("Buyer", "vendor", "tenant");
    DataBaseHandler dataBaseHandler;


    public void saveCustomer(ActionEvent actionEvent) {
        

    }

    public void cancel(ActionEvent actionEvent) {
        System.exit (0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance ();
        customerType.setItems (customerTypeList);

    }
}
