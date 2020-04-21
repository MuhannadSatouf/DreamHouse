package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {


    public ProgressIndicator progressBar;
    public ProgressBar progressBar2;

    public void register(ActionEvent actionEvent) throws IOException {

        viewWindow ("/View/addCustomerFXML.fxml", "Add Customer");

    }

    public void signIn(ActionEvent actionEvent) throws IOException {
        progressBar.setVisible (true);

        viewWindow ("/View/mainMenuFXML.fxml", "Menu");

    }

    void viewWindow(String location, String name) {
        try {
            Parent parent = FXMLLoader.load (getClass ().getResource (location));
            Stage stage = new Stage (StageStyle.DECORATED);
            stage.setTitle (name);
            stage.setScene (new Scene (parent));
            stage.show ();


        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    progressBar.setVisible (false);



    }



}
