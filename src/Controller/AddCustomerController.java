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

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
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

        if (customerSSN.getText ().isEmpty () || address.getText ().isEmpty () || name.getText ().isEmpty () ||
                phone.getText ().isEmpty () || email.getText ().isEmpty () || customerPassword.getText ().isEmpty ()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please enter in all fields");
            alert.showAndWait ();
            return;
        }

        dataBaseHandler.addUser (customerSSN.getText (), customerPassword.getText (),
                name.getText (), address.getText (), phone.getText (), email.getText ());

        try {

            String qu = "INSERT INTO customer (Customer_type,SSN)" +
                    "VALUES(?,?)";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);
            pst.setString (1, customerType.getValue ().toString ());
            pst.setString (2, customerSSN.getText ());

            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }

    }

    public void cancel(ActionEvent actionEvent) {
        System.exit (0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //   customerType.setValue ("Buyer");
        dataBaseHandler = DataBaseHandler.getInstance ();
        customerType.setItems (customerTypeList);
    }
}
