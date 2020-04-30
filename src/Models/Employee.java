package Models;

import javafx.beans.property.SimpleStringProperty;

public class Employee {

    private SimpleStringProperty employee_Id;

    public Employee(String ssn, String name, String address, String phone, String email, SimpleStringProperty employee_Id) {

        this.employee_Id = employee_Id;
    }
}
