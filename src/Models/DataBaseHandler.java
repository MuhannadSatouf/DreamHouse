package Models;


import java.sql.*;

public class DataBaseHandler {

    private static DataBaseHandler databaseHandler = null;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection connection = null;
    public static Statement statement = null;

    public static DataBaseHandler getInstance() {
        if (databaseHandler == null) {
            databaseHandler = new DataBaseHandler();
        }
        return databaseHandler;
    }

    public void deleteInstance() {
        databaseHandler = null;
    }

    public DataBaseHandler() {
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


    public void addApartment(String property_Id, String region, String address, String area, String year_built, boolean parking,
                             boolean heating, boolean balcony, String floor, String rooms, String bathrooms) {


        try {


            String qu = "INSERT INTO apartmentforrent (Property_ID,Region,Address,Area,Year_Built,Parking,Heating,Balcony,Floor,Rooms,Bathrooms) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement pst;
            pst = DataBaseHandler.connection.prepareStatement(qu);

            pst.setString(1, property_Id);
            pst.setString(2, region);
            pst.setString(3, address);
            pst.setString(4, area);
            pst.setString(5, year_built);
            pst.setBoolean(6, parking);
            pst.setBoolean(7, heating);
            pst.setBoolean(8, balcony);
            pst.setString(9, floor);
            pst.setString(10, rooms);
            pst.setString(11, bathrooms);


            pst.execute();
            pst.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
            pst.setInt(4, Integer.parseInt(area));
            pst.setString(5, price);
            pst.setInt(6, Integer.parseInt(String.valueOf(fees)));
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
            String SSN = "8906219699";
            String customerPassword = new PassWordHash().generateHash("111111");
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
            String SSN = "8906219699";
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
            String customerPassword = new PassWordHash().generateHash("22");
            String name = "Mohamad";
            String address = "Karlistad";
            String phone = "0768837489";
            String email = "mohanad2satouf@gmail.com";

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
            String customerPassword = new PassWordHash().generateHash("33");
            String name = "Ahmad";
            String address = "Stockholm";
            String phone = "0768837489";
            String email = "mohanad2satouf@gmail.com";

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


}

