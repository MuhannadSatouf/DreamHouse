package Controller;

import Models.DataBaseHandler;
import Models.Land;
import Models.Property;
import com.jfoenix.controls.*;
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
import java.time.format.DateTimeFormatter;
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
    public Pane startPane;
    private boolean editMode = Boolean.FALSE;
    DataBaseHandler dataBaseHandler;
    ObservableList<String> propertyType = FXCollections.observableArrayList ("VACANT_LAND", "RANCH", "FARM", "TIMBERLAND");


    public void save(ActionEvent actionEvent) {



    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) startPane.getScene ().getWindow ();
        stage.close ();

    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       dataBaseHandler=DataBaseHandler.getInstance ();
        type.setItems (propertyType);
    }
}
