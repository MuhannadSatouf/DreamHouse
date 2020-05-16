package Models;

import javafx.beans.property.*;


import javafx.beans.property.SimpleStringProperty;



public class CommercialProperty extends Property {
    public SimpleStringProperty type;
    public SimpleStringProperty floor;
    public SimpleIntegerProperty yearBuilt;

    public CommercialProperty(int property_ID, String region, String address, int area, int price, int fees, boolean propertyAvailability, String type, String floor, int yearBuilt) {
        super(property_ID, region, address, area, price, fees, propertyAvailability);
        this.type = new SimpleStringProperty(type);
        this.floor = new SimpleStringProperty(floor);
        this.yearBuilt = new SimpleIntegerProperty(yearBuilt);
    }


    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getFloor() {
        return floor.get();
    }

    public int getYearBuilt() {
        return yearBuilt.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public SimpleIntegerProperty yearBuiltProperty() {
        return yearBuilt;
    }
}