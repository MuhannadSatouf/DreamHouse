package Controller;


import Models.Customer;
import Models.DataBaseHandler;
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

public class ViewCustomerController implements Initializable {

    public Pane upPane;
    public HBox hbox;
    public Pane mainPane;
    public Pane downPane;
    public TableColumn<Customer, String> SSNCol;
    public TableColumn<Customer, String> typeCol;
    public TableColumn<Customer, String> nameCol;
    public TableColumn<Customer, String> addressCol;
    public TableColumn<Customer, String> phoneCol;
    public TableColumn<Customer, String> emailCol;
    public TableView<Customer> tableOfCustomer;

    ObservableList<Customer> customerList2 = FXCollections.observableArrayList();

    private void editCol() {
        SSNCol.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    private void loadData() {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        String qu = "SELECT DISTINCT User.SSN,User.Name,User.Address,User.Phone,User.Email,Customer.Customer_type" +
                " FROM User,Customer " +
                "WHERE User.SSN=Customer.SSN";

        ResultSet resultSet = databaseHandler.execQuery(qu);

        try {
            while (resultSet.next()) {
                String customerSsn = resultSet.getString("SSN");
                String customerName = resultSet.getString("Name");
                String customerAddress = resultSet.getString("Address");
                String customerPhone = resultSet.getString("Phone");
                String customerEmail = resultSet.getString("Email");
                String customerType = resultSet.getString("Customer_type");

                customerList2.add(new Customer(customerSsn, customerName, customerAddress, customerPhone, customerEmail, customerType));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableOfCustomer.setItems(customerList2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        editCol();
        loadData();

        hbox.prefWidthProperty().bind(upPane.widthProperty());
        hbox.prefHeightProperty().bind(upPane.heightProperty());
        upPane.prefWidthProperty().bind(mainPane.widthProperty());


        tableOfCustomer.prefWidthProperty().bind(downPane.widthProperty());
        tableOfCustomer.prefHeightProperty().bind(downPane.heightProperty());

        downPane.prefWidthProperty().bind(mainPane.widthProperty());
        downPane.prefHeightProperty().bind(mainPane.heightProperty());


    }

    public void editInfo(ActionEvent actionEvent) {
        Customer userToEdit = tableOfCustomer.getSelectionModel().getSelectedItem();
        if (userToEdit == null) {
            createAlert ("Please choose an item first!");
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addCustomerFXML.fxml"));
            Parent parent = fxmlLoader.load();
            AddCustomerController addCustomerController = fxmlLoader.getController();
            addCustomerController.refreshUser (userToEdit);
            addCustomerController.refreshCustomer (userToEdit);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Land");
            stage.setScene(new Scene (parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteInfo(ActionEvent actionEvent) {
        Customer userToEdit = tableOfCustomer.getSelectionModel().getSelectedItem();
        if (userToEdit == null) {
            createAlert ("Please choose an item first!");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Customer");
        alert.setContentText("Are you sure you want to delete this SSN number: " + userToEdit.getSsn ()+ " ?");

        Optional<ButtonType> answerOfUser = alert.showAndWait();
        if (answerOfUser.get() == ButtonType.OK) {
            boolean result = DataBaseHandler.getInstance().deleteCustomer (userToEdit);
            if (result) {
                createAlert ("Customer has been deleted successfully!");
                customerList2.remove(userToEdit);
            } else {
                createAlert ("Operation has been cancelled!");
            }
        }
    }

    public void refresh(ActionEvent actionEvent) {


    }
    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
