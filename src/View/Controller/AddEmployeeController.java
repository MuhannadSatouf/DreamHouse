package View.Controller;

import Models.DataBaseHandler;
import Models.Employee;
import Models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    private Pane startPane;
    public JFXTextField addressTextField;
    public JFXTextField phoneTextField;
    public JFXTextField emailTextField;
    public JFXButton saveBtn;
    public JFXTextField nameTextField;
    public JFXButton cancelBtn;
    public JFXTextField employeePasswordTextField;
    public JFXComboBox type;
    public JFXTextField ssnEmployeeTextField;
    private boolean editMode = Boolean.FALSE;

    DataBaseHandler dataBaseHandler;

    public void saveEmployee(ActionEvent actionEvent) throws SQLException {

        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String check = dataBaseHandler.checkIfUserExist(ssnEmployeeTextField.getText());
        String reg1 = "[1-9][0-9]{9}";
        String reg2 = "[1-9][0-9]{11}";

        if (ssnEmployeeTextField.getText().matches(reg1) || ssnEmployeeTextField.getText().matches(reg2) && phoneTextField.getText().matches(reg1)) {
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

                    createMessage("The employee has been added to the system");
                    Stage stage = (Stage) startPane.getScene().getWindow();
                    stage.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            } else if (check.equals(ssnEmployeeTextField.getText())) {
                createMessage("The user already exist");
                refreshing();
            }


        } else {
            createMessage("The customer SSN should be 10-12 numbers and the phone number should be 10 numbers");
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

    public void refreshEmployee(Employee employee) {
        editMode = Boolean.TRUE;
    }

    public void refreshUser(User user) {
        ssnEmployeeTextField.setText(user.getSsn());
        phoneTextField.setText(user.getPhone());
        emailTextField.setText(user.getEmail());
        nameTextField.setText((user.getName()));
        addressTextField.setText(user.getAddress());
        editMode = Boolean.TRUE;
        ssnEmployeeTextField.setEditable(false);
        employeePasswordTextField.setEditable(false); // Does not work, need to modify methods
    }

    public void refreshing() {
        ssnEmployeeTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        nameTextField.setText("");
        addressTextField.setText("");
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
