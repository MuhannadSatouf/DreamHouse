package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class House extends Property {
    private final BooleanProperty garage;

    public House(IntegerProperty propertyId, StringProperty region, StringProperty address, DoubleProperty area, IntegerProperty yearBuilt, IntegerProperty price, BooleanProperty availability, BooleanProperty availability1, BooleanProperty garage) {
        super(propertyId, region, address, area, yearBuilt, price, availability, availability1);
        this.garage = garage;
    }

    public boolean isGarage() {
        return garage.get();
    }

    public BooleanProperty garageProperty() {
        return garage;
    }

}
