package Controller;

import Database.DataBaseHandler;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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

    public JFXDatePicker availableFromDate;
    DataBaseHandler dataBaseHandler;

    ObservableList<String> numberOfRooms = FXCollections.observableArrayList ("Studio","1","2","3","4","5","6");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList ("One","Two","Three");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("Ground floor","1","2","3","4","5","6","7","more");


    public void save(ActionEvent actionEvent) {

        int property_ID = Integer.parseInt (propertyID.getText ());
        String apartmentRegion = region.getText ();
        String apartmentAddress = address.getText ();
        String apartmentArea = area.getText ();
        int year_Built = Integer.parseInt (yearBuilt.getText ());
        int apartmentPrice = Integer.parseInt (price.getText ());
        boolean apartmentHeating = heating.isSelected ();
        boolean apartmentParking = parking.isSelected ();
        boolean apartmentBalcony =balcony.isSelected ();
        String apartmentFloor =floor.getAccessibleText ();
        String apartmentRooms =roomsNumber.getAccessibleText ();
        String apartmentBathrooms =bathroomsNumber.getAccessibleText ();





        if (propertyID.getText ().isEmpty ()|| apartmentRegion.isEmpty () || apartmentAddress.isEmpty ()
                || apartmentArea.isEmpty () || yearBuilt.getText ().isEmpty () || price.getText ().isEmpty ()) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please enter in all fields");
            alert.showAndWait ();
            return;
        }
       /*String qu ="INSERT INTO ApartmentForRent (property_ID,apartmentRegion,apartmentAddress,apartmentArea,year_Built,apartmentPrice,apartmentHeating,apartmentParking,apartmentBalcony,apartmentFloor,apartmentRooms,apartmentBathrooms,true)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
       /* String Query_String = "INSERT INTO tablename(field1,field2)
        VALUES ('"+Text1+"','"+Text2+"')"*/

        String qu = "INSERT INTO ApartmentForRent VALUES ("
                + "'" + property_ID + "',"
                + "'" + apartmentRegion + "',"
                + "'" + apartmentAddress + "',"
                + "'" + apartmentArea + "',"
                + "'" + year_Built + "',"
                + "'" + apartmentPrice + "',"
                + "'" + apartmentHeating + "',"
                + "'" + apartmentParking + "',"
                + "'" + apartmentBalcony + "',"
                + "'" + apartmentFloor + "',"
                + "'" + apartmentRooms + "',"
                + "'" + apartmentBathrooms + "',"
                + "'" + true + "'" +
                ")";
        System.out.println (qu);
        if (dataBaseHandler.execAction (qu)) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText (null);
            alert.setContentText ("SUCCESS");
            alert.showAndWait ();

        } else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("There is Error");
            alert.showAndWait ();
            propertyID.setText ("");
            region.setText ("");
            address.setText ("");
            area.setText ("");
            yearBuilt.setText ("");
            price.setText ("");


        }
    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler =DataBaseHandler.getInstance ();
        roomsNumber.setItems (numberOfRooms);
        bathroomsNumber.setItems (numberOfBathrooms);
        floor.setItems (floorNumber);
    }
}
