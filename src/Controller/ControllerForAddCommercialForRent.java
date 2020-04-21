package Controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForAddCommercialForRent implements Initializable {
    public JFXTextField area;
    public JFXTextField yearBuilt;
    public JFXTextField price;
    public JFXCheckBox parking;
    public JFXButton saveBtn;
    public JFXComboBox type;
    public JFXTextField address;
    public JFXTextField region;
    public JFXComboBox floor;
    public JFXTextField propertyID;
    public JFXButton cancelBtn;


    public JFXDatePicker availableFrom;

    ObservableList<String> propertyType = FXCollections.observableArrayList ("Offices","Retail Properties","Gas Stations","Stores","Restaurants");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("Ground floor","1","2","3","4","5","6","7","None","more");

    public void save(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setItems (propertyType);
        floor.setItems (floorNumber);
    }
}
