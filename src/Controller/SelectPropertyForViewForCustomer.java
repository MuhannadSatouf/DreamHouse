package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectPropertyForViewForCustomer implements Initializable {


    public JFXButton selectHouseForSaleViewBtn;
    public JFXButton selectHouseForRentViewBtn;
    public JFXButton selectApartmentForSaleViewBtn;
    public JFXButton selectApartmentForRentViewBtn;
    public JFXButton selectCommercialForRentViewBtn;
    public JFXButton selectLandForRentViewBtn;
    public JFXButton selectCommercialForSaleViewBtn;
    public JFXButton selectLandForSaleViewBtn;




    void switchBetweenScenes(ActionEvent actionEvent, String location) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource(location));
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("/Resources/Table.Css").toExternalForm());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    void createNewStage(String location, String name) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(name);
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("/Resources/Table.Css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void selectHouseForSaleView(ActionEvent actionEvent) {

    }

    public void selectHouseForRentView(ActionEvent actionEvent) {
    }

    public void selectApartmentForSaleView(ActionEvent actionEvent) {
    }

    public void selectApartmentForRentView(ActionEvent actionEvent) {
    }

    public void selectCommercialForRentView(ActionEvent actionEvent) {
    }

    public void selectLandForRentView(ActionEvent actionEvent) {
    }

    public void selectCommercialForSaleView(ActionEvent actionEvent) {
    }

    public void selectLandForSaleView(ActionEvent actionEvent) {
    }
}
