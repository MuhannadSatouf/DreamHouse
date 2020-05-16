package Controller;

import Models.DataBaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    public JFXTextField address;
    public JFXTextField phone;
    public JFXTextField email;
    public JFXButton saveBtn;
    public JFXTextField name;
    public JFXButton cancelBtn;
    public JFXTextField employeePassword;
    public JFXComboBox type;
    public JFXTextField ssnEmployee;
    ObservableList<String> employeeTypeList = FXCollections.observableArrayList ("Sales agent", "Buyer's agent", "Real estate support staff");
    DataBaseHandler dataBaseHandler;

    public void saveEmployee(ActionEvent actionEvent) {
        if (ssnEmployee.getText().isEmpty() || address.getText().isEmpty() || name.getText().isEmpty() ||
                phone.getText().isEmpty() || email.getText().isEmpty() || employeePassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter in all fields");
            alert.showAndWait();
            return;
        }
        String reg = "[0-9]+";
        if (ssnEmployee.getText().matches(reg) & phone.getText().matches(reg)) {

            dataBaseHandler.addUser(ssnEmployee.getText(), employeePassword.getText(),
                    name.getText(), address.getText(), phone.getText(), email.getText());

            try {

                String qu = "INSERT INTO employee (Role,SSN)" +
                        "VALUES(?,?)";

                PreparedStatement pst;
                pst = DataBaseHandler.connection.prepareStatement(qu);
                pst.setString(1, type.getValue().toString());
                pst.setString(2, ssnEmployee.getText());

                pst.execute();
                pst.close();

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You should enter numbers only in SSN and Phone fields!");
            alert.showAndWait();

        }
    }

    public void cancel(ActionEvent actionEvent) {
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance ();
        type.setItems (employeeTypeList);
    }
}
