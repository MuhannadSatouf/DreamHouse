package Controller;

import Models.Apartment;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewApartmentForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public TableColumn<Apartment, Integer> propertyIDCol;
    public TableColumn<Apartment, String> regionCol;
    public TableColumn<Apartment, String> addressCol;
    public TableColumn<Apartment, Integer> areaCol;
    public TableColumn<Apartment, Integer> yearCol;
    public TableColumn<Apartment, Integer> priceCol;
    public TableColumn<Apartment, Boolean> heatingCol;
    public TableColumn<Apartment, Boolean> parkingCol;
    public TableColumn<Apartment, Boolean> balconyCol;
    public TableColumn<Apartment, String> floorCol;
    public TableColumn<Apartment, String> roomsCol;
    public TableColumn<Apartment, String> bathroomsCol;
    public TableColumn<Apartment, Boolean> availabilityCol;
    public TableView<Apartment> tableOfApartmentForRent;
    public TableColumn<Apartment, Integer> availableFromDateCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfApartmentForRent.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfApartmentForRent.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
}
