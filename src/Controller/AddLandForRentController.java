package Controller;

import Models.DataBaseHandler;
import Models.Land;
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

public class AddLandForRentController implements Initializable {
    public JFXTextField area;
    public JFXButton saveBtn;
    public JFXComboBox type;
    public JFXTextField address;
    public JFXTextField region;
    public JFXTextField price;
    public JFXTextField propertyID;
    public JFXButton cancelBtn;
    public JFXCheckBox includesResidence;
    public JFXCheckBox irrigated;
    public Pane startPane;
    private boolean editMode = Boolean.FALSE;
    DataBaseHandler dataBaseHandler;
    ObservableList<String> propertyType = FXCollections.observableArrayList("VACANT_LAND", "RANCH", "FARM", "TIMBERLAND");


    public void save(ActionEvent actionEvent) {
        if (propertyID.getText().isEmpty() || region.getText().isEmpty() || address.getText().isEmpty() ||
                price.getText().isEmpty()) {
            createAlert("Please enter in all fields");
            return;
        }

        if (editMode) {
            landEdit();
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
                dataBaseHandler.addProperty(propertyID.getText(), region.getText(), address.getText(), area.getText(), String.valueOf(fees), price.getText());
                try {
                    String qu = "INSERT INTO land (Type,Irrigated,Includes_Residence,Property_ID) " + "VALUES (?,?,?,?) ";
                    PreparedStatement pst;
                    pst = DataBaseHandler.connection.prepareStatement(qu);
                    pst.setString(1, type.getValue().toString());
                    pst.setBoolean(2, irrigated.isSelected());
                    pst.setBoolean(3, includesResidence.isSelected());
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) startPane.getScene().getWindow();
        stage.close();

    }

    public void landEdit() {
        int fees = 0;
        Land land = new Land(Integer.parseInt(propertyID.getText()), region.getText(), address.getText(), Integer.parseInt(area.getText()),
                Integer.parseInt(price.getText()), fees,
                type.getValue().toString(), irrigated.isSelected(), includesResidence.isSelected(), true);
        if (dataBaseHandler.editPropertyForLand(land)) {
            createAlert("Land has been edited successfully!");

        } else {
            createAlert("FAILED");

        }
    }

    public void refreshLand(Land land) {
        type.setItems(propertyType);
        irrigated.selectedProperty().set(false);
        includesResidence.selectedProperty().set(false);
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
        irrigated.selectedProperty().set(false);
        includesResidence.selectedProperty().set(false);
        type.setItems(propertyType);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance();
        type.setItems(propertyType);
    }

    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
