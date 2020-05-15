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

public class ViewLandForRentController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public Pane upPane;
    public HBox hbox;
    public TableColumn<Land, Integer> propertyIDCol;
    public TableColumn<Land, String> regionCol;
    public TableColumn<Land, String> addressCol;
    public TableColumn<Land, Integer> areaCol;
    public TableColumn<Land, Integer> yearCol;
    public TableColumn<Land, Integer> priceCol;
    public TableColumn<Land, String> typeCol;
    public TableColumn<Land, Boolean> IrrigatedCol;
    public TableColumn<Land, Boolean> availabilityCol;
    public TableColumn<Land, Boolean> residentialCol;
    public TableView<Land> tableOfLandForRent;
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
