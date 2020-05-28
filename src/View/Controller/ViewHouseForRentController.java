package View.Controller;

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

public class ViewHouseForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public JFXTextField search;
    public JFXSlider slideFroPrice;
    public TableColumn<House, Integer> propertyIDCol;
    public TableColumn<House, String> regionCol;
    public TableColumn<House, String> addressCol;
    public TableColumn<House, Integer> areaCol;
    public TableColumn<House, Integer> yearCol;
    public TableColumn<House, Integer> priceCol;
    public TableColumn<House, String> heatingCol;
    public TableColumn<House, String> parkingCol;
    public TableColumn<House, String> balconyCol;
    public TableColumn<House, String> garageCol;
    public TableColumn<House, String> roomsCol;
    public TableColumn<House, String> bathroomsCol;
    public TableColumn<House, String> availabilityCol;
    public TableView<House> tableOfHouseForRent;

    ObservableList<House> listOfHouse = FXCollections.observableArrayList();

    private void editCol() {
        propertyIDCol.setCellValueFactory(new PropertyValueFactory<>("property_ID"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearBuilt"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        parkingCol.setCellValueFactory(new PropertyValueFactory<>("parking"));
        balconyCol.setCellValueFactory(new PropertyValueFactory<>("balcony"));
        garageCol.setCellValueFactory(new PropertyValueFactory<>("garage"));
        roomsCol.setCellValueFactory(new PropertyValueFactory<>("room"));
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
        garageCol.setCellValueFactory(cellData -> {
            boolean availabilityValue = cellData.getValue().isGarage();
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
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();

        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Property.Availability,Resident.heating,Resident.parking," +
                "Resident.balcony,Resident.rooms,Resident.bathrooms,Resident.year,house.garage,property.fees " +
                "FROM property,resident,house " +
                "WHERE property.Property_ID=house.Property_ID " +
                "AND property.Property_ID=Resident.Property_ID " +
                "And fees = 0";

        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int year = resultSet.getInt("Year");
                int price = resultSet.getInt("Price");
                boolean isAvail = resultSet.getBoolean("Availability");
                boolean heating = resultSet.getBoolean("Heating");
                boolean parking = resultSet.getBoolean("Parking");
                boolean balcony = resultSet.getBoolean("Balcony");
                boolean garage = resultSet.getBoolean("Garage");
                String rooms = resultSet.getString("Rooms");
                String bathrooms = resultSet.getString("Bathrooms");
                int fees = 0;
                listOfHouse.add(new House(propertyID, region, address, area, year, fees, price, heating, parking, balcony, rooms, bathrooms, isAvail, garage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfHouseForRent.setItems(listOfHouse);
    }

    public void editInfo(ActionEvent actionEvent) {
        House HouseToEdit = tableOfHouseForRent.getSelectionModel().getSelectedItem();
        if (HouseToEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please choose an item first!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addHouseForRentFXML.fxml"));
            Parent parent = fxmlLoader.load();
            AddHouseForRentController controllerForAddHouseForRent = fxmlLoader.getController();
            controllerForAddHouseForRent.refreshProperty(HouseToEdit);
            controllerForAddHouseForRent.refreshHouse(HouseToEdit);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit House");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteInfo(ActionEvent actionEvent) {
        House HouseToDelete = tableOfHouseForRent.getSelectionModel().getSelectedItem();
        if (HouseToDelete == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please choose an item first!");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete House");
        alert.setContentText("Are you sure you want to delete this property ID number: " + HouseToDelete.getProperty_ID() + " ?");

        Optional<ButtonType> answerOfUser = alert.showAndWait();
        if (answerOfUser.get() == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance().deleteHouse(HouseToDelete);
            if (result) {
                createMessage("House has been deleted successfully!");
                listOfHouse.remove(HouseToDelete);
            } else {
                createMessage("Operation has been cancelled!");
            }
        }
    }


    public void refresh(ActionEvent actionEvent) {
        listOfHouse.clear();
        editCol();
        loadData();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCol();
        loadData();
        hbox.prefWidthProperty().bind(upPane.widthProperty());
        hbox.prefHeightProperty().bind(upPane.heightProperty());
        upPane.prefWidthProperty().bind(mainPane.widthProperty());

        tableOfHouseForRent.prefWidthProperty().bind(downPane.widthProperty());
        tableOfHouseForRent.prefHeightProperty().bind(downPane.heightProperty());

        downPane.prefWidthProperty().bind(mainPane.widthProperty());
        downPane.prefHeightProperty().bind(mainPane.heightProperty());
    }

    public void mouseClick(MouseEvent mouseEvent) {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        listOfHouse.clear();
        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Property.Availability,Resident.heating,Resident.parking," +
                "Resident.balcony,Resident.rooms,Resident.bathrooms,Resident.year,house.garage,property.fees " +
                "FROM property,resident,house " +
                "WHERE property.Property_ID=house.Property_ID " +
                "AND property.Property_ID=Resident.Property_ID " +
                "AND property.Price > '" + slideFroPrice.getValue() + "' " +
                "And fees = 0";

        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int year = resultSet.getInt("Year");
                int price = resultSet.getInt("Price");
                boolean isAvail = resultSet.getBoolean("Availability");
                boolean heating = resultSet.getBoolean("Heating");
                boolean parking = resultSet.getBoolean("Parking");
                boolean balcony = resultSet.getBoolean("Balcony");
                boolean garage = resultSet.getBoolean("Garage");
                String rooms = resultSet.getString("Rooms");
                String bathrooms = resultSet.getString("Bathrooms");
                int fees = 0;
                listOfHouse.add(new House(propertyID, region, address, area, year, fees, price, heating, parking, balcony, rooms, bathrooms, isAvail, garage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfHouseForRent.setItems(listOfHouse);

        slideFroPrice.valueProperty().addListener((observable, oldValue, newValue) -> {

            tableOfHouseForRent.setItems(listOfHouse);

        });

    }

    public void searchForRegion(ActionEvent actionEvent) {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        listOfHouse.clear();
        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Property.Availability,Resident.heating,Resident.parking," +
                "Resident.balcony,Resident.rooms,Resident.bathrooms,Resident.year,house.garage,property.fees " +
                "FROM property,resident,house " +
                "WHERE property.Property_ID=house.Property_ID " +
                "AND property.Property_ID=Resident.Property_ID " +
                "AND property.Region='" + search.getText() + "'" +
                "And fees = 0";

        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int year = resultSet.getInt("Year");
                int price = resultSet.getInt("Price");
                boolean isAvail = resultSet.getBoolean("Availability");
                boolean heating = resultSet.getBoolean("Heating");
                boolean parking = resultSet.getBoolean("Parking");
                boolean balcony = resultSet.getBoolean("Balcony");
                boolean garage = resultSet.getBoolean("Garage");
                String rooms = resultSet.getString("Rooms");
                String bathrooms = resultSet.getString("Bathrooms");
                int fees = 0;
                listOfHouse.add(new House(propertyID, region, address, area, year, fees, price, heating, parking, balcony, rooms, bathrooms, isAvail, garage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOfHouseForRent.setItems(listOfHouse);
    }

    public void createMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
