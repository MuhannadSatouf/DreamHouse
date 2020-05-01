package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class House extends Resident {
    private final BooleanProperty garage;

    public House(int property_ID, String region, String address, int area, int yearBuilt, int fees,
                 int price, boolean heating, boolean parking, boolean balcony, String room, String bathroom,
                 boolean propertyAvailability, boolean garage) {
        super(property_ID, region, address, area, yearBuilt, fees, price, heating, parking, balcony, room, bathroom, propertyAvailability);
        this.garage = new SimpleBooleanProperty(garage);
    }


    public boolean isGarage() {
        return garage.get();
    }

    public BooleanProperty garageProperty() {
        return garage;
    }

}
