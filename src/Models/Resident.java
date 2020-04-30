package Models;

import javafx.beans.property.BooleanProperty;


class Resident extends Property {

    private final BooleanProperty heating;
    private final BooleanProperty parking;
    private final BooleanProperty balcony;

    Resident(BooleanProperty heating, BooleanProperty parking, BooleanProperty balcony) {
        this.heating = heating;
        this.parking = parking;
        this.balcony = balcony;
    }


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
