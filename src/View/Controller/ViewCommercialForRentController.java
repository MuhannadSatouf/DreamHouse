package View.Controller;

import Models.CommercialProperty;
import Models.DataBaseHandler;
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

public class ViewCommercialForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public JFXTextField search;
    public JFXSlider slideFroPrice;
    public TableColumn<CommercialProperty, Integer> propertyIDCol;
    public TableColumn<CommercialProperty, String> regionCol;
    public TableColumn<CommercialProperty, String> addressCol;
    public TableColumn<CommercialProperty, Integer> areaCol;
    public TableColumn<CommercialProperty, Integer> priceCol;
    public TableColumn<CommercialProperty, String> typeCol;
    public TableColumn<CommercialProperty, String> floorCol;
    public TableColumn<CommercialProperty, String> availabilityCol;
    public TableView<CommercialProperty> tableOfCommercialForRent;
    public TableColumn<CommercialProperty, Integer> yearCol;

    ObservableList<CommercialProperty> listOfCommercial = FXCollections.observableArrayList();

    private void editCol() {
        propertyIDCol.setCellValueFactory(new PropertyValueFactory<>("property_ID"));
        regionCol.setCellValueFactory(new PropertyValueFactory<>("region"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("yearBuilt"));
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
    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();

        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Availability,commercial.Type,commercial.floor,commercial.Year_Built,property.fees " +
                "FROM property,commercial " +
                "WHERE property.Property_ID=commercial.Property_ID " +
                "AND fees = 0";


        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int price = resultSet.getInt("Price");
                boolean isAvail = resultSet.getBoolean("Availability");
                String type = resultSet.getString("Type");
                String floor = resultSet.getString("Floor");
                int fees = 0;
                int yearBuilt = resultSet.getInt("Year_Built");
                listOfCommercial.add(new CommercialProperty(propertyID, region, address, area, price, fees, isAvail, type, floor, yearBuilt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfCommercialForRent.setItems(listOfCommercial);
    }

    public void editInfo(ActionEvent actionEvent) {
        CommercialProperty commercialToEdit = tableOfCommercialForRent.getSelectionModel().getSelectedItem();
        if (commercialToEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please choose an item first!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addCommercialForRentFXML.fxml"));
            Parent parent = fxmlLoader.load();
            AddCommercialForRentController controllerForAddCommercialForRent = fxmlLoader.getController();
            controllerForAddCommercialForRent.refreshProperty(commercialToEdit);
            controllerForAddCommercialForRent.refreshCommercial(commercialToEdit);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Commercial");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteInfo(ActionEvent actionEvent) {
        CommercialProperty commercialToDelete = tableOfCommercialForRent.getSelectionModel().getSelectedItem();
        if (commercialToDelete == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please choose an item first!");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Commercial");
        alert.setContentText("Are you sure you want to delete this property ID number: " + commercialToDelete.getProperty_ID() + " ?");

        Optional<ButtonType> answerOfUser = alert.showAndWait();
        if (answerOfUser.get() == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance().deleteCommercial(commercialToDelete);
            if (result) {
                createMessage("Commercial has been deleted successfully!");
                listOfCommercial.remove(commercialToDelete);
            } else {
                createMessage("Operation has been cancelled!");
            }
        }
    }

    public void refresh(ActionEvent actionEvent) {
        listOfCommercial.clear();
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


        tableOfCommercialForRent.prefWidthProperty().bind(downPane.widthProperty());
        tableOfCommercialForRent.prefHeightProperty().bind(downPane.heightProperty());

        downPane.prefWidthProperty().bind(mainPane.widthProperty());
        downPane.prefHeightProperty().bind(mainPane.heightProperty());
    }

    public void mouseClick(MouseEvent mouseEvent) {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        listOfCommercial.clear();
        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Availability,commercial.Type,commercial.floor,commercial.Year_Built,property.fees " +
                "FROM property,commercial " +
                "WHERE property.Property_ID=commercial.Property_ID " +
                "AND property.Price > '" + slideFroPrice.getValue() + "' " +
                "AND fees = 0";


        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int price = resultSet.getInt("Price");
                boolean isAvail = resultSet.getBoolean("Availability");
                String type = resultSet.getString("Type");
                String floor = resultSet.getString("Floor");
                int fees = 0;
                int yearBuilt = resultSet.getInt("Year_Built");
                listOfCommercial.add(new CommercialProperty(propertyID, region, address, area, price, fees, isAvail, type, floor, yearBuilt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfCommercialForRent.setItems(listOfCommercial);

        slideFroPrice.valueProperty().addListener((observable, oldValue, newValue) -> {

            tableOfCommercialForRent.setItems(listOfCommercial);

        });

    }

    public void searchForRegion(ActionEvent actionEvent) {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        listOfCommercial.clear();
        String qu = "SELECT property.Property_ID,property.Region,property.Address," +
                "property.Area,property.Price,Availability,commercial.Type,commercial.floor,commercial.Year_Built,property.fees " +
                "FROM property,commercial " +
                "WHERE property.Property_ID=commercial.Property_ID " +
                "AND property.Region='" + search.getText() + "'" +
                "AND fees = 0";


        ResultSet resultSet = databaseHandler.execQuery(qu);
        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int price = resultSet.getInt("Price");
                boolean isAvail = resultSet.getBoolean("Availability");
                String type = resultSet.getString("Type");
                String floor = resultSet.getString("Floor");
                int fees = 0;
                int yearBuilt = resultSet.getInt("Year_Built");
                listOfCommercial.add(new CommercialProperty(propertyID, region, address, area, price, fees, isAvail, type, floor, yearBuilt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOfCommercialForRent.setItems(listOfCommercial);
    }

    public void createMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
