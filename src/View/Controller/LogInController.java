package View.Controller;

import Models.DataBaseHandler;
import Models.PassWordHash;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LogInController implements Initializable {

    public Pane startPane;
    @FXML
    private JFXTextField SSNInput, passwordInput;
    public ProgressIndicator progressBar;
    public ProgressBar progressBar2;
    private boolean isManager;
    private boolean isEmployee;
    private boolean isCustomer;


    public void register(ActionEvent actionEvent) throws IOException {

        viewWindow("/View/addCustomerFXML.fxml", "Add Customer");

    }

    public void signIn(ActionEvent actionEvent) throws IOException, SQLException {
        progressBar.setVisible(true);
        validateLogin();
        if (isManager) {

            viewWindow("/View/mainMenuForManager.fxml", "Menu");
        } else if (isEmployee) {
            viewWindow("/View/mainMenuForEmployee.fxml", "Menu");
        } else if (isCustomer) {
            viewWindow("/View/mainMenuForCustomer.fxml", "Menu");

        }
        Stage stage = (Stage) startPane.getScene().getWindow();
        stage.close();
    }


    public boolean validateLogin() throws SQLException {
        final String secretKey = "ssshhhhhhhhhhh!!!!";

        if (SSNInput.getText().equals("") || passwordInput.getText().equals("")) {
            errorMassage();
        } else {
            DataBaseHandler DBHandler = new DataBaseHandler();
            String check = DBHandler.checkLogInIfEmployee(SSNInput.getText(), new PassWordHash().encrypt(passwordInput.getText(), secretKey));
            switch (check) {
                case "1":
                    isManager = true;
                    return true;
                case "2":
                    isEmployee = true;
                    return true;
                case "10":
                    DataBaseHandler dataBaseHandler = new DataBaseHandler();
                    String check2 = dataBaseHandler.checkLogInIfCustomer(SSNInput.getText(), new PassWordHash().encrypt(passwordInput.getText(), secretKey));
                    switch (check2) {
                        case "1":
                            isCustomer = true;
                            return true;
                        case "0":
                            errorMassage();
                            return false;
                    }
            }
        }
        return false;
    }


    private void errorMassage() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "The username or password are incorrect", ButtonType.OK);
        alert.setTitle("Error");
        alert.setHeaderText("Error: Information missing!");
        SSNInput.setText("");
        passwordInput.setText("");
        alert.showAndWait();
    }

    void viewWindow(String location, String name) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(name);
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void forgetPassword(ActionEvent event) {
        viewWindow("/View/forgetPassword.fxml", "Forget password");
    }


    @FXML
    void aboutUsButton(ActionEvent event) {
        viewWindow("/View/aboutUs.fxml", "About DREAM HOUSE");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // These for testing the database and inlogning and will active them only one time to create them in the database if we use local database


      /* DataBaseHandler dataBaseHandler = null;
        try {
           dataBaseHandler = new DataBaseHandler ();
       } catch (SQLException throwables) {
           throwables.printStackTrace ();
       }
       dataBaseHandler.addUsersForTesting();*/

    }
}

