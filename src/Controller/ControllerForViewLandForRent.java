package Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerForViewLandForRent implements Initializable {
    public Pane mainPane;
    public Pane downPane;

    public TableColumn propertyIDCol;
    public TableColumn regionCol;
    public TableColumn addressCol;
    public TableColumn areaCol;
    public TableColumn yearCol;
    public TableColumn priceCol;
    public TableColumn heatingCol;
    public TableColumn typeCol;
    public TableColumn IrrigatedCol;
    public Pane upPane;
    public HBox hbox;
    public TableColumn availabilityCol;
    public TableView tableOfLandForRent;
    public TableColumn residentialCol;
    public TableColumn availableFromDateCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfLandForRent.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfLandForRent.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
}
