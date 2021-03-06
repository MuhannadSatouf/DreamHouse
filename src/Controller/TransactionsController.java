package Controller;

import Models.DataBaseHandler;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    public Label getRegionSale;
    public Label getPriceSale;
    public Label getNameSale;
    public Label getAddressSaleCustomer;
    public Label getPhoneSale;
    public Label getEmailSale;
    public Label getRegionRent;
    public Label getAddressRent;
    public Label getPriceRent;
    public Label getNameRent;
    public Label getAddressRentCustomer;
    public Label getPhoneRent;
    public Label getEmailRent;
    public Label getFeesSale;
    public Label availableForSale;
    public Label getAddressSale;
    public Label availableForRent;
    public JFXTextField customerSSNSale;
    public JFXTextField propertySaleID;
    public JFXTextField customerRentSSN;
    public JFXTextField propertyRentID;
    DataBaseHandler dataBaseHandler;

    public void sellProperty(ActionEvent actionEvent) {
        String customerSSN = customerSSNSale.getText ();
        String propertyIDSale = propertySaleID.getText ();
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Sell Confirmation");
        alert.setHeaderText (null);
        alert.setContentText ("Are you sure to Sell this property?");

        Optional<ButtonType> response = alert.showAndWait ();
        if (customerSSNSale.getText ().isEmpty () || propertySaleID.getText ().isEmpty ()) {
            createAlert ("Please fill both property ID and Customer SSN");
        } else {
            if (response.get () == ButtonType.OK) {
                String st = "INSERT INTO user_has_property (Property_ID,SSN) VALUES ("
                        + "'" + propertyIDSale + "'," +
                        "'" + customerSSN + "')";
                String st2 = "UPDATE Property SET Availability = false WHERE Property_ID= '" + propertyIDSale + "'";

                String qu = "SELECT * FROM Property WHERE Property_ID = '" + propertyIDSale + "'";
                ResultSet resultSet = dataBaseHandler.execQuery (qu);

                try {
                    while (resultSet.next ()) {
                        boolean available = resultSet.getBoolean ("Availability");
                        if (available && dataBaseHandler.execAction (st) && dataBaseHandler.execAction (st2)) {

                            createAlert ("Property has been SOLD!");
                        } else {
                            Alert alert2 = new Alert (Alert.AlertType.ERROR);
                            alert2.setTitle ("FAILED");
                            alert2.setHeaderText (null);
                            alert2.setContentText ("This property is already Sold.");
                            alert2.showAndWait ();
                            return;
                        }

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace ();
                }


            }
        }
    }


    public void rentProperty(javafx.event.ActionEvent actionEvent) {
        String customerSSN = customerRentSSN.getText ();
        String propertyIDRent = propertyRentID.getText ();
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("Rent Confirmation");
        alert.setHeaderText (null);
        alert.setContentText ("Are you sure to Rent this property?");
        Optional<ButtonType> response = alert.showAndWait ();
        if (response.get () == ButtonType.OK) {
            String st = "INSERT INTO user_has_property (Property_ID,SSN) VALUES ("
                    + "'" + propertyIDRent + "'," +
                    "'" + customerSSN + "')";
            String st2 = "UPDATE Property SET Availability = false WHERE Property_ID= '" + propertyIDRent + "'";

            String qu = "SELECT * FROM Property WHERE Property_ID = '" + propertyIDRent + "'";
            ResultSet resultSet = dataBaseHandler.execQuery (qu);

            try {
                while (resultSet.next ()) {
                    boolean available = resultSet.getBoolean ("Availability");
                    if (available && dataBaseHandler.execAction (st) && dataBaseHandler.execAction (st2)) {

                        createAlert ("Property has been RENT!");
                    } else {
                        Alert alert2 = new Alert (Alert.AlertType.ERROR);
                        alert2.setTitle ("FAILED");
                        alert2.setHeaderText (null);
                        alert2.setContentText ("This property is already Rented!");
                        alert2.showAndWait ();
                        return;
                    }

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }


        }

    }

    public void loadCustomerSaleInfo(ActionEvent actionEvent) {
        clearCustomerSaleLabel ();
        String customerSSN = customerSSNSale.getText ();
        String qu = "SELECT * FROM User WHERE SSN = '" + customerSSN + "'";
        ResultSet resultSet = dataBaseHandler.execQuery (qu);
        boolean cont = false;

        try {
            while (resultSet.next ()) {
                String name = resultSet.getString ("Name");
                String address = resultSet.getString ("Address");
                String phone = resultSet.getString ("Phone");
                String email = resultSet.getString ("Email");

                getNameSale.setText (name);
                getAddressSaleCustomer.setText (address);
                getPhoneSale.setText (phone);
                getEmailSale.setText (email);
                cont = true;
            }
            if (!cont) {
                createAlert ("Please enter a correct Social Security Number!");
                customerSSNSale.setText ("");
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }


    public void loadPropertySaleInfo(ActionEvent actionEvent) {
        clearPropertySaleLabel ();
        String propertyIDSale = propertySaleID.getText ();
        String qu = "SELECT * FROM Property WHERE Property_ID = '" + propertyIDSale + "'";
        ResultSet resultSet = dataBaseHandler.execQuery (qu);
        boolean cont = false;

        try {
            while (resultSet.next ()) {
                boolean available = resultSet.getBoolean ("Availability");
                String region = resultSet.getString ("Region");
                String propertyAddress = resultSet.getString ("Address");
                String price = resultSet.getString ("Price");
                String fees = resultSet.getString ("Fees");
                if (available) {
                    availableForSale.setText ("Available");
                    availableForSale.setStyle ("-fx-background-color: GREEN");
                } else {
                    availableForSale.setText ("Sold");
                    availableForSale.setStyle ("-fx-background-color: RED");
                }
                getRegionSale.setText (region);
                getAddressSale.setText (propertyAddress);
                getPriceSale.setText (price);
                getFeesSale.setText (fees);
                cont = true;
            }
            if (!cont) {
                createAlert ("Please enter a correct Property ID");
                propertySaleID.setText ("");
                availableForSale.setText ("");
                availableForSale.setStyle ("-fx-background-color: ");

            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }


    public void loadPropertyRentInfo(ActionEvent actionEvent) {
        clearPropertyRentLabel ();
        String propertyIDRent = propertyRentID.getText ();
        String qu = "SELECT * FROM Property WHERE Property_ID = '" + propertyIDRent + "'";
        ResultSet resultSet = dataBaseHandler.execQuery (qu);
        boolean cont = false;

        try {
            while (resultSet.next ()) {
                boolean available = resultSet.getBoolean ("Availability");
                String region = resultSet.getString ("Region");
                String propertyAddress = resultSet.getString ("Address");
                String price = resultSet.getString ("Price");
                if (available) {
                    availableForRent.setText ("Available");
                    availableForRent.setStyle ("-fx-background-color: GREEN");
                } else {
                    availableForRent.setText ("Rent");
                    availableForRent.setStyle ("-fx-background-color: RED");
                }
                getRegionRent.setText (region);
                getAddressRent.setText (propertyAddress);
                getPriceRent.setText (price);

                cont = true;
            }
            if (!cont) {
                createAlert ("Please enter a correct Property ID");
                propertyRentID.setText ("");
                availableForRent.setText ("");
                availableForRent.setStyle ("-fx-background-color: ");

            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void loadCustomerRentInfo(ActionEvent actionEvent) {
        clearCustomerRentLabel ();
        String customerSSN = customerRentSSN.getText ();
        String qu = "SELECT * FROM User WHERE SSN = '" + customerSSN + "'";
        ResultSet resultSet = dataBaseHandler.execQuery (qu);
        boolean cont = false;

        try {
            while (resultSet.next ()) {
                String name = resultSet.getString ("Name");
                String address = resultSet.getString ("Address");
                String phone = resultSet.getString ("Phone");
                String email = resultSet.getString ("Email");

                getNameRent.setText (name);
                getAddressRentCustomer.setText (address);
                getPhoneRent.setText (phone);
                getEmailRent.setText (email);
                cont = true;
            }
            if (!cont) {
                createAlert ("Please enter a correct Social Security Number!");
                customerRentSSN.setText ("");
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    void clearPropertySaleLabel() {
        availableForSale.setText ("");
        getRegionSale.setText ("");
        getAddressSale.setText ("");
        getPriceSale.setText ("");
        getFeesSale.setText ("");
    }

    void clearCustomerSaleLabel() {
        getNameSale.setText ("");
        getAddressSaleCustomer.setText ("");
        getPhoneSale.setText ("");
        getEmailSale.setText ("");

    }

    void clearPropertyRentLabel() {

        getRegionRent.setText ("");
        getAddressRent.setText ("");
        getPriceRent.setText ("");

    }

    void clearCustomerRentLabel() {

        getNameRent.setText ("");
        getAddressRentCustomer.setText ("");
        getPhoneRent.setText ("");
        getEmailRent.setText ("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataBaseHandler = DataBaseHandler.getInstance ();
    }


    public void createAlert(String message) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setHeaderText (null);
        alert.setContentText (message);
        alert.showAndWait ();
    }
}
