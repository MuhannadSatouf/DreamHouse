package Controller;

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

public class AddCommercialForRentController implements Initializable {
    public Pane startPane;
    private boolean editMode = Boolean.FALSE;
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


    DataBaseHandler dataBaseHandler;
    ObservableList<String> propertyType = FXCollections.observableArrayList("OFFICES", "RETAIL_PROPERTIES", "GAS_STATIONS", "STORES", "RESTAURANTS");
    ObservableList<String> floorNumber = FXCollections.observableArrayList("GROUND_FLOOR", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "NONE", "MORE");

    public void save(ActionEvent actionEvent) {
        if (propertyID.getText().isEmpty() || type.getValue().toString().isEmpty() || region.getText().isEmpty() ||
                address.getText().isEmpty() || area.getText().isEmpty() || yearBuilt.getText().isEmpty()
                || yearBuilt.getText().isEmpty() || price.getText().isEmpty()) {
            createAlert("Please enter in all fields");
            return;
        }
        String reg = "[0-9]+";
        if (area.getText().matches(reg) & price.getText().matches(reg) & yearBuilt.getText().matches(reg)) {
            if (editMode) {
                commercialEdit();
                return;
            }
            String query = "SELECT Property_ID from property WHERE Property_ID='" + propertyID.getText() + "'";
            ResultSet resultSet = dataBaseHandler.execQuery(query);
            try {
                if (resultSet.next()) {
                    createAlert("This ID was already entered!");
                    refreshing();

                } else {
                    int fees = 0;
                    dataBaseHandler.addProperty(propertyID.getText(), region.getText(),
                            address.getText(), area.getText(), String.valueOf(fees), price.getText());
                    try {

                        String qu = "INSERT INTO Commercial (Type, Floor, Year_Built,Property_ID ) " + "VALUES (?,?,?,?) ";

                        PreparedStatement pst;
                        pst = DataBaseHandler.connection.prepareStatement(qu);
                        pst.setString(1, type.getValue().toString());
                        pst.setString(2, floor.getValue().toString());
                        pst.setString(3, yearBuilt.getText());
                        pst.setString(4, propertyID.getText());
                        pst.execute();
                        pst.close();

                    } catch (SQLException throwable) {
                        throwable.printStackTrace();
                    }
                    createAlert("Your information has been stored successfully!");

                    Stage stage = (Stage) startPane.getScene().getWindow();
                    stage.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            createAlert("You should enter numbers only in area,price and fees fields!");

        }
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void commercialEdit() {
        int fees = 0;
        CommercialProperty commercialProperty = new CommercialProperty(Integer.parseInt(propertyID.getText()), region.getText(), address.getText(),
                Integer.parseInt(area.getText()), Integer.parseInt(price.getText()), fees, true, type.getValue().toString(),
                floor.getValue().toString(), Integer.parseInt(yearBuilt.getText()));
        if (dataBaseHandler.editPropertyForCommercial(commercialProperty)) {
            createAlert("Commercial property has been edited successfully!");

        } else {
            createAlert("FAILED");

        }
    }

    public void refreshCommercial(CommercialProperty commercialProperty) {
        type.setItems(propertyType);
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
        yearBuilt.setText("");
        type.setItems(propertyType);
        floor.setItems(floorNumber);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance();
        type.setItems(propertyType);
        floor.setItems(floorNumber);
    }

    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
