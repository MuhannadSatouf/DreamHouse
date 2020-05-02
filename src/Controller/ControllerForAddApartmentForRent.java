package Controller;


import Models.DataBaseHandler;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForAddApartmentForRent implements Initializable {
    public JFXTextField area;
    public JFXTextField yearBuilt;
    public JFXCheckBox parking;
    public JFXTextField price;
    public JFXCheckBox heating;
    public JFXCheckBox balcony;
    public JFXComboBox roomsNumber;
    public JFXButton saveBtn;
    public JFXComboBox bathroomsNumber;
    public JFXButton cancelBtn;
    public JFXTextField propertyID;
    public JFXComboBox floor;
    public JFXTextField region;
    public JFXTextField address;
    public DatePicker availableFrom;

    DataBaseHandler dataBaseHandler;

    ObservableList<String> numberOfRooms = FXCollections.observableArrayList ("STUDIO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList ("ONE", "TWO", "THREE");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("GROUND_FLOOR", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "MORE");


    public void save(ActionEvent actionEvent) {

    }


    public void cancel(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance ();
        roomsNumber.setItems (numberOfRooms);
        bathroomsNumber.setItems (numberOfBathrooms);
        floor.setItems (floorNumber);
    }
}
