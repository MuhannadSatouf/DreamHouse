package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SelectPropertyForViewForCustomer implements Initializable {


    public SelectPropertyForViewForCustomer() throws SQLException {
    }

    public void selectHouseForView(ActionEvent event) throws IOException {

        makeChangesOnController("/View/selectPropertyHouseForViewFXML.fxml", event);

    }

    public void selectApartmentForView(ActionEvent event) {
        switchBetweenScenes(event, "/View/selectPropertyApartmentForViewFXML.fxml");
    }

    public void selectLandForView(ActionEvent event) {
        switchBetweenScenes(event, "/View/selectPropertyLandForViewFXML.fxml");
    }

    public void selectCommercialForView(ActionEvent event) throws IOException {
        // switchBetweenScenes(event, "/View/selectPropertyCommercialForViewFXML.fxml");
        makeChangesOnController("/View/selectPropertyCommercialForViewFXML.fxml", event);
    }


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

    public void makeChangesOnController(String location, ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);


            MainMenuController main = fxmlLoader.getController();
            main.addPropertyBtn.setVisible(false);
            main.addUserBtn.setVisible(false);
            main.transactionsBtn.setVisible(false);
            main.statisticsBtn.setVisible(false);
            main.viewPropertyBtn.setVisible(false);
            main.viewUserBtn.setVisible(false);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
