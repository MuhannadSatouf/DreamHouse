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

public class ControllerForAddLandForSale implements Initializable {
    public JFXTextField area;
    public JFXCheckBox irrigated;
    public JFXButton saveBtn;
    public JFXComboBox type;
    public JFXTextField address;
    public JFXTextField region;
    public JFXTextField fees;
    public JFXTextField propertyID;
    public JFXTextField price;
    public JFXButton cancelBtn;
    public JFXCheckBox includesResidence;
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
