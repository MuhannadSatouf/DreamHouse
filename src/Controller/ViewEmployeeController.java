package Controller;

import Models.Customer;
import Models.DataBaseHandler;
import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {
    public Pane mainPane;
    public Pane downPane;
    public HBox hbox;
    public Pane upPane;
    public TableView<Employee> tableOfEmployee;
    public TableColumn<Employee,String> SSNCol;
   // public TableColumn<Employee,String >typeCol; // no column called type! should call Role
    public TableColumn<Employee,String> nameCol;
    public TableColumn<Employee,String> addressCol;
    public TableColumn<Employee,String> phoneCol;
    public TableColumn<Employee,String> emailCol;

    ObservableList<Employee> employeeList = FXCollections.observableArrayList ();

    private void editCol() {
        SSNCol.setCellValueFactory (new PropertyValueFactory<>("ssn"));
        nameCol.setCellValueFactory (new PropertyValueFactory<> ("name"));
        addressCol.setCellValueFactory (new PropertyValueFactory<> ("address"));
        phoneCol.setCellValueFactory (new PropertyValueFactory<> ("phone"));
        emailCol.setCellValueFactory (new PropertyValueFactory<> ("email"));
       // typeCol.setCellValueFactory (new PropertyValueFactory<> ("type"));


    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();
        String qu = "SELECT User.SSN,User.Name,User.Address,User.Phone,User.Email,Employee.Employee_type" +
                " FROM User,Employee " +
                "WHERE User.SSN=Employee.SSN" ;
        //   String qu2 =       " SELECT Customer_type FROM Customer";

        ResultSet resultSet = databaseHandler.execQuery (qu);




        try {
            while (resultSet.next ()) {
                String employeeSsn = resultSet.getString ("SSN");
                String employeeName = resultSet.getString ("Name");
                String employeeAddress = resultSet.getString ("Address");
                String employeePhone = resultSet.getString ("Phone");
                String employeeEmail = resultSet.getString ("Email");
               // String employeeType =resultSet.getString ("Employee_type");


               // employeeList.add (new Employee (employeeSsn,employeeName,employeeAddress,employeePhone,employeeEmail,....));


            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        tableOfEmployee.setItems (employeeList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editCol ();
        loadData ();
        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfEmployee.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfEmployee.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());
    }
}
