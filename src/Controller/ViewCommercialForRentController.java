package Controller;

import Models.CommercialProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCommercialForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public TableColumn<CommercialProperty,Integer> propertyIDCol;
    public TableColumn<CommercialProperty,String> regionCol;
    public TableColumn<CommercialProperty,String> addressCol;
    public TableColumn<CommercialProperty,Integer> areaCol;
    public TableColumn<CommercialProperty,Integer> priceCol;
    public TableColumn<CommercialProperty,String> typeCol;
    public TableColumn<CommercialProperty,String> floorCol;
    public TableColumn<CommercialProperty,Boolean> availabilityCol;
    public TableView<CommercialProperty> tableOfCommercialForRent;
    public TableColumn <CommercialProperty,Integer>yearCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfCommercialForRent.prefWidthProperty ().bind (downPane.widthProperty ());
       tableOfCommercialForRent.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
}
