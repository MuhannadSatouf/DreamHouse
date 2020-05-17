package Controller;

import Models.DataBaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    public JFXTextField addressTextField;
    public JFXTextField phoneTextField;
    public JFXTextField emailTextField;
    public JFXButton saveBtn;
    public JFXTextField nameTextField;
    public JFXButton cancelBtn;
    public JFXTextField employeePasswordTextField;
    public JFXComboBox type;
    public JFXTextField ssnEmployeeTextField;


    DataBaseHandler dataBaseHandler;

    public void saveEmployee(ActionEvent actionEvent) throws SQLException {

        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String check = dataBaseHandler.checkIfUserExist(ssnEmployeeTextField.getText());

        if (!check.equals(ssnEmployeeTextField.getText())) {

            dataBaseHandler.addUser(ssnEmployeeTextField.getText(), employeePasswordTextField.getText(),
                    nameTextField.getText(), addressTextField.getText(), phoneTextField.getText(), emailTextField.getText());

            try {

                String qu = "INSERT INTO employee (Role,SSN)" +
                        "VALUES(?,?)";

                PreparedStatement pst;
                pst = DataBaseHandler.connection.prepareStatement(qu);
                pst.setString(1, "Employee");
                pst.setString(2, ssnEmployeeTextField.getText());

                pst.execute();
                pst.close();

                createMessage("Thank your, the employee has been added to the system");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else if (check.equals(ssnEmployeeTextField.getText())) {
            createMessage("The user already exist");
        }
    }

    public void cancel(ActionEvent actionEvent) {
        employeePasswordTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
        employeePasswordTextField.setText("");
        ssnEmployeeTextField.setText("");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeSSNOnlyNumbers();
        makeTheSaveButtonActive();
        makeNameOnlyString();
        dataBaseHandler = DataBaseHandler.getInstance();

    }

    private void makeSSNOnlyNumbers() {
        // make the textField ssn and phone take only numbers.
        ssnEmployeeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") && newValue.length() <= 12) {
                ssnEmployeeTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        phoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                phoneTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void createMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // make ths save button active when all fields are filled
    public void makeTheSaveButtonActive() {
        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(ssnEmployeeTextField.textProperty(), employeePasswordTextField.textProperty(), phoneTextField.textProperty()
                        , emailTextField.textProperty(), nameTextField.textProperty(), addressTextField.textProperty(),
                        nameTextField.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (ssnEmployeeTextField.getText().isEmpty() || employeePasswordTextField.getText().isEmpty()
                        || phoneTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || nameTextField.getText().isEmpty()
                        || addressTextField.getText().isEmpty() || nameTextField.getText().isEmpty());
            }
        };

        saveBtn.disableProperty().bind(bb);
    }

    public void makeNameOnlyString() {
        nameTextField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!newValue.matches("\\D*")) {
                    nameTextField.setText(newValue.replaceAll("[^\\D]", ""));
                }
            }
        });
    }
}
