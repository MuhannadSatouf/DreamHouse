package Controller;

import Models.DataBaseHandler;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public HBox hbox;
    public Pane upPane;
    public TableView<Employee> tableOfEmployee;
    public TableColumn<Employee, String> SSNCol;
    public TableColumn<Employee, String> nameCol;
    public TableColumn<Employee, String> addressCol;
    public TableColumn<Employee, String> phoneCol;
    public TableColumn<Employee, String> emailCol;

    ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    private void editCol() {
        SSNCol.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        String qu = "SELECT DISTINCT User.SSN,User.Name,User.Address,User.Phone,User.Email" +
                " FROM User,Employee " +
                "WHERE User.SSN=Employee.SSN";
        ResultSet resultSet = databaseHandler.execQuery(qu);

        try {
            while (resultSet.next()) {
                String employeeSsn = resultSet.getString("SSN");
                String employeeName = resultSet.getString("Name");
                String employeeAddress = resultSet.getString("Address");
                String employeePhone = resultSet.getString("Phone");
                String employeeEmail = resultSet.getString("Email");

                employeeList.add(new Employee(employeeSsn, employeeName, employeeAddress, employeePhone, employeeEmail));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableOfEmployee.setItems(employeeList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCol();
        loadData();
        hbox.prefWidthProperty().bind(upPane.widthProperty());
        hbox.prefHeightProperty().bind(upPane.heightProperty());
        upPane.prefWidthProperty().bind(mainPane.widthProperty());


        tableOfEmployee.prefWidthProperty().bind(downPane.widthProperty());
        tableOfEmployee.prefHeightProperty().bind(downPane.heightProperty());

        downPane.prefWidthProperty().bind(mainPane.widthProperty());
        downPane.prefHeightProperty().bind(mainPane.heightProperty());
    }

    public void editInfo(ActionEvent actionEvent) {
        Employee employeeToEdit = tableOfEmployee.getSelectionModel().getSelectedItem();
        if (employeeToEdit == null) {
            createAlert("Please choose an employee first!");

            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addEmployeeFXML.fxml"));
            Parent parent = fxmlLoader.load();
            AddEmployeeController controllerForAddEmployee = fxmlLoader.getController();
            controllerForAddEmployee.refreshUser(employeeToEdit);
            controllerForAddEmployee.refreshEmployee(employeeToEdit);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Employee");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteInfo(ActionEvent actionEvent) {
        Employee employeeToDelete = tableOfEmployee.getSelectionModel().getSelectedItem();
        if (employeeToDelete == null) {
            createAlert("Please choose an employee first!");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Employee");
        alert.setContentText("Are you sure you want to delete this employee: " + employeeToDelete.getSsn() + " ?");

        Optional<ButtonType> answerOfUser = alert.showAndWait();
        if (answerOfUser.get() == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance().deleteEmployee(employeeToDelete);
            if (result) {
                createAlert("Employee has been deleted successfully!");
                employeeList.remove(employeeToDelete);
            } else {

                createAlert("Operation has been cancelled!");
            }
        }
    }

    public void refresh(ActionEvent actionEvent) {
        employeeList.clear();
        editCol();
        loadData();
    }

    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
