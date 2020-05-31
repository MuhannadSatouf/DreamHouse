package Controller;

import Models.DataBaseHandler;
import Models.EmailSend;
import Models.PDF;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class SendEmailWithPDF {
    @FXML
    private JFXTextField emailTextField, propertyIdTextField;
    DataBaseHandler dataBaseHandler = new DataBaseHandler();

    public SendEmailWithPDF() throws SQLException {
    }


    public void SendButton(ActionEvent event) throws SQLException {
        PDF pdf = new PDF();
        pdf.createPDF(propertyIdTextField.getText());
        EmailSend emailSend = new EmailSend();
        String check = dataBaseHandler.checkIfEmail(emailTextField.getText());

        switch (check) {
            case "1":
                emailSend.sendEmailWithPDF(emailTextField.getText(), 3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("The email has been sent");
                alert.showAndWait();
                break;
            case "0":
                break;
        }
    }
}
