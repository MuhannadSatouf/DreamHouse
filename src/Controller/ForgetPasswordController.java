package Controller;

import Models.DataBaseHandler;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class ForgetPasswordController {

    @FXML
    private JFXTextField emailTextField;

    @FXML
    void sendButton(ActionEvent event) throws SQLException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.checkIfEmail(emailTextField.getText());

    }


}
