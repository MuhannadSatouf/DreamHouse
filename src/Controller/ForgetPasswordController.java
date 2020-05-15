package Controller;

import Models.DataBaseHandler;
import Models.EmailSend;
import Models.PassWordHash;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class ForgetPasswordController {
    final String secretKey = "ssshhhhhhhhhhh!!!!";
    String passwordFromDatabase = "";

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
                break;
            case "0":
                System.out.println("The email not correct");
                break;
                // will be here an alert
        }
    }


}
