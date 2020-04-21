package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Customer extends User {
    private final StringProperty type;

    public Customer(StringProperty ssn, StringProperty name, StringProperty address, IntegerProperty phone, StringProperty email, StringProperty type) {
        super(ssn, name, address, phone, email);
        this.type = type;
    }
}
