package Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;

public class DataBaseHandler {

    private static DataBaseHandler databaseHandler = null;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection connection = null;
    public static Statement statement = null;
    String passwordFromDatabase = "";
    final String secretKey = "ssshhhhhhhhhhh!!!!";
    PreparedStatement preparedStatement;

    public static DataBaseHandler getInstance() {
        if (databaseHandler == null) {
            try {
                databaseHandler = new DataBaseHandler ();
            } catch (SQLException throwables) {
                throwables.printStackTrace ();
            }
        }
        return databaseHandler;
    }


    public DataBaseHandler() throws SQLException {
        createConnection ();
    }


    void createConnection() {
        try {

            Class.forName (DRIVER).newInstance ();

            // This url for connecting to Remote database
            connection = DriverManager.getConnection ("jdbc:mysql://den1.mysql6.gear.host/dreamhousedata?useSSL=false", "dreamhousedata", "Rx5XS11M7~5~");

            // This url to connect to local database
            // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");

            System.out.println ("Connection success");
        } catch (Exception e) {

            e.printStackTrace ();
        }
    }


    public void addProperty(String property_Id, String region, String address, String area, String fees, String price) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,fees) " +
                    "VALUES(?,?,?,?,?,?)";


            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);
            pst.setString (1, property_Id);
            pst.setString (2, region);
            pst.setString (3, address);
            pst.setString (4, area);
            pst.setString (5, price);
            pst.setString (6, fees);
            pst.execute ();
            pst.close ();


        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
    }


    public void addUser(String customerSSn, String customerPassword, String name, String address, String phone, String email) {


        try {
            String qu = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";
            String UserPassword = new PassWordHash ().encrypt (customerPassword, secretKey);

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);
            pst.setString (1, customerSSn);
            pst.setString (2, UserPassword);
            pst.setString (3, name);
            pst.setString (4, address);
            pst.setString (5, phone);
            pst.setString (6, email);
            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }

    }


    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            statement = connection.createStatement ();
            result = statement.executeQuery (query);
        } catch (SQLException ex) {
            ex.printStackTrace ();
            return null;
        } finally {
        }
        return result;
    }


    public boolean execAction(String qu) {
        try {
            statement = connection.createStatement ();
            statement.execute (qu);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace ();
            return false;
        } finally {
        }
    }

    public String checkLogInIfEmployee(String ssn, String password) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();
        String passwordId = "";
        String role = "";
        String SSN = "";

        // return false;
        String qu = "SELECT * FROM user us, employee emp WHERE emp.SSN = '" + ssn + "'" + "AND us.SSN = '" + ssn + "'";
        ResultSet rs = databaseHandler.execQuery (qu);

        while (rs.next ()) {
            passwordId = rs.getString ("passWord");
            SSN = rs.getString ("SSN");
            role = rs.getString ("Role");

        }

        if (passwordId.equals (password) && SSN.equals (ssn) & role.equals ("Manager")) {
            return "1";
        } else if (passwordId.equals (password) && SSN.equals (ssn) & role.equals ("Employee")) {
            return "2";
        } else {
            return "10";
        }
    }

    public String checkLogInIfCustomer(String ssn, String password) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();
        String passwordId = "";
        String SSN = "";

        String qu = "SELECT * FROM user us, customer cus WHERE cus.SSN = '" + ssn + "'" + "AND us.SSN = '" + ssn + "'";
        ResultSet rs = databaseHandler.execQuery (qu);

        while (rs.next ()) {
            passwordId = rs.getString ("passWord");
            SSN = rs.getString ("SSN");
        }
        if (passwordId.equals (password) && SSN.equals ("")) {
            return "0";
        } else if (passwordId.equals (password) && SSN.equals (ssn)) {
            return "1";
        } else {
            return "0";
        }
    }

    //This method was used one time for testing connection with database
    public void addUsersForTesting() {

        try {
            String qu = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";

            String SSN = "11";
            String customerPassword = new PassWordHash ().encrypt ("11", secretKey);

            String name = "Muhannad";
            String address = "Kristianstad";
            String phone = "0768837489";
            String email = "mohanad2satouf@gmail.com";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);
            pst.setString (1, SSN);
            pst.setString (2, customerPassword);
            pst.setString (3, name);
            pst.setString (4, address);
            pst.setString (5, phone);
            pst.setString (6, email);
            pst.execute ();
            pst.close ();


        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }

        try {
            String qu2 = "INSERT INTO employee (Role, SSN) " +
                    "VALUES (?,?)";

            String role = "Manager";
            String SSN = "11";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu2);
            pst.setString (1, role);
            pst.setString (2, SSN);
            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
        try {
            String qu3 = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";
            String SSN = "22";
            String customerPassword = new PassWordHash ().encrypt ("22", secretKey);
            String name = "Mohamad";
            String address = "Karlistad";
            String phone = "0768837489";
            String email = "mohanad1satouf@gmail.com";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu3);
            pst.setString (1, SSN);
            pst.setString (2, customerPassword);
            pst.setString (3, name);
            pst.setString (4, address);
            pst.setString (5, phone);
            pst.setString (6, email);
            pst.execute ();
            pst.close ();


        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
        try {
            String qu2 = "INSERT INTO employee (Role, SSN) " +
                    "VALUES (?,?)";

            String role = "Employee";
            String SSN = "22";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu2);
            pst.setString (1, role);
            pst.setString (2, SSN);
            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
        try {
            String qu3 = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";
            String SSN = "33";
            String customerPassword = new PassWordHash ().encrypt ("33", secretKey);
            String name = "Ahmad";
            String address = "Stockholm";
            String phone = "0768837489";
            String email = "mohanad22satouf@gmail.com";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu3);
            pst.setString (1, SSN);
            pst.setString (2, customerPassword);
            pst.setString (3, name);
            pst.setString (4, address);
            pst.setString (5, phone);
            pst.setString (6, email);
            pst.execute ();
            pst.close ();


        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
        try {
            String qu2 = "INSERT INTO customer (Customer_type, SSN) " +
                    "VALUES (?,?)";

            String Customer_type = "Buyer";
            String SSN = "33";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu2);
            pst.setString (1, Customer_type);
            pst.setString (2, SSN);
            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
    }


    public boolean deleteLand(Property property) {
        try {
            String deleteStat = "DELETE FROM Land WHERE Property_ID=? ";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setInt (1, property.getProperty_ID ());
            preparedStatement.executeUpdate ();
            deleteFromProperty (property);
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return false;
    }

    public boolean deleteCustomer(Customer customer) {
        try {
            String deleteStat = "DELETE FROM Customer WHERE SSN=? ";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setString (1, customer.getSsn ());
            preparedStatement.executeUpdate ();
            deleteFromUser (customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return false;
    }


    public boolean deleteApartment(Property property) {
        try {
            String deleteStat = "DELETE FROM Apartment WHERE Property_ID=? ";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setInt (1, property.getProperty_ID ());
            preparedStatement.executeUpdate ();
            deleteFromProperty (property);
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public boolean deleteHouse(Property property) {
        try {
            String deleteStat = "DELETE FROM House WHERE Property_ID=? ";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setInt (1, property.getProperty_ID ());
            preparedStatement.executeUpdate ();
            deleteFromProperty (property);
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public boolean deleteCommercial(Property property) {
        try {
            String deleteStat = "DELETE FROM Commercial WHERE Property_ID=? ";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setInt (1, property.getProperty_ID ());
            preparedStatement.executeUpdate ();
            deleteFromProperty (property);
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public void deleteFromProperty(Property property) {
        try {
            String deleteStat = "DELETE FROM Property WHERE Property_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setInt (1, property.getProperty_ID ());
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void deleteFromUser(User user) {
        try {
            String deleteStat = "DELETE FROM User WHERE SSN = ?";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setString (1, user.getSsn ());
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public boolean deleteEmployee(User user) {
        try {
            String deleteStat = "DELETE FROM Employee WHERE SSN=? ";
            PreparedStatement preparedStatement = connection.prepareStatement (deleteStat);
            preparedStatement.setString (1, user.getSsn ());
            preparedStatement.executeUpdate ();
            deleteFromUser (user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public boolean editUser(User user) {
        try {
            String editUser = "UPDATE user SET SSN=?, Name=?, Address=?," +
                    "Phone=?, Email=?" +
                    "WHERE SSN=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editUser);
            preparedStatement.setString (1, user.getSsn ());
            preparedStatement.setString (2, user.getName ());
            preparedStatement.setString (3, user.getAddress ());
            preparedStatement.setString (4, user.getPhone ());
            preparedStatement.setString (5, user.getEmail ());
            preparedStatement.setString (6, user.getSsn ());
            int res = preparedStatement.executeUpdate ();


            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }


    public void editLand(Land land) {

        String updateLand = "UPDATE land SET Type=?, Irrigated=?, Includes_Residence=? WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (updateLand);
            preparedStatement.setString (1, land.getType ());
            preparedStatement.setBoolean (2, land.isIrrigated ());
            preparedStatement.setBoolean (3, land.isIncludesResidence ());
            preparedStatement.setInt (4, land.getProperty_ID ());
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }

    }

    public void editApartment(Apartment apartment) {

        String updateApartmentForResident = "UPDATE Resident SET  Heating=?,  Parking=?, Balcony=?, Rooms=?, Bathrooms=?, Year=?    WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (updateApartmentForResident);
            preparedStatement.setBoolean (1, apartment.isHeating ());
            preparedStatement.setBoolean (2, apartment.isParking ());
            preparedStatement.setBoolean (3, apartment.isBalcony ());
            preparedStatement.setString (4, apartment.getRoom ());
            preparedStatement.setString (5, apartment.getBathroom ());
            preparedStatement.setInt (6, apartment.getYearBuilt ());
            preparedStatement.setInt (7, apartment.getProperty_ID ());
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }


        String updateApartment = "UPDATE apartment SET Floor=?   WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (updateApartment);
            preparedStatement.setString (1, apartment.getFloor ());
            preparedStatement.setString (2, String.valueOf (apartment.getProperty_ID ()));
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }


    }

    public void editHouse(House house) {


        String updateHouseForResident = "UPDATE Resident SET  Heating=?,  Parking=?, Balcony=?, Rooms=?, Bathrooms=?, Year=?    WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (updateHouseForResident);
            preparedStatement.setBoolean (1, house.isHeating ());
            preparedStatement.setBoolean (2, house.isParking ());
            preparedStatement.setBoolean (3, house.isBalcony ());
            preparedStatement.setString (4, house.getRoom ());
            preparedStatement.setString (5, house.getBathroom ());
            preparedStatement.setInt (6, house.getYearBuilt ());
            preparedStatement.setInt (7, house.getProperty_ID ());
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }


        String updateHouse = "UPDATE house SET Garage=? WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (updateHouse);
            preparedStatement.setBoolean (1, house.isGarage ());
            preparedStatement.setString (2, String.valueOf (house.getProperty_ID ()));
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    public void editCommercial(CommercialProperty commercialProperty) {

        String updateCommercial = "UPDATE commercial SET Type=?, Floor=? ,Year_Built=? WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement (updateCommercial);
            preparedStatement.setString (1, commercialProperty.getType ());
            preparedStatement.setString (2, commercialProperty.getFloor ());
            preparedStatement.setInt (3, commercialProperty.getYearBuilt ());
            preparedStatement.setInt (4, commercialProperty.getProperty_ID ());
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }


    public boolean editPropertyForLand(Land land) {
        try {
            String editProperty = "UPDATE property SET Property_ID=?, Region=?, Address=?," +
                    "Area=?, fees=?, Price=? " +
                    "WHERE Property_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, land.getProperty_ID ());
            preparedStatement.setString (2, land.getRegion ());
            preparedStatement.setString (3, land.getAddress ());
            preparedStatement.setInt (4, land.getArea ());
            preparedStatement.setInt (5, land.getPrice ());
            preparedStatement.setInt (6, land.getFees ());
            preparedStatement.setInt (7, land.getProperty_ID ());
            int res = preparedStatement.executeUpdate ();
            editLand (land);

            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public boolean editPropertyForApartment(Apartment apartment) {
        try {
            String editProperty = "UPDATE property SET Property_ID=?, Region=?, Address=?," +
                    "Area=?, Price=?, fees=? " +
                    "WHERE Property_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, apartment.getProperty_ID ());
            preparedStatement.setString (2, apartment.getRegion ());
            preparedStatement.setString (3, apartment.getAddress ());
            preparedStatement.setInt (4, apartment.getArea ());
            preparedStatement.setInt (5, apartment.getPrice ());
            preparedStatement.setInt (6, apartment.getFees ());
            preparedStatement.setInt (7, apartment.getProperty_ID ());

            int res = preparedStatement.executeUpdate ();
            editApartment (apartment);
            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public boolean editPropertyForHouse(House house) {
        try {
            String editProperty = "UPDATE property SET Property_ID=?, Region=?, Address=?," +
                    "Area=?, fees=?, Price=? " +
                    "WHERE Property_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, house.getProperty_ID ());
            preparedStatement.setString (2, house.getRegion ());
            preparedStatement.setString (3, house.getAddress ());
            preparedStatement.setInt (4, house.getArea ());
            preparedStatement.setInt (5, house.getFees ());
            preparedStatement.setInt (6, house.getPrice ());
            preparedStatement.setInt (7, house.getProperty_ID ());
            int res = preparedStatement.executeUpdate ();
            editHouse (house);
            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }


    public boolean editPropertyForCommercial(CommercialProperty commercialProperty) {
        try {
            String editProperty = "UPDATE Property SET Property_ID=?, Region=?, Address=?, " +
                    "Area=?, fees=? , Price=?  " +
                    "WHERE Property_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, commercialProperty.getProperty_ID ());
            preparedStatement.setString (2, commercialProperty.getRegion ());
            preparedStatement.setString (3, commercialProperty.getAddress ());
            preparedStatement.setInt (4, commercialProperty.getArea ());
            preparedStatement.setInt (5, commercialProperty.getFees ());
            preparedStatement.setInt (6, commercialProperty.getPrice ());
            preparedStatement.setInt (7, commercialProperty.getProperty_ID ());
            int res = preparedStatement.executeUpdate ();
            editCommercial (commercialProperty);
            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }


    public ObservableList<PieChart.Data> getAllPropertiesStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList ();

        String qu = "SELECT count(Property_ID) From Property";
        String qu2 = "SELECT count(Property_ID) From Land";
        String qu3 = "SELECT count(Property_ID) From House";
        String qu4 = "SELECT count(Property_ID) From Commercial";
        String qu5 = "SELECT count(Property_ID) From Apartment";


        ResultSet rs = execQuery (qu);
        try {
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Properties: (" + count + ")", count));
            }
            rs = execQuery (qu2);
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total lands : (" + count + ")", count));
            }
            rs = execQuery (qu3);
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Houses: (" + count + ")", count));
            }
            rs = execQuery (qu4);
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Apartments: (" + count + ")", count));
            }
            rs = execQuery (qu5);
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Commercial Properties: (" + count + ")", count));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

        return data;
    }


    public ObservableList<PieChart.Data> getRentOrSaleStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList ();

        String qu = "SELECT count(Property_ID) From Property";
        String qu2 = "SELECT count(*) From Property WHERE fees > 0 ";
        String qu3 = "SELECT count(*) From Property where fees <= 0 ";

        ResultSet rs = execQuery (qu);
        try {
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Properties: (" + count + ")", count));
            }
            rs = execQuery (qu2);
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Properties for sale: (" + count + ")", count));
            }
            rs = execQuery (qu3);
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total Properties for rent: (" + count + ")", count));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

        return data;
    }


    public String checkIfEmail(String email) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();
        String EmailFromDatabase = "";


        String qu = "SELECT Email FROM user  WHERE Email = '" + email + "'";
        ResultSet rs = databaseHandler.execQuery (qu);

        while (rs.next ()) {
            EmailFromDatabase = rs.getString ("Email");

        }
        if (EmailFromDatabase.equals (email)) {
            getPasswordFromDatabase (email);
            return "1";
        } else {
            return "0";
        }
    }

    public String getPasswordFromDatabase(String email) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance ();

        String qu = "SELECT password FROM user  WHERE Email = '" + email + "'";
        ResultSet rs = databaseHandler.execQuery (qu);

        while (rs.next ()) {
            passwordFromDatabase = rs.getString ("password");
        }
        return passwordFromDatabase;
    }

  /*  public void pdfForProperty(String Property_ID) {
        PreparedStatement preparedStatement = null;

        String query = "Select Property_ID, Region, Address, Area, Price, fees from property where Property_ID = " + Property_ID;
        ResultSet resultSet = databaseHandler.execQuery(query);

        try {
            while (resultSet.next()) {
                int propertyID = resultSet.getInt("Property_ID");
                String region = resultSet.getString("Region");
                String address = resultSet.getString("Address");
                int area = resultSet.getInt("Area");
                int price = resultSet.getInt("Price");
                int fees = resultSet.getInt("fees");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/

    public String checkIfUserExist(String SSN) {

        String query = "Select SSN from user where SSN =" + SSN;
        ResultSet resultSet = databaseHandler.execQuery (query);
        try {
            while (resultSet.next ()) {
                String ssn = resultSet.getString ("SSN");
                return ssn;
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return "";
    }

    public boolean editCustomer(Customer customer) {
        try {
            String editProperty = "UPDATE Customer SET Customer_type=? " +
                    "WHERE SSN=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setString (1, customer.typeProperty ().getValue ());
            preparedStatement.setString (2, customer.getSsn ());
            int res = preparedStatement.executeUpdate ();
            editUser (customer);

            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

}

