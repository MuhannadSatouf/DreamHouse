package Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Property {

    public   SimpleIntegerProperty property_ID;
    public   SimpleStringProperty region;
    public  SimpleStringProperty address;
    public  SimpleIntegerProperty area;
    public  SimpleIntegerProperty price;
    public  SimpleBooleanProperty propertyAvailability;
    public  SimpleIntegerProperty fees;

    public Property(int property_ID, String region, String address, int area, int price, int fees, boolean propertyAvailability) {
        this.fees = new SimpleIntegerProperty(fees);
        this.property_ID = new SimpleIntegerProperty (property_ID);
        this.region = new SimpleStringProperty (region);
        this.address = new SimpleStringProperty (address);
        this.area = new SimpleIntegerProperty  (area);
        this.price = new SimpleIntegerProperty (price);
        this.propertyAvailability=new SimpleBooleanProperty (propertyAvailability);
    }


    public int getFees() {
        return fees.get();
    }

    public SimpleIntegerProperty feesProperty() {
        return fees;
    }

    public int getProperty_ID() {
        return property_ID.get ();
    }

    public SimpleIntegerProperty property_IDProperty() {
        return property_ID;
    }

    public int getArea() {
        return area.get ();
    }

    public SimpleIntegerProperty areaProperty() {
        return area;
    }



    public int getPrice() {
        return price.get ();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public String getRegion() {
        return region.get ();
    }

    public SimpleStringProperty regionProperty() {
        return region;
    }

    public String getAddress() {
        return address.get ();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public boolean isPropertyAvailability() {
        return propertyAvailability.get ();
    }

    public SimpleBooleanProperty propertyAvailabilityProperty() {
        return propertyAvailability;
    }
}
