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
    public static DataBaseHandler getInstance() {
        if (databaseHandler == null) {
            try {
                databaseHandler = new DataBaseHandler();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return databaseHandler;
    }


    public DataBaseHandler() throws SQLException {
        createConnection();
    }


    void createConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "root");
            //Remote database  // connection = DriverManager.getConnection ("jdbc:mysql://den1.mysql5.gear.host:3306/realestate2?useSSL=false", "realestate2", "Xl9pU?!yN6Rr");
            System.out.println("Connection success");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void addPropertyForSale(String property_Id, String region, String address, String area, String fees, String price) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,fees) " +
                    "VALUES(?,?,?,?,?,?)";


            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);
            pst.setString(1, property_Id);
            pst.setString(2, region);
            pst.setString(3, address);
            pst.setString(4, area);
            pst.setString(5, price);
            pst.setString(6, fees);
            pst.execute();
            pst.close();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public void addPropertyForRent(String property_Id, String region, String address, String area, String availableFrom, String price) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,availableFrom) " +
                    "VALUES(?,?,?,?,?,?)";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);

            pst.setString(1, property_Id);
            pst.setString(2, region);
            pst.setString(3, address);
            pst.setInt(4, Integer.parseInt(area));
            pst.setString(5, price);
            pst.setString(5, availableFrom);
            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void addUser(String customerSSn, String customerPassword, String name, String address, String phone, String email) {


        try {
            String qu = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);
            pst.setString(1, customerSSn);
            pst.setString(2, customerPassword);
            pst.setString(3, name);
            pst.setString(4, address);
            pst.setString(5, phone);
            pst.setString(6, email);
            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public void addCustomer(String customer_type, String SSN) {

        try {

            String qu = "INSERT INTO customer (Customer_type,SSN)" +
                    "VALUES(?,?)";


            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);
            pst.setString(1, customer_type);
            pst.setString(2, SSN);

            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
        }
        return result;
    }


    public boolean execAction(String qu) {
        try {
            statement = connection.createStatement();
            statement.execute(qu);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
        }
    }

    public String checkLogInIfEmployee(String ssn, String password) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        String passwordId = "";
        String role = "";
        String SSN = "";


        // return false;


        String qu = "SELECT * FROM user us, employee emp WHERE emp.SSN = '" + ssn + "'" + "AND us.SSN = '" + ssn + "'";
        ResultSet rs = databaseHandler.execQuery(qu);

        while (rs.next()) {
            passwordId = rs.getString("passWord");
            SSN = rs.getString("SSN");
            role = rs.getString("Role");

        }

        if (passwordId.equals(password) && SSN.equals(ssn) & role.equals("Manager")) {
            return "1";
        } else if (passwordId.equals(password) && SSN.equals(ssn) & role.equals("Employee")) {
            return "2";
        } else {
            return "10";
        }
    }

    public String checkLogInIfCustomer(String ssn, String password) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        String passwordId = "";
        String SSN = "";

        String qu = "SELECT * FROM user us, customer cus WHERE cus.SSN = '" + ssn + "'" + "AND us.SSN = '" + ssn + "'";
        ResultSet rs = databaseHandler.execQuery(qu);

        while (rs.next()) {
            passwordId = rs.getString("passWord");
            SSN = rs.getString("SSN");
        }
        if (passwordId.equals(password) && SSN.equals("")) {
            return "0";
        } else if (passwordId.equals(password) && SSN.equals(ssn)) {
            return "1";
        } else {
            return "0";
        }
    }

    public void addUsersForTesting() {

        try {
            String qu = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";
            String SSN = "11";
            String customerPassword = new PassWordHash().encrypt("44",secretKey);
            String name = "Muhannad";
            String address = "Kristianstad";
            String phone = "0768837489";
            String email = "mohanad2satouf@gmail.com";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);
            pst.setString(1, SSN);
            pst.setString(2, customerPassword);
            pst.setString(3, name);
            pst.setString(4, address);
            pst.setString(5, phone);
            pst.setString(6, email);
            pst.execute();
            pst.close();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        try {
            String qu2 = "INSERT INTO employee (Role, SSN) " +
                    "VALUES (?,?)";

            String role = "Manager";
            String SSN = "11";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu2);
            pst.setString(1, role);
            pst.setString(2, SSN);
            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            String qu3 = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";
            String SSN = "22";
            String customerPassword = new PassWordHash().encrypt("22",secretKey);
            String name = "Mohamad";
            String address = "Karlistad";
            String phone = "0768837489";
            String email = "mohanad1satouf@gmail.com";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu3);
            pst.setString(1, SSN);
            pst.setString(2, customerPassword);
            pst.setString(3, name);
            pst.setString(4, address);
            pst.setString(5, phone);
            pst.setString(6, email);
            pst.execute();
            pst.close();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            String qu2 = "INSERT INTO employee (Role, SSN) " +
                    "VALUES (?,?)";

            String role = "Employee";
            String SSN = "22";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu2);
            pst.setString(1, role);
            pst.setString(2, SSN);
            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            String qu3 = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";
            String SSN = "33";
            String customerPassword = new PassWordHash().encrypt("33",secretKey);
            String name = "Ahmad";
            String address = "Stockholm";
            String phone = "0768837489";
            String email = "mohanad22satouf@gmail.com";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu3);
            pst.setString(1, SSN);
            pst.setString(2, customerPassword);
            pst.setString(3, name);
            pst.setString(4, address);
            pst.setString(5, phone);
            pst.setString(6, email);
            pst.execute();
            pst.close();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        try {
            String qu2 = "INSERT INTO customer (Customer_type, SSN) " +
                    "VALUES (?,?)";

            String Customer_type = "Buyer";
            String SSN = "33";
            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu2);
            pst.setString(1, Customer_type);
            pst.setString(2, SSN);
            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public boolean deleteLand(Property property) {
        try {
            String deleteStat = "DELETE FROM Land WHERE Property_ID=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStat);
            preparedStatement.setInt(1, property.getProperty_ID());
            preparedStatement.executeUpdate();
            deleteFromProperty(property);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public void deleteFromProperty(Property property) {
        try {
            String deleteStat = "DELETE FROM Property WHERE Property_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteStat);
            preparedStatement.setInt(1, property.getProperty_ID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLand(Land land) {


        String updateLand = "UPDATE land SET Type=?, Irrigated=?, Includes_Residence=? WHERE Property_ID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateLand);
            preparedStatement.setString(1, land.getType());
            preparedStatement.setBoolean(2, land.isIrrigated());
            preparedStatement.setBoolean(3, land.isIncludesResidence());
            preparedStatement.setInt(4, land.getProperty_ID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public boolean editPropertyForSale(Land land) {
        try {
            String editProperty = "UPDATE property SET Property_ID=?, Region=?, Address=?," +
                    "Area=?, Price=?, fees=? " +
                    "WHERE Property_ID=?";

            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, land.getProperty_ID ());
            preparedStatement.setString (2, land.getRegion ());
            preparedStatement.setString (3, land.getAddress ());
            preparedStatement.setInt (4, land.getArea ());
            preparedStatement.setInt (5, land.getPrice ());
            preparedStatement.setString (6, land.getFeesOrDate ());
            preparedStatement.setInt (7, land.getProperty_ID ());
            int res = preparedStatement.executeUpdate ();
            editLand (land);

            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



    public boolean editPropertyForRent(Land land) {
        try {
            String editProperty = "UPDATE property SET Property_ID=?, Region=?, Address=?," +
                    "Area=?, Price=?, AvailableFrom=? " +
                    "WHERE Property_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, land.getProperty_ID ());
            preparedStatement.setString (2, land.getRegion ());
            preparedStatement.setString (3, land.getAddress ());
            preparedStatement.setInt (4, land.getArea ());
            preparedStatement.setInt (5, land.getPrice ());
            preparedStatement.setString (6, land.getFeesOrDate ());
            preparedStatement.setInt (7, land.getProperty_ID ());
            int res = preparedStatement.executeUpdate ();
            editLand (land);
            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }

        return false;
    }

    public ObservableList<PieChart.Data> getLandStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList ();

        String qu = "SELECT count(Property_ID) From Land";
        //  String qu2 ="SELECT COUNT (*) From Property where ";

        ResultSet rs = execQuery (qu);
        try {
            if (rs.next ()) {
                int count = rs.getInt (1);
                data.add (new PieChart.Data ("Total lands: ("+count +")", count));

            }
            //  rs=execQuery (qu2);
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }

        return data;
    }



    public void checkIfEmail(String email) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();
        String EmailFromDatabase = "";


        String qu = "SELECT Email FROM user  WHERE Email = '" + email + "'";
        ResultSet rs = databaseHandler.execQuery(qu);

        while (rs.next()) {
            EmailFromDatabase = rs.getString("Email");

        }
        if (EmailFromDatabase.equals(email)) {
            EmailSend emailSend = new EmailSend();
            getPasswordFromDatabase(email);
            emailSend.sendEmailWithPassword(email, "Welcome to Dream house\n You have sent a request to remember your password. \n This is your password: ",
                    new PassWordHash().decrypt(passwordFromDatabase,secretKey));
        } else {

        }
    }

    public void getPasswordFromDatabase(String email) throws SQLException {
        DataBaseHandler databaseHandler = DataBaseHandler.getInstance();

        String qu = "SELECT password FROM user  WHERE Email = '" + email + "'";
        ResultSet rs = databaseHandler.execQuery(qu);

        while (rs.next()){
            passwordFromDatabase = rs.getString("password");
        }

    }


}

