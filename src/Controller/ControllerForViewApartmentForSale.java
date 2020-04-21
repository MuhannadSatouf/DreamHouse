package Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForViewApartmentForSale implements Initializable {
    public TableView tableOfApartmentForSale;
    public TableColumn propertyIDCol;
    public TableColumn regionCol;
    public TableColumn addressCol;
    public TableColumn areaCol;
    public TableColumn yearCol;
    public TableColumn feesCol;
    public TableColumn priceCol;
    public TableColumn heatingCol;
    public TableColumn parkingCol;
    public TableColumn balconyCol;
    public TableColumn floorCol;
    public TableColumn roomsCol;
    public TableColumn bathroomsCol;
    public Pane downPane;
    public Pane upPane;
    public Pane mainPane;

    public ImageView imageView;
    public HBox hbox;
    public TableColumn availabilityCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


       hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
       upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfApartmentForSale.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfApartmentForSale.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());




    }
}
