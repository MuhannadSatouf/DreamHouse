package Controller;

import Models.Apartment;
import Models.DataBaseHandler;
import Models.Land;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerForViewApartmentForSale implements Initializable {
    public Pane downPane;
    public Pane upPane;
    public Pane mainPane;
    public ImageView imageView;
    public HBox hbox;
    public TableView<Apartment> tableOfApartmentForSale;
    public TableColumn<Apartment, Integer> propertyIDCol;
    public TableColumn<Apartment, String> regionCol;
    public TableColumn<Apartment, String> addressCol;
    public TableColumn<Apartment, Integer> areaCol;
    public TableColumn<Apartment, Integer> yearCol;
    public TableColumn<Apartment, Integer> feesCol;
    public TableColumn<Apartment, Integer> priceCol;
    public TableColumn<Apartment, Boolean> heatingCol;
    public TableColumn<Apartment, Boolean> parkingCol;
    public TableColumn<Apartment, Boolean> balconyCol;
    public TableColumn<Apartment, String> floorCol;
    public TableColumn<Apartment, String> roomsCol;
    public TableColumn<Apartment, String> bathroomsCol;
    public TableColumn<Apartment, Boolean> availabilityCol;

    ObservableList<Apartment> listOfApartmentForSale = FXCollections.observableArrayList();

    private void editCol() {
        propertyIDCol.setCellValueFactory(new PropertyValueFactory<>("property_ID"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearBuilt"));
        feesCol.setCellValueFactory(new PropertyValueFactory<>("fees"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        heatingCol.setCellValueFactory(new PropertyValueFactory<>("heating"));
        parkingCol.setCellValueFactory(new PropertyValueFactory<>("parking"));
        balconyCol.setCellValueFactory(new PropertyValueFactory<>("balcony"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        roomsCol.setCellValueFactory(new PropertyValueFactory<>("rooms"));
        bathroomsCol.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("propertyAvailability"));


    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();

        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,property.fees,property.year,Availability," +
                "resident.heating,resident.parking,resident.balcony,resident.rooms,resident.bathrooms,resident.year " +
                ",apartment.floor "+
                "FROM property, resident, apartment " +
                "WHERE property.Property_ID = resident.Property_ID " +
                "AND property.Property_ID = apartment.Property_ID ";

        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int yearBuilt = resultSet.getInt("Year");
                int fees = resultSet.getInt("fees");
                int price = resultSet.getInt("Price");
                boolean heating = resultSet.getBoolean("heating");
                boolean parking = resultSet.getBoolean("parking");
                boolean balcony = resultSet.getBoolean("balcony");
                String floor = resultSet.getString("floor");
                String room = resultSet.getString("room");
                String bathroom = resultSet.getString("bathroom");
                boolean isAvail = resultSet.getBoolean("Availability");

                listOfApartmentForSale.add(new Apartment(propertyID,  region,  address,  area,  yearBuilt,  fees,
                 price,  heating,  parking,  balcony, floor,  room,  bathroom,
                        isAvail));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfApartmentForSale.setItems(listOfApartmentForSale);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCol();
        loadData();

        hbox.prefWidthProperty().bind(upPane.widthProperty());
        hbox.prefHeightProperty().bind(upPane.heightProperty());
        upPane.prefWidthProperty().bind(mainPane.widthProperty());


        tableOfApartmentForSale.prefWidthProperty().bind(downPane.widthProperty());
        tableOfApartmentForSale.prefHeightProperty().bind(downPane.heightProperty());

        downPane.prefWidthProperty().bind(mainPane.widthProperty());
        downPane.prefHeightProperty().bind(mainPane.heightProperty());


    }
}
