package Controller;


import Models.Customer;
import Models.Customer;
import Models.DataBaseHandler;
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

public class ViewCustomerController implements Initializable {
    public Pane upPane;
    public HBox hbox;
    public Pane mainPane;
    public Pane downPane;
    public TableView<Customer> tableOfCustomer;
    public TableColumn <Customer,String>SSNCol;
    public TableColumn <Customer,String> typeCol;
    public TableColumn <Customer,String> nameCol;
    public TableColumn <Customer,String> addressCol;
    public TableColumn <Customer,String> phoneCol;
    public TableColumn <Customer,String> emailCol;
    public TableColumn <Customer,String> passCol;


    ObservableList<Customer>customerList2 = FXCollections.observableArrayList ();

    private void editCol() {
        SSNCol.setCellValueFactory (new PropertyValueFactory <>("ssn"));
        nameCol.setCellValueFactory (new PropertyValueFactory<> ("name"));
        addressCol.setCellValueFactory (new PropertyValueFactory<> ("address"));
        phoneCol.setCellValueFactory (new PropertyValueFactory<> ("phone"));
        emailCol.setCellValueFactory (new PropertyValueFactory<> ("email"));
        typeCol.setCellValueFactory (new PropertyValueFactory<> ("type"));


    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();
        String qu = "SELECT User.SSN,User.Name,User.Address,User.Phone,User.Email,Customer.Customer_type" +
                " FROM User,Customer " +
                "WHERE User.SSN=Customer.SSN" ;
        //   String qu2 =       " SELECT Customer_type FROM Customer";

        ResultSet resultSet = databaseHandler.execQuery (qu);




        try {
            while (resultSet.next ()) {
                String customerSsn = resultSet.getString ("SSN");
                String customerName = resultSet.getString ("Name");
                String customerAddress = resultSet.getString ("Address");
                String customerPhone = resultSet.getString ("Phone");
                String customerEmail = resultSet.getString ("Email");
                String customerType =resultSet.getString ("Customer_type");


                customerList2.add (new Customer (customerSsn,customerName,customerAddress,customerPhone,customerEmail,customerType));


            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }


        tableOfCustomer.setItems (customerList2);



    }









    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        editCol ();
        loadData ();

        hbox.prefWidthProperty ().bind (upPane.widthProperty ());
        hbox.prefHeightProperty ().bind (upPane.heightProperty ());
        upPane.prefWidthProperty ().bind (mainPane.widthProperty ());


        tableOfCustomer.prefWidthProperty ().bind (downPane.widthProperty ());
        tableOfCustomer.prefHeightProperty ().bind (downPane.heightProperty ());

        downPane.prefWidthProperty ().bind (mainPane.widthProperty ());
        downPane.prefHeightProperty ().bind (mainPane.heightProperty ());


    }
}
