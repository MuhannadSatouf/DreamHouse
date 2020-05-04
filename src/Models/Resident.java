package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Resident extends Property {

    private final SimpleBooleanProperty heating;
    private final SimpleBooleanProperty parking;
    private final SimpleBooleanProperty balcony;
    private final SimpleStringProperty room;
    private final SimpleStringProperty bathroom;
    private  SimpleIntegerProperty  yearBuilt;


    public Resident(int property_ID, String region, String address, int area, int yearBuilt, String feesOrDate, int price,
                    boolean heating, boolean parking, boolean balcony, String room, String bathroom, boolean propertyAvailability) {
        super(property_ID, region, address, area, price, feesOrDate, propertyAvailability);
        this.heating = new SimpleBooleanProperty(heating);
        this.parking = new SimpleBooleanProperty(parking);
        this.balcony = new SimpleBooleanProperty(balcony);
        this.room = new SimpleStringProperty(room);
        this.bathroom = new SimpleStringProperty(bathroom);
        this.yearBuilt = new SimpleIntegerProperty (yearBuilt);
    }

    public int getYearBuilt() {
        return yearBuilt.get ();
    }

    public SimpleIntegerProperty yearBuiltProperty() {
        return yearBuilt;
    }

    public String getRoom() {
        return room.get();
    }

    public SimpleStringProperty roomProperty() {
        return room;
    }

    public String getBathroom() {
        return bathroom.get();
    }

    public SimpleStringProperty bathroomProperty() {
        return bathroom;
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
