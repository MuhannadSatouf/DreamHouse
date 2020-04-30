package Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Property {

    private  SimpleIntegerProperty property_ID;
    private  SimpleStringProperty region;
    private  SimpleStringProperty address;
    private  SimpleIntegerProperty area;
    private  SimpleIntegerProperty  yearBuilt;
    private  SimpleIntegerProperty price;
    private  SimpleBooleanProperty propertyAvailability;

    public Property(int property_ID, String region, String address, int area, int yearBuilt, int price,boolean propertyAvailability) {
        this.property_ID = new SimpleIntegerProperty (property_ID);
        this.region = new SimpleStringProperty (region);
        this.address = new SimpleStringProperty (address);
        this.area = new SimpleIntegerProperty  (area);
        this.yearBuilt = new SimpleIntegerProperty (yearBuilt);
        this.price = new SimpleIntegerProperty (price);
        this.propertyAvailability=new SimpleBooleanProperty (propertyAvailability);
    }
    public Property() {


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

    public int getYearBuilt() {
        return yearBuilt.get ();
    }

    public SimpleIntegerProperty yearBuiltProperty() {
        return yearBuilt;
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
