package Models;



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
            connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/mydb?useSSL=false", "root", "root");
            //Remote database  // connection = DriverManager.getConnection ("jdbc:mysql://den1.mysql5.gear.host:3306/realestate2?useSSL=false", "realestate2", "Xl9pU?!yN6Rr");
            System.out.println ("Connection success");
        } catch (Exception e) {

            e.printStackTrace ();
        }
    }


    public void addApartment(String property_Id, String region, String address, String area, String year_built, boolean parking,
                             boolean heating, boolean balcony, String floor, String rooms, String bathrooms) {


        try {


            String qu = "INSERT INTO apartmentforrent (Property_ID,Region,Address,Area,Year_Built,Parking,Heating,Balcony,Floor,Rooms,Bathrooms) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement (qu);

            pst.setString (1, property_Id);
            pst.setString (2, region);
            pst.setString (3, address);
            pst.setString (4, area);
            pst.setString (5, year_built);
            pst.setBoolean (6, parking);
            pst.setBoolean (7, heating);
            pst.setBoolean (8, balcony);
            pst.setString (9, floor);
            pst.setString (10, rooms);
            pst.setString (11, bathrooms);


            pst.execute ();
            pst.close ();

        } catch (SQLException throwable) {
            throwable.printStackTrace ();
        }


    }


    public void addProperty(String property_Id, String region, String address, String area,String price,String fees) {
        try {
            String qu = "INSERT INTO property (Property_ID,Region,Address,Area,Price,Fees) " +
                    "VALUES(?,?,?,?,?,?.?)";

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
}
