package Controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

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
    public DatePicker availableFrom;


    ObservableList<String> propertyType = FXCollections.observableArrayList ("OFFICES","RETAIL_PROPERTIES","GAS_STATIONS","STORES","RESTAURANTS");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("GROUND_FLOOR","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","NONE","MORE");

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
