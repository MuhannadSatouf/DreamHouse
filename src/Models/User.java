package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty ssn;
    private final StringProperty name;
    private final StringProperty address;
    private final IntegerProperty phone;
    private final StringProperty email;

    public User(IntegerProperty ssn, StringProperty name, StringProperty address, IntegerProperty phone, StringProperty email) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public IntegerProperty getSsn() {
        return ssn;
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getAddress() {
        return address;
    }

    public IntegerProperty getPhone() {
        return phone;
    }

    public StringProperty getEmail() {
        return email;
    }
}