package Models;


import Controller.ControllerForAddLandForSale;
import javafx.scene.control.Alert;

import java.sql.*;

public class DataBaseHandler {

    private static DataBaseHandler databaseHandler = null;
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static Connection connection = null;
    public static Statement statement = null;

    public static DataBaseHandler getInstance() {
        if (databaseHandler == null) {
            databaseHandler = new DataBaseHandler ();
        }
        return databaseHandler;
    }

    private DataBaseHandler() {
        createConnection ();


    }


    void createConnection() {
        try {
            Class.forName (DRIVER).newInstance ();
            connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
            //Remote database  // connection = DriverManager.getConnection ("jdbc:mysql://den1.mysql5.gear.host:3306/realestate2?useSSL=false", "realestate2", "Xl9pU?!yN6Rr");
            System.out.println ("Connection success");
        } catch (Exception e) {

            e.printStackTrace ();
        }
    }


    public void addPropertyForSale(String property_Id, String region, String address, String area, String fees, String price) {
        try {




                String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,fees) " +
                        "VALUES(?,?,?,?,?,?)";
                PreparedStatement pst;
                pst = DataBaseHandler.connection.prepareStatement (qu);
                pst.setString (1, property_Id);
                pst.setString (2, region);
                pst.setString (3, address);
                pst.setInt (4, Integer.parseInt (area));
                pst.setString (5, price);
                pst.setInt (6, Integer.parseInt (String.valueOf (fees)));
                pst.execute ();
                pst.close ();



<<<<<<< HEAD

=======
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

<<<<<<< HEAD

    public void addProperty(String property_Id, String region, String address, String area,String price,String fees) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,Fees) " +
                    "VALUES(?,?,?,?,?,?.?)";
=======
    public void addPropertyForSale(String property_Id, String region, String address, String area, String fees, String price) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,fees) " +
                    "VALUES(?,?,?,?,?,?)";
>>>>>>> d65d5f0b29bc6bdbddcadb89173e2a9fd5db4b20

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);

<<<<<<< HEAD
            pst.setString (1, property_Id);
            pst.setString (2, region);
            pst.setString (3, address);
            pst.setString (4, area);
            pst.setString (5, price);
            pst.setString (6, fees);
            pst.execute ();
            pst.close ();
=======
            pst.setString(1, property_Id);
            pst.setString(2, region);
            pst.setString(3, address);
            pst.setInt(4, Integer.parseInt(area));
            pst.setString(5, price);
            pst.setInt(6, Integer.parseInt(String.valueOf(fees)));
            pst.execute();
            pst.close();
>>>>>>> d65d5f0b29bc6bdbddcadb89173e2a9fd5db4b20
>>>>>>> e1b1eaef68d2c7cc95cf6c8a7038a88bd4be7b8c

        } catch (SQLException throwable) {
          throwable.printStackTrace ();
        }
    }
<<<<<<< HEAD

=======


    public void addPropertyForRent(String property_Id, String region, String address, String area, String availableFrom, String price) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,availableFrom) " +
                    "VALUES(?,?,?,?,?,?)";

            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);

            pst.setString (1, property_Id);
            pst.setString (2, region);
            pst.setString (3, address);
            pst.setInt (4, Integer.parseInt (area));
            pst.setString (5, price);
            pst.setString (5, availableFrom);
            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }
    }
<<<<<<< HEAD

=======
>>>>>>> d65d5f0b29bc6bdbddcadb89173e2a9fd5db4b20
>>>>>>> e1b1eaef68d2c7cc95cf6c8a7038a88bd4be7b8c
    public void addUser(String customerSSn, String customerPassword, String name, String address, String phone, String email) {

        try {
            String qu = "INSERT INTO user (SSN,Password,Name,Address,Phone,Email) " +
                    "VALUES(?,?,?,?,?,?)  ";


            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);
            pst.setString (1, customerSSn);
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


    public boolean editProperty(Land land) {
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


   /* public boolean editLand(Land land) {
        try {
            String editProperty = "UPDATE property SET Property_ID=?, Region=?, Address=?," +
                    "Area=?,  Price=?, fees=?, " +
                    "Type=?, Irrigated=?, Includes_Residence=? " +
                    "WHERE Property_ID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement (editProperty);
            preparedStatement.setInt (1, land.getProperty_ID ());
            preparedStatement.setString (2,land.getRegion () );
            preparedStatement.setString (3, land.getAddress ());
            preparedStatement.setInt (4, land.getArea ());
            preparedStatement.setInt (5, land.getPrice ());
            preparedStatement.setInt (6, land.getFees ());
            preparedStatement.setString (8, land.getType ());
            preparedStatement.setBoolean (9, land.isIrrigated ());
            preparedStatement.setBoolean (10, land.isIncludesResidence ());
            int res = preparedStatement.executeUpdate ();
            return (res > 0);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return false;
    }*/
}












