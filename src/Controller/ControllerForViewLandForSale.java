package Controller;


import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerForViewLandForSale implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public TableView<Land> tableOfLandForSale;
    public TableColumn<Land, Integer> propertyIDCol;
    public TableColumn<Land, String> regionCol;
    public TableColumn<Land, String> addressCol;
    public TableColumn<Land, Integer> areaCol;
    public TableColumn<Land, Integer> yearCol;
    public TableColumn<Land, Integer> priceCol;
    public TableColumn<Land, String> typeCol;
    public TableColumn<Land, Boolean> IrrigatedCol;
    public TableColumn<Land, Boolean> availabilityCol;
    public TableColumn<Land, Boolean> residentialCol;
    public TableColumn<Land, Integer> feesCol;


    ObservableList<Land> listOfLand = FXCollections.observableArrayList ();

    private void editCol() {
        propertyIDCol.setCellValueFactory (new PropertyValueFactory<> ("property_ID"));
        regionCol.setCellValueFactory (new PropertyValueFactory<> ("region"));
        addressCol.setCellValueFactory (new PropertyValueFactory<> ("address"));
        areaCol.setCellValueFactory (new PropertyValueFactory<> ("area"));
        priceCol.setCellValueFactory (new PropertyValueFactory<> ("price"));
        feesCol.setCellValueFactory (new PropertyValueFactory<> ("fees"));
        typeCol.setCellValueFactory (new PropertyValueFactory<> ("type"));
        IrrigatedCol.setCellValueFactory (new PropertyValueFactory<> ("irrigated"));
        residentialCol.setCellValueFactory (new PropertyValueFactory<> ("includesResidence"));
        availabilityCol.setCellValueFactory (new PropertyValueFactory<> ("propertyAvailability"));
    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();

        String qu = "SELECT property.fees,property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Availability,land.Type,land.Irrigated,land.Includes_Residence " +
                "FROM property,land " +
                "WHERE property.Property_ID=land.Property_ID "
                + "AND fees > 0";
        ResultSet resultSet = databaseHandler.execQuery (qu);
        try {
            while (resultSet.next ()) {
                int propertyID = resultSet.getInt ("Property_ID");
                String region = resultSet.getString ("Region");
                String address = resultSet.getString ("Address");
                int area = resultSet.getInt ("Area");
                int price = resultSet.getInt ("Price");
                boolean isAvail = resultSet.getBoolean ("Availability");
                String type = resultSet.getString ("Type");
                boolean irrigated = resultSet.getBoolean ("Irrigated");
                boolean includesResidence = resultSet.getBoolean ("Includes_Residence");
                int fees = resultSet.getInt ("fees");
                listOfLand.add (new Land (propertyID, region, address, area, price, fees, type, irrigated, includesResidence, isAvail));
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        tableOfLandForSale.setItems (listOfLand);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCol ();
        loadData ();
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        tableOfLandForSale.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfLandForSale.prefHeightProperty ().bind (downPane.heightProperty ());
        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }

    public void editInfo(ActionEvent actionEvent) {
        Land landToEdit = tableOfLandForSale.getSelectionModel ().getSelectedItem ();
        if (landToEdit == null) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please choose an item first!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader (getClass ().getResource ("/View/addLandForSaleFXML.fxml"));
            Parent parent = fxmlLoader.load ();
            ControllerForAddLandForSale controllerForAddLandForSale = fxmlLoader.getController ();
            controllerForAddLandForSale.refreshProperty (landToEdit);
            controllerForAddLandForSale.refreshLand (landToEdit);
            Stage stage = new Stage (StageStyle.DECORATED);
            stage.setTitle ("Edit Land");
            stage.setScene (new Scene (parent));
            stage.show ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }


    public void deleteInfo(ActionEvent actionEvent) {
        Land landToDelete = tableOfLandForSale.getSelectionModel ().getSelectedItem ();
        if (landToDelete == null) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please choose an item first!");
            return;
        }
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Delete Land");
        alert.setContentText ("Are you sure you want to delete this property ID number: " + landToDelete.getProperty_ID () + " ?");

        Optional<ButtonType> answerOfUser = alert.showAndWait ();
        if (answerOfUser.get () == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance ().deleteLand (landToDelete);
            if (result) {
                alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText (null);
                alert.setContentText ("Land has been deleted successfully!");
                alert.show ();
                listOfLand.remove (landToDelete);
            } else {
                alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText (null);
                alert.setContentText ("Operation has been cancelled!");
                alert.show ();
            }
        }
    }


    public void refresh(ActionEvent actionEvent) {
        listOfLand.clear ();
        editCol ();
        loadData ();
    }
}








