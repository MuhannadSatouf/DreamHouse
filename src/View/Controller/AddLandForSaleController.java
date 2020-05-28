package View.Controller;

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


public class AddLandForSaleController implements Initializable {

    public Pane startPane;
    private boolean editMode = Boolean.FALSE;
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

    public void save(ActionEvent actionEvent) throws SQLException {


        if (propertyID.getText().isEmpty() || region.getText().isEmpty() || address.getText().isEmpty() ||
                price.getText().isEmpty() || fees.getText().isEmpty()) {
            createAlert("Please enter in all fields");
            return;
        }
//add this to all add controller
        String reg = "[0-9]+";
        if (propertyID.getText().matches(reg) & area.getText().matches(reg) & price.getText().matches(reg) & fees.getText().matches(reg)) {
            if (editMode) {
                landEdit();
                return;
            }
            String query = "SELECT Property_ID from property WHERE Property_ID='" + propertyID.getText() + "'";
            ResultSet resultSet = dataBaseHandler.execQuery(query);
         /*   PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (query);
            pst.execute ();*/

            try {
                if (resultSet.next()) {
                    createAlert("This ID was already entered!");
                    refreshing();
                } else {
                    dataBaseHandler.addProperty(propertyID.getText(), region.getText(), address.getText(),
                            area.getText(), fees.getText(), price.getText());
                    try {
                        String qu = "INSERT INTO land (Type,Irrigated,Includes_Residence,Property_ID) " + "VALUES (?,?,?,?) ";
                        PreparedStatement pst;
                        pst = DataBaseHandler.connection.prepareStatement(qu);
                        try {
                            pst.setString(1, type.getValue().toString());
                        } catch (NullPointerException e) {
                            createAlert("Please enter land type first!");
                            return;

                        }

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

        } else {
            createAlert("You should enter numbers only in area,price and fees fields!");

        }

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) startPane.getScene().getWindow();
        stage.close();
    }


    public void landEdit() {
        Land land = new Land(Integer.parseInt(propertyID.getText()), region.getText(), address.getText(), Integer.parseInt(area.getText()),
                Integer.parseInt(price.getText()), Integer.parseInt(fees.getText()),
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
        fees.setText(String.valueOf(property.getFees()));
        editMode = Boolean.TRUE;
        propertyID.setEditable(false);
    }

    public void refreshing() {
        propertyID.setText("");
        region.setText("");
        address.setText("");
        area.setText("");
        price.setText("");
        fees.setText("");
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












