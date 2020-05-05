package Controller;

import Models.House;
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

public class AddHouseForSaleController implements Initializable {
    public JFXTextField area;
    public JFXTextField yearBuilt;
    public JFXTextField fees;
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

    ObservableList<String> numberOfRooms = FXCollections.observableArrayList ("STUDIO","ONE","TWO","THREE","FOUR","FIVE","SIX");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList ("ONE","TWO","THREE");

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
