package src.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewHouseForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;

    public TableColumn propertyIDCol;
    public TableColumn regionCol;
    public TableColumn addressCol;
    public TableColumn areaCol;
    public TableColumn yearCol;
    public TableColumn priceCol;
    public TableColumn heatingCol;
    public TableColumn parkingCol;
    public TableColumn balconyCol;
    public TableColumn garageCol;
    public TableColumn roomsCol;
    public TableColumn bathroomsCol;
    public Pane upPane;
    public HBox hbox;
    public TableView tableOfHouseForRent;
    public TableColumn availabilityCol;
    public TableColumn availableFromDateCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfHouseForRent.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfHouseForRent.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
}