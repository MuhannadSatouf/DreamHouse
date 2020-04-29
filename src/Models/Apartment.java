package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

enum ApartmentFloor{GROUND_FLOOR,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,MORE}
public class Apartment extends Resident {

    enum ApartmentFloor {}

    public Apartment(IntegerProperty propertyId, StringProperty region, StringProperty address, DoubleProperty area, IntegerProperty yearBuilt, IntegerProperty price, BooleanProperty availability, BooleanProperty availability1, BooleanProperty heating, BooleanProperty parking, BooleanProperty balcony) {
        super(propertyId, region, address, area, yearBuilt, price, availability, availability1, heating, parking, balcony);
    }
}
