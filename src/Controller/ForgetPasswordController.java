package Controller;

import Models.DataBaseHandler;
import Models.EmailSend;
import Models.PassWordHash;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ForgetPasswordController {
    final String secretKey = "ssshhhhhhhhhhh!!!!";


    @FXML
    private AnchorPane startPane;

    @FXML
    private JFXTextField emailTextField;

    @FXML
    void sendButton(ActionEvent event) throws SQLException {
        EmailSend emailSend = new EmailSend();
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.checkIfEmail(emailTextField.getText());
        String check = dataBaseHandler.checkIfEmail(emailTextField.getText());
        switch (check) {
            case "1":
                emailSend.sendEmailWithPassword(emailTextField.getText(), "Welcome to Dream house\n " +
                                "You have sent a request to remember your password. \n This is your password: ",
                        new PassWordHash().decrypt(dataBaseHandler.getPasswordFromDatabase(emailTextField.getText()), secretKey));

                createAlert("The email has been sent, Thanks");
                Stage stage = (Stage) startPane.getScene().getWindow();
                stage.close();
                break;
            case "0":
                System.out.println("The email not correct");
                break;
            // will be here an alert
        }
    }


    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
