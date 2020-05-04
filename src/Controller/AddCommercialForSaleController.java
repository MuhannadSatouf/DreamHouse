package Controller;

import Models.Apartment;
import Models.CommercialProperty;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCommercialForSaleController implements Initializable {
    public Pane startPane;
    private boolean editMode = Boolean.FALSE;
    public JFXTextField area;
    public JFXTextField yearBuilt;
    public JFXTextField fees;
    public JFXCheckBox parking;
    public JFXButton saveBtn;
    public JFXComboBox type;
    public JFXTextField address;
    public JFXTextField region;
    public JFXComboBox floor;
    public JFXTextField price;
    public JFXTextField propertyID;
    public JFXButton cancelBtn;
    DataBaseHandler dataBaseHandler;
    ObservableList<String> propertyType = FXCollections.observableArrayList ("OFFICES","RETAIL_PROPERTIES","GAS_STATIONS","STORES","RESTAURANTS");
    ObservableList<String> floorNumber = FXCollections.observableArrayList ("GROUND_FLOOR","ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","NONE","MORE");

    public void save(ActionEvent actionEvent) {
        if (propertyID.getText().isEmpty() || type.getValue().toString().isEmpty() || region.getText().isEmpty() ||
                address.getText().isEmpty() || area.getText().isEmpty() || yearBuilt.getText().isEmpty()
                || fees.getText().isEmpty() || yearBuilt.getText().isEmpty() || price.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all fields");
            alert.showAndWait();
            return;
        }
        if (editMode) {
            commercialEdit();
            return;
        }
        String query = "SELECT Property_ID from property WHERE Property_ID='" + propertyID.getText() + "'";
        ResultSet resultSet = dataBaseHandler.execQuery(query);
        try {
            if (resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("This ID was already entered!");
                alert.showAndWait();
                refreshing();
            } else {
                dataBaseHandler.addProperty(propertyID.getText(), region.getText(),
                        address.getText(), area.getText(), fees.getText(), price.getText());
                try {

                    String qu = "INSERT INTO Commercial (Type, Floor, Property_ID, Year_Built) " + "VALUES (?,?,?,?) ";

                    PreparedStatement pst;
                    pst = DataBaseHandler.connection.prepareStatement(qu);
                    pst.setString(1, type.getValue().toString());
                    pst.setString(2, floor.getValue().toString());
                    pst.setString(3, propertyID.getText());
                    pst.setString(4, yearBuilt.getText());
                    pst.execute();
                    pst.close();

                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Your information has been stored successfully!");
                alert.showAndWait();

                Stage stage = (Stage) startPane.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) startPane.getScene ().getWindow ();
        stage.close ();
    }

    public void commercialEdit() {
        int fees=0;
        CommercialProperty commercialProperty = new CommercialProperty (Integer.parseInt (propertyID.getText ()),region.getText (), address.getText (),
                Integer.parseInt (area.getText ()),Integer.parseInt (price.getText ()),fees, true,type.getValue().toString(),
                floor.getValue().toString(),Integer.parseInt(yearBuilt.getText()));
        if (dataBaseHandler.editProperty (commercialProperty)) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText (null);
            alert.setContentText ("Apartment has been edited successfully!");
            alert.show ();

        } else {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("FAILED");
            alert.show ();

        }
    }
    public void refreshCommercial () {
        type.setItems(propertyType);
        floor.setItems(floorNumber);
        yearBuilt.setText("");
        editMode = Boolean.TRUE;
    }
    public void refreshProperty(Property property) {
        propertyID.setText (String.valueOf (property.getProperty_ID ()));
        region.setText (property.getRegion ());
        address.setText (property.getAddress ());
        area.setText (String.valueOf (property.getArea ()));
        price.setText (String.valueOf (property.getPrice ()));
        fees.setText (String.valueOf (property.getFees ()));
        editMode = Boolean.TRUE;
        propertyID.setEditable (false);
    }
    public void refreshing() {
        propertyID.setText ("");
        region.setText ("");
        address.setText ("");
        area.setText ("");
        price.setText ("");
        fees.setText ("");
      type.setItems(propertyType);
      floor.setItems(floorNumber);
      yearBuilt.setText("");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance ();
        type.setItems (propertyType);
        floor.setItems (floorNumber);
    }
}
