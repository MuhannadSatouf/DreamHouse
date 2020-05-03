package src.Controller;

import Models.DataBaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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

public class AddApartmentForSaleController implements Initializable {
    public JFXTextField propertyID;
    public JFXTextField region;
    public JFXTextField address;
    public JFXTextField yearBuilt;
    public JFXTextField price;
    public JFXTextField area;
    public JFXTextField fees;
    public JFXCheckBox parking;
    public JFXCheckBox heating;
    public JFXCheckBox balcony;
    public JFXComboBox roomsNumber;
    public JFXButton saveBtn;
    public JFXComboBox bathroomsNumber;
    public JFXComboBox floor;
    public JFXButton cancelBtn;


    DataBaseHandler dataBaseHandler;
    ObservableList<String> numberOfRooms = FXCollections.observableArrayList ("STUDIO","ONE","TWO","THREE","FOUR","FIVE","SIX");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList ("ONE","TWO","THREE");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("GROUND_FLOOR","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","MORE");

    public void save(ActionEvent actionEvent) {
        if (propertyID.getText ().isEmpty () || region.getText ().isEmpty () || address.getText ().isEmpty () ||
                yearBuilt.getText ().isEmpty () || price.getText ().isEmpty () ||  area.getText().isEmpty() ||
                fees.getText().isEmpty()){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please enter in all fields");
            alert.showAndWait ();
            return;
        }

        dataBaseHandler.addPropertyForSale(propertyID.getText(), region.getText(), address.getText(), area.getText(), fees.getText(),price.getText());
        try {
            String qu = "INSERT INTO resident (Heating,Parking,Balcony,Rooms,Bathrooms,Year,Property_ID) " + "VALUES (?,?,?,?,?,?,?) ";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);
            pst.setBoolean(1, heating.isSelected());
            pst.setBoolean(2, parking.isSelected());
            pst.setBoolean(3, balcony.isSelected());
            pst.setString(4, roomsNumber.getValue().toString());
            pst.setString(5, bathroomsNumber.getValue().toString());
            pst.setInt(6, Integer.parseInt(yearBuilt.getText()));
            pst.setInt(7, Integer.parseInt(propertyID.getText()));

            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            String qu = "INSERT INTO apartment (Floor,Property_ID) " + "VALUES (?,?) ";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);
            pst.setString(1, floor.getValue().toString());
            pst.setInt(2, Integer.parseInt(propertyID.getText()));


            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance();
        roomsNumber.setItems (numberOfRooms);
        bathroomsNumber.setItems (numberOfBathrooms);
        floor.setItems (floorNumber);
    }
}
