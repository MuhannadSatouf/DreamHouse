package Models;

import javafx.beans.property.SimpleStringProperty;

public class Customer extends User {
    private final SimpleStringProperty type;



    public Customer(String ssn, String name ,String address, String phone, String email, String type) {
        super (ssn, name, address, phone, email);
        this.type = new SimpleStringProperty (type);
    }




    public String getType() {
        return type.get ();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }


}