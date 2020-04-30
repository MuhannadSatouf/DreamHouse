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
    public TableColumn<Land, Integer> propertyIDCol;
    public TableColumn<Land, String> regionCol;
    public TableColumn<Land, String> addressCol;
    public TableColumn<Land, Integer> areaCol;
    public TableColumn<Land, Integer> yearCol;
    public TableColumn<Land, Integer> priceCol;
    public TableColumn<Land, String> typeCol;
    public TableColumn<Land, Boolean> IrrigatedCol;
    public Pane upPane;
    public HBox hbox;
    public TableColumn<Land, Boolean> availabilityCol;
    public TableView tableOfLandForSale;
    public TableColumn<Land, Boolean> residentialCol;
    public TableColumn<ForSale, String> feesCol;


    ObservableList<Land> listOfLand1 = FXCollections.observableArrayList ();
    ObservableList<Land> listOfLand2 = FXCollections.observableArrayList ();
    ObservableList<ForSale> listOfLand3 = FXCollections.observableArrayList ();


    private void editCol() {
        propertyIDCol.setCellValueFactory (new PropertyValueFactory<> ("property_ID"));
        regionCol.setCellValueFactory (new PropertyValueFactory<> ("region"));
        addressCol.setCellValueFactory (new PropertyValueFactory<> ("address"));
        areaCol.setCellValueFactory (new PropertyValueFactory<> ("area"));
        yearCol.setCellValueFactory (new PropertyValueFactory<> ("yearBuilt"));
        priceCol.setCellValueFactory (new PropertyValueFactory<> ("price"));
        availabilityCol.setCellValueFactory (new PropertyValueFactory<> ("propertyAvailability"));
        IrrigatedCol.setCellValueFactory (new PropertyValueFactory<> ("irrigated"));
        residentialCol.setCellValueFactory (new PropertyValueFactory<> ("includesResidence"));
        typeCol.setCellValueFactory (new PropertyValueFactory<> ("type"));
        feesCol.setCellValueFactory (new PropertyValueFactory<> ("fees"));

        //feesCol.setCellValueFactory (new Callback<TableColumn.CellDataFeatures<ForSale, String>, ObservableValue<String>> ()  {
     /*       @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ForSale, String> forSaleIntegerCellDataFeatures) {
                return forSaleIntegerCellDataFeatures.getValue ().feesProperty () ;

            }
        });*/





    }


    private void loadFees (){
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();
        String qu ="SELECT Fees FROM forsale WHERE Property.Property_ID=forsale.Property_ID";
        ResultSet resultSet = databaseHandler.execQuery (qu);
        try {
            while (resultSet.next ()) {
                int fees = resultSet.getInt ("Fees");
                //  listOfLand3.add (new ForSale (fees));

            }

        }catch (SQLException e){
            e.printStackTrace ();
        }



    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();


        String qu = "SELECT Property.Property_ID,property.Region,Property.Address,Property.Area,Property.Year,Property.Price,Property.Availability," +
                "land.Type,land.Irrigated,land.Includes_Residence," +
                "forsale.Fees " +
                "FROM Property,land,forsale " +
                "WHERE Property.Property_ID=land.Property_ID " +
                "AND Property.Property_ID=forsale.Property_ID";


        ResultSet resultSet = databaseHandler.execQuery (qu);
        try {
            while (resultSet.next ()) {
                int propertyID = resultSet.getInt ("Property_ID");
                String region = resultSet.getString ("Region");
                String address = resultSet.getString ("Address");
                int area = resultSet.getInt ("Area");
                int year = resultSet.getInt ("Year");
                int price = resultSet.getInt ("Price");
                boolean isAvail = resultSet.getBoolean ("Availability");
                String type = resultSet.getString ("Type");
                boolean irrigated = resultSet.getBoolean ("Irrigated");
                boolean includesResidence = resultSet.getBoolean ("Includes_Residence");
                //  int fees = resultSet.getInt ("Fees");
                // listOfLand1.add (new Property (propertyID,region,address,area,year,price,isAvail));
                listOfLand2.add (new Land (propertyID, region, address, area, year, price, isAvail, type, irrigated, includesResidence));
                //listOfLand3.add (new ForSale (propertyID,region,address,area,year,price,isAvail,fees));



            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        tableOfLandForSale.setItems (listOfLand2);
        tableOfLandForSale.setItems (listOfLand3);

        // tableOfLandForSale.setItems (listOfLand1);

        // tableOfLandForSale.setItems (listOfLand3);


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

