package Controller;

import Models.Apartment;
import Models.DataBaseHandler;
import Models.House;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.input.MouseEvent;
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

public class ViewApartmentForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public JFXTextField search;
    public JFXSlider slideFroPrice;
    public TableColumn<Apartment, Integer> propertyIDCol;
    public TableColumn<Apartment, String> regionCol;
    public TableColumn<Apartment, String> addressCol;
    public TableColumn<Apartment, Integer> areaCol;
    public TableColumn<Apartment, Integer> yearCol;
    public TableColumn<Apartment, Integer> priceCol;
    public TableColumn<Apartment,String> heatingCol;
    public TableColumn<Apartment,String> parkingCol;
    public TableColumn<Apartment,String> balconyCol;
    public TableColumn<Apartment, String> floorCol;
    public TableColumn<Apartment, String> roomsCol;
    public TableColumn<Apartment, String> bathroomsCol;
    public TableColumn<Apartment, String> availabilityCol;
    public TableView<Apartment> tableOfApartmentForRent;

    ObservableList<Apartment> listOfApartment = FXCollections.observableArrayList();

    private void editCol() {
        propertyIDCol.setCellValueFactory(new PropertyValueFactory<>("property_ID"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearBuilt"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        roomsCol.setCellValueFactory(new PropertyValueFactory<>("rooms"));
        bathroomsCol.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
        availabilityCol.setCellValueFactory(cellData -> {
            boolean availabilityValue = cellData.getValue().isPropertyAvailability();
            String isAvailable;
            if (availabilityValue) {
                isAvailable = "Available";
            } else {
                isAvailable = "Rented";
            }

            return new ReadOnlyStringWrapper(isAvailable);
        });
        heatingCol.setCellValueFactory(cellData -> {
            boolean availabilityValue = cellData.getValue().isHeating();
            String isAvailable;
            if (availabilityValue) {
                isAvailable = "✔";
            } else {
                isAvailable = "✘";
            }

            return new ReadOnlyStringWrapper(isAvailable);
        });
        parkingCol.setCellValueFactory(cellData -> {
            boolean availabilityValue = cellData.getValue().isParking();
            String isAvailable;
            if (availabilityValue) {
                isAvailable = "✔";
            } else {
                isAvailable = "✘";
            }

            return new ReadOnlyStringWrapper(isAvailable);
        });
        balconyCol.setCellValueFactory(cellData -> {
            boolean availabilityValue = cellData.getValue().isBalcony();
            String isAvailable;
            if (availabilityValue) {
                isAvailable = "✔";
            } else {
                isAvailable = "✘";
            }

            return new ReadOnlyStringWrapper(isAvailable);
        });
    }
    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();

        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Property.Availability,Resident.heating," +
                "Resident.parking,Resident.balcony,Resident.rooms,Resident.bathrooms," +
                "Resident.year,apartment.floor,property.fees " +
                "FROM property,resident,apartment " +
                "WHERE property.Property_ID=apartment.Property_ID " +
                "AND property.Property_ID=Resident.Property_ID " +
                "And fees = 0";


        ResultSet resultSet = databaseHandler.execQuery (qu);
        try {
            while (resultSet.next ()) {
                int propertyID = resultSet.getInt ("Property_ID");
                String region = resultSet.getString ("Region");
                String address = resultSet.getString ("Address");
                int area = resultSet.getInt ("Area");
                int year = resultSet.getInt("Year");
                int price = resultSet.getInt ("Price");
                boolean isAvail = resultSet.getBoolean ("Availability");
                boolean heating = resultSet.getBoolean ("Heating");
                boolean parking = resultSet.getBoolean ("Parking");
                boolean balcony = resultSet.getBoolean ("Balcony");
                String floor = resultSet.getString ("Floor");
                String rooms = resultSet.getString ("Rooms");
                String bathrooms = resultSet.getString ("Bathrooms");
                int fees = 0;
                listOfApartment.add (new Apartment(propertyID, region, address, area, year, fees, price, heating, parking,balcony,floor,rooms,bathrooms,isAvail));
            }

        } catch (SQLException e) {
            e.printStackTrace ();
        }
        tableOfApartmentForRent.setItems (listOfApartment);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCol();
        loadData();

        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfApartmentForRent.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfApartmentForRent.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
    public void editInfo(ActionEvent actionEvent) {
        Apartment apartmentToEdit = tableOfApartmentForRent.getSelectionModel ().getSelectedItem ();
        if (apartmentToEdit == null) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please choose an item first!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader (getClass ().getResource ("/View/addApartmentForRentFXML.fxml"));
            Parent parent = fxmlLoader.load ();
            AddApartmentForRentController controllerForAddApartmentForRent = fxmlLoader.getController ();

            controllerForAddApartmentForRent.refreshProperty (apartmentToEdit);
            controllerForAddApartmentForRent.refreshApartment (apartmentToEdit);
            Stage stage = new Stage (StageStyle.DECORATED);
            stage.setTitle ("Edit Apartment");
            stage.setScene (new Scene(parent));
            stage.show ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
    public void deleteInfo(ActionEvent actionEvent) {
        Apartment apartmentToDelete = tableOfApartmentForRent.getSelectionModel ().getSelectedItem ();
        if (apartmentToDelete == null) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please choose an item first!");
            return;
        }
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Delete Apartment");
        alert.setContentText ("Are you sure you want to delete this property ID number: " + apartmentToDelete.getProperty_ID () + " ?");

        Optional<ButtonType> answerOfUser = alert.showAndWait ();
        if (answerOfUser.get () == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance ().deleteApartment (apartmentToDelete);
            if (result) {
                alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText (null);
                alert.setContentText ("The apartment has been deleted successfully!");
                alert.show ();
                listOfApartment.remove (apartmentToDelete);
            } else {
                alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText (null);
                alert.setContentText ("Operation has been cancelled!");
                alert.show ();
            }
        }
    }

    public void refresh(ActionEvent actionEvent) {
        listOfApartment.clear ();
        editCol ();
        loadData ();
    }

    public void searchForRegion(ActionEvent actionEvent) {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        listOfApartment.clear();
        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Property.Availability,Resident.heating,Resident.parking,Resident.balcony,Resident.rooms,Resident.bathrooms,Resident.year,apartment.floor,property.fees " +
                "FROM property,resident,apartment " +
                "WHERE property.Property_ID=apartment.Property_ID " +
                "AND property.Property_ID=Resident.Property_ID " +
                "AND property.Region='" + search.getText() + "'" +
                "And fees = 0";

        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next ()) {
                int propertyID = resultSet.getInt ("Property_ID");
                String region = resultSet.getString ("Region");
                String address = resultSet.getString ("Address");
                int area = resultSet.getInt ("Area");
                int year = resultSet.getInt("Year");
                int price = resultSet.getInt ("Price");
                boolean isAvail = resultSet.getBoolean ("Availability");
                boolean heating = resultSet.getBoolean ("Heating");
                boolean parking = resultSet.getBoolean ("Parking");
                boolean balcony = resultSet.getBoolean ("Balcony");
                String floor = resultSet.getString ("Floor");
                String rooms = resultSet.getString ("Rooms");
                String bathrooms = resultSet.getString ("Bathrooms");
                int fees = 0;
                listOfApartment.add (new Apartment(propertyID, region, address, area, year, fees, price, heating, parking,balcony,floor,rooms,bathrooms,isAvail));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOfApartmentForRent.setItems(listOfApartment);
    }

    public void mouseClick(MouseEvent mouseEvent) {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        listOfApartment.clear();
        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Property.Availability,Resident.heating,Resident.parking,Resident.balcony,Resident.rooms,Resident.bathrooms,Resident.year,apartment.floor,property.fees " +
                "FROM property,resident,apartment " +
                "WHERE property.Property_ID=apartment.Property_ID " +
                "AND property.Property_ID=Resident.Property_ID " +
                "AND property.Price > '" + slideFroPrice.getValue() + "' " +
                "And fees = 0";

        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next ()) {
                int propertyID = resultSet.getInt ("Property_ID");
                String region = resultSet.getString ("Region");
                String address = resultSet.getString ("Address");
                int area = resultSet.getInt ("Area");
                int year = resultSet.getInt("Year");
                int price = resultSet.getInt ("Price");
                boolean isAvail = resultSet.getBoolean ("Availability");
                boolean heating = resultSet.getBoolean ("Heating");
                boolean parking = resultSet.getBoolean ("Parking");
                boolean balcony = resultSet.getBoolean ("Balcony");
                String floor = resultSet.getString ("Floor");
                String rooms = resultSet.getString ("Rooms");
                String bathrooms = resultSet.getString ("Bathrooms");
                int fees = 0;
                listOfApartment.add (new Apartment(propertyID, region, address, area, year, fees, price, heating, parking,balcony,floor,rooms,bathrooms,isAvail));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfApartmentForRent.setItems(listOfApartment);

        slideFroPrice.valueProperty().addListener((observable, oldValue, newValue) -> {

            tableOfApartmentForRent.setItems(listOfApartment);

        });

    }
}
