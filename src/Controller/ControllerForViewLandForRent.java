package Controller;

import Models.DataBaseHandler;
import Models.Land;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Optional;
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

    public TableColumn availableFromCol;
    public ContextMenu contextTable;

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

    public void editInfo(ActionEvent actionEvent) {
    }

/*
    public void deleteInfo(ActionEvent actionEvent) {
        Land landToDelete = tableOfLandForRent.getSelectionModel ().getSelectedItem ();
        if (landToDelete == null) {
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ("Please choose a land first!");
            return;

        }
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Delete Book");
        alert.setContentText ("Are you sure want to delete the book" + landToDelete.getProperty_ID () + "?");

        Optional<ButtonType> answerOfUser = alert.showAndWait ();

        if (answerOfUser.get () == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance ().deleteLandForRentFromDatabase (landToDelete);
            if (result) {
                alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText (null);
                alert.setContentText ("Book was deleted successfully");
                alert.show ();
                listOfLand.remove (landToDelete);

            } else {

                alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText (null);
                alert.setContentText ("Failed");
                alert.show ();
            }

  }
        }
*/








    public void deleteInfo(ActionEvent actionEvent) {
    }

    public void refresh(ActionEvent actionEvent) {
    }
}
