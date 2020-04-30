package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Manager extends User{
    private final IntegerProperty managerId;


    public Manager(String ssn, String name, String address, String phone, String email, IntegerProperty managerId) {
        super (ssn, name, address, phone, email);
        this.managerId = managerId;
    }
}
