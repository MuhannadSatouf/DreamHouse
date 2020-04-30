package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForAddApartmentForSale implements Initializable {
    public JFXTextField area;
    public JFXTextField yearBuilt;
    public JFXTextField fees;
    public JFXTextField price;
    public JFXCheckBox parking;
    public JFXCheckBox heating;
    public JFXCheckBox balcony;
    public JFXComboBox roomsNumber;
    public JFXButton saveBtn;
    public JFXComboBox bathroomsNumber;
    public JFXTextField propertyID;
    public JFXComboBox floor;
    public JFXButton cancelBtn;
    public JFXTextField address;
    public JFXTextField region;

    ObservableList<String> numberOfRooms = FXCollections.observableArrayList ("STUDIO","ONE","TWO","THREE","FOUR","FIVE","SIX");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList ("ONE","TWO","THREE");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("GROUND_FLOOR","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","MORE");

    public void save(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomsNumber.setItems (numberOfRooms);
        bathroomsNumber.setItems (numberOfBathrooms);
        floor.setItems (floorNumber);
    }
}
