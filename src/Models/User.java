package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private SimpleStringProperty ssn;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty password;



    public User(String ssn, String name ,String address, String phone, String email) {
        this.ssn = new SimpleStringProperty (ssn);

        this.name =new SimpleStringProperty (name);

        this.address =new SimpleStringProperty (address);
        this.phone =new SimpleStringProperty (phone);
        this.email =new SimpleStringProperty (email);
    }


    public User(){



    }



    public String getSsn() {
        return ssn.get ();
    }

    public SimpleStringProperty ssnProperty() {
        return ssn;
    }



    public String getName() {
        return name.get ();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getAddress() {
        return address.get ();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getPhone() {
        return phone.get ();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public String getEmail() {
        return email.get ();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
}







