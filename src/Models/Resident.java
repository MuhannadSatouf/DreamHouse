package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


enum Room{STUDIO,ONE,TWO,THREE,FOUR,FIVE,SIX};
enum BathRoom{ONE,TWO,THREE};

class Resident extends Property {

    private final BooleanProperty heating;
    private final BooleanProperty parking;
    private final BooleanProperty balcony;

    public Resident(IntegerProperty propertyId, StringProperty region, StringProperty address, DoubleProperty area, IntegerProperty yearBuilt, IntegerProperty price, BooleanProperty availability, BooleanProperty availability1, BooleanProperty heating, BooleanProperty parking, BooleanProperty balcony) {
        super(propertyId, region, address, area, yearBuilt, price, availability, availability1);
        this.heating = heating;
        this.parking = parking;
        this.balcony = balcony;

    }


    private  enum room{};
    private  enum bathroom{};


    public boolean isHeating() {
        return heating.get();
    }

    public BooleanProperty heatingProperty() {
        return heating;
    }

    public boolean isParking() {
        return parking.get();
    }

    public BooleanProperty parkingProperty() {
        return parking;
    }

    public boolean isBalcony() {
        return balcony.get();
    }

    public BooleanProperty balconyProperty() {
        return balcony;
    }
}
