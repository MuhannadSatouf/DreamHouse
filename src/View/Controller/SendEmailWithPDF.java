package View.Controller;

import Models.DataBaseHandler;
import Models.EmailSend;
import Models.PDF;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
                break;
            case "0":
                break;
        }
    }
}
