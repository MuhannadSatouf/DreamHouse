package Controller;

import Models.House;
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
    public Pane upPane;
    public HBox hbox;
    public TableColumn<House,Integer> propertyIDCol;
    public TableColumn<House,String> regionCol;
    public TableColumn<House,String> addressCol;
    public TableColumn<House,Integer> areaCol;
    public TableColumn<House,Integer> yearCol;
    public TableColumn<House,Integer> priceCol;
    public TableColumn<House,Boolean> heatingCol;
    public TableColumn<House,Boolean> parkingCol;
    public TableColumn<House,Boolean> balconyCol;
    public TableColumn<House,Boolean> garageCol;
    public TableColumn<House,String> roomsCol;
    public TableColumn<House,String> bathroomsCol;
    public TableColumn<House,Boolean> availabilityCol;
    //public TableColumn availableFromDateCol;
    public TableView<House> tableOfHouseForRent;

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
