package Controller;

import Models.Apartment;
import Models.DataBaseHandler;
import Models.Property;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddApartmentForRentController implements Initializable {
    public Pane startPane;
    private boolean editMode = Boolean.FALSE;
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

    ObservableList<String> numberOfRooms = FXCollections.observableArrayList("STUDIO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX");
    ObservableList<String> numberOfBathrooms = FXCollections.observableArrayList("ONE", "TWO", "THREE");
    ObservableList<String> floorNumber = FXCollections.observableArrayList("GROUND_FLOOR", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "MORE");


    public void save(ActionEvent actionEvent) {
        if (propertyID.getText().isEmpty() || region.getText().isEmpty() || address.getText().isEmpty() ||
                price.getText().isEmpty() || area.getText().isEmpty() || yearBuilt.getText().isEmpty()) {
            createAlert("Please enter in all fields");
            return;
        }
        String reg = "[0-9]+";
        if (propertyID.getText().matches(reg) & area.getText().matches(reg) & price.getText().matches(reg) & yearBuilt.getText().matches(reg)) {
            if (editMode) {
                apartmentEdit();
                return;
            }
            String query = "SELECT Property_ID from property WHERE Property_ID='" + propertyID.getText() + "'";
            ResultSet resultSet = dataBaseHandler.execQuery(query);
            try {
                if (resultSet.next()) {
                    createAlert("This ID was already entered!");
                    refreshing();
                    return;
                } else {
                    int fees = 0;
                    dataBaseHandler.addProperty(propertyID.getText(), region.getText(), address.getText(), area.getText(), String.valueOf(fees), price.getText());

                    try {

                        String qu = "INSERT INTO Resident (Heating,Parking,Balcony,Rooms, Bathrooms, Property_ID, Year) " + "VALUES (?,?,?,?,?,?,?) ";

                        PreparedStatement pst;
                        pst = DataBaseHandler.connection.prepareStatement(qu);
                        pst.setBoolean(1, heating.isSelected());
                        pst.setBoolean(2, parking.isSelected());
                        pst.setBoolean(3, balcony.isSelected());
                        pst.setString(4, roomsNumber.getValue().toString());
                        pst.setString(5, bathroomsNumber.getValue().toString());
                        pst.setString(6, propertyID.getText());
                        pst.setString(7, yearBuilt.getText());
                        pst.execute();
                        pst.close();

                    } catch (SQLException throwable) {
                        throwable.printStackTrace();
                    }

                }
                try {

                    String qu = "INSERT INTO Apartment (Floor, property_ID) " + "VALUES (?,?) ";


                    PreparedStatement pst;
                    pst = DataBaseHandler.connection.prepareStatement(qu);
                    pst.setString(1, floor.getValue().toString());
                    pst.setString(2, propertyID.getText());

                    pst.execute();
                    pst.close();

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
                createAlert("Your information has been stored successfully!");

                Stage stage = (Stage) startPane.getScene().getWindow();
                stage.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            createAlert("You should enter numbers only in area,price and fees fields!");

        }
    }


    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) startPane.getScene().getWindow();
        stage.close();
    }

    public void apartmentEdit() {
        int fees = 0;
        Apartment apartment = new Apartment(Integer.parseInt(propertyID.getText()), region.getText(), address.getText(),
                Integer.parseInt(area.getText()), Integer.parseInt(yearBuilt.getText()), fees,
                Integer.parseInt(price.getText()), heating.isSelected(), parking.isSelected(), balcony.isSelected(),
                floor.getValue().toString(), roomsNumber.getValue().toString(), bathroomsNumber.getValue().toString(), true);
        if (dataBaseHandler.editPropertyForApartment(apartment)) {
            createAlert("Apartment has been edited successfully!");

        } else {
            createAlert("FAILED");
        }
    }

    public void refreshApartment(Apartment apartment) {
        floor.setItems(floorNumber);
        editMode = Boolean.TRUE;
    }

    public void refreshProperty(Property property) {
        propertyID.setText(String.valueOf(property.getProperty_ID()));
        region.setText(property.getRegion());
        address.setText(property.getAddress());
        area.setText(String.valueOf(property.getArea()));
        price.setText(String.valueOf(property.getPrice()));
        editMode = Boolean.TRUE;
        propertyID.setEditable(false);
    }

    public void refreshing() {
        propertyID.setText("");
        region.setText("");
        address.setText("");
        area.setText("");
        price.setText("");
        floor.setItems(floorNumber);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance();
        roomsNumber.setItems(numberOfRooms);
        bathroomsNumber.setItems(numberOfBathrooms);
        floor.setItems(floorNumber);
    }

    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

