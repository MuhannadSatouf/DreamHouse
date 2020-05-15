package Models;

import javafx.beans.property.*;


import javafx.beans.property.SimpleStringProperty;



public class CommercialProperty extends Property {
    public SimpleStringProperty type;
    public SimpleStringProperty floor;







    public CommercialProperty(int property_ID, String region, String address, int area, int price, int fees,
                              boolean propertyAvailability, String type, String floor) {
        super(property_ID, region, address, area, price, fees, propertyAvailability);
        this.type = new SimpleStringProperty(type);
        this.floor = new SimpleStringProperty(floor);

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

    public SimpleStringProperty floorProperty() {
        return floor;
    }

}
