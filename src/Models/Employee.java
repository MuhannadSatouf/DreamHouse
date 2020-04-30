package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Employee extends User {
    private IntegerProperty employeeId;

    public Employee(IntegerProperty ssn, StringProperty name, StringProperty address, IntegerProperty phone, StringProperty email) {
        super (ssn, name, address, phone, email);
    }


    public IntegerProperty getID() {
        return employeeId;
    }
}
