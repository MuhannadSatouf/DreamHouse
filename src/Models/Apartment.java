package Models;

import javafx.beans.property.SimpleStringProperty;

public class Apartment extends Resident {
    private final SimpleStringProperty floor;

    public Apartment(int property_ID, String region, String address, int area, int yearBuilt, String feesOrDate,
                     int price, boolean heating, boolean parking, boolean balcony,String floor, String room, String bathroom,
                     boolean propertyAvailability) {
        super(property_ID, region, address, area, yearBuilt, feesOrDate, price, heating, parking, balcony, room, bathroom, propertyAvailability);
        this.floor = new SimpleStringProperty(floor);
    }


    public String getFloor() {
        return floor.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }
}
