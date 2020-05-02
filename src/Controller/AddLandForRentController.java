package Controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
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
    public DatePicker availableFrom;

    ObservableList<String> propertyType = FXCollections.observableArrayList ("VACANT_LAND","RANCH","FARM","TIMBERLAND");
    public void save(ActionEvent actionEvent) {

    }

    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setItems (propertyType);
    }
}
