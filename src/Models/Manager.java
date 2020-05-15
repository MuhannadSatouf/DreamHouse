package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Manager extends User{
    private final IntegerProperty managerId;


    public Manager(IntegerProperty ssn, StringProperty name, StringProperty address, IntegerProperty phone, StringProperty email, IntegerProperty managerId) {

        this.managerId = managerId;
    }
}
