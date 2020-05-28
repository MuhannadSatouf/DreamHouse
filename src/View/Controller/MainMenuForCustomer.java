package View.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class MainMenuForCustomer implements Initializable {
    LogInController logInController = new LogInController();


    public MainMenuForCustomer() throws SQLException {
    }

    @FXML
    public void sendEmailWithPdf(ActionEvent event) throws SQLException {

        logInController.viewWindow("/View/SendEmailWithPDF.fxml", "Send an email");

    }

    public void viewProperty(ActionEvent event) throws IOException {
        makeChangesOnController("/View/SelectPropertyForViewForCustomer.fxml", event);
    }


    public void selectHouseForView(ActionEvent actionEvent) {
        makeChangesOnController("/View/selectPropertyHouseForViewFXML.fxml", actionEvent);
    }

    public void selectApartmentForView(ActionEvent actionEvent) {
        makeChangesOnController("/View/selectPropertyApartmentForViewFXML.fxml", actionEvent);
    }

    public void selectLandForView(ActionEvent actionEvent) {
        makeChangesOnController("/View/selectPropertyLandForViewFXML.fxml", actionEvent);
    }

    public void selectCommercialForView(ActionEvent actionEvent) {
        makeChangesOnController("/View/selectPropertyCommercialForViewFXML.fxml", actionEvent);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void makeChangesOnController(String location, ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(location));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

          //  MainMenuController main = fxmlLoader.getController();
          //  main.addPropertyBtn.setVisible(false);
           // main.addUserBtn.setVisible(false);
          //  main.transactionsBtn.setVisible(false);
          //  main.statisticsBtn.setVisible(false);
          //  main.viewPropertyBtn.setVisible(false);
          //  main.viewUserBtn.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
