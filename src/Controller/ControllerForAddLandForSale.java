package Controller;


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

    DataBaseHandler dataBaseHandler;
    ObservableList<String> propertyType = FXCollections.observableArrayList("Vacant Land", "Ranch", "Farm", "Timberland");

    public void save(ActionEvent actionEvent) {

        if (propertyID.getText().isEmpty() || region.getText().isEmpty() || address.getText().isEmpty() ||
                price.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all fields");
            alert.showAndWait();
            return;
        }
<<<<<<< HEAD

        dataBaseHandler.addProperty (propertyID.getText (),region.getText (),address.getText (),area.getText (),price.getText (),fees.getText());

=======
        dataBaseHandler.addPropertyForSale(propertyID.getText(), region.getText(), address.getText(), area.getText(), fees.getText(),price.getText());
>>>>>>> d65d5f0b29bc6bdbddcadb89173e2a9fd5db4b20

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


    }


    public void cancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance();
        type.setItems(propertyType);
    }
}
