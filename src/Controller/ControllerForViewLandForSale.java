package Controller;


import Models.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerForViewLandForSale implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public TableView tableOfLandForSale;
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


    ObservableList<Land> listOfLand2 = FXCollections.observableArrayList ();

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
                "property.Area,property.Price,Availability,land.Type,land.Irrigated,land.Includes_Residence "+
                "FROM property,land "+
                "WHERE property.Property_ID=land.Property_ID ";
//                + "AND fees > 0";


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
                int fees = resultSet.getInt("fees");

                listOfLand2.add (new Land (propertyID,region,address,area,price,fees,type,irrigated,includesResidence,isAvail));

            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        tableOfLandForSale.setItems (listOfLand2);

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
}

