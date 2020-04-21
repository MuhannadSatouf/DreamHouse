package Controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForAddHouseForRent implements Initializable {
    public JFXTextField area;
    public JFXTextField yearBuilt;
    public JFXTextField price;
    public JFXCheckBox parking;
    public JFXCheckBox heating;
    public JFXCheckBox garage;
    public JFXCheckBox balcony;
    public JFXComboBox roomsNumber;
    public JFXButton saveBtn;
    public JFXButton cancelBtn;
    public JFXComboBox bathroomsNumber;
    public JFXTextField address;
    public JFXTextField region;
    public JFXTextField propertyID;
    public JFXTextField availableFromDate;
    public JFXDatePicker availableFrom;


    ObservableList<String> numberOfRooms = FXCollections.observableArrayList ("Studio","1","2","3","4","5","6");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList ("One","Two","Three");


    public void save(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomsNumber.setItems (numberOfRooms);
        bathroomsNumber.setItems (numberOfBathrooms);
    }
}
