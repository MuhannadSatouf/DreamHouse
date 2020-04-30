package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Property {
    private final IntegerProperty propertyId;
    private final StringProperty region;
    private final StringProperty address;
    private final DoubleProperty area;
    private final IntegerProperty yearBuilt;
    private final IntegerProperty price;
    private final BooleanProperty availability;


    public Property(IntegerProperty propertyId, StringProperty region, StringProperty address, DoubleProperty area, IntegerProperty yearBuilt, IntegerProperty price, BooleanProperty availability, BooleanProperty availability1) {
        this.propertyId = propertyId;
        this.region = region;
        this.address = address;
        this.area = area;
        this.yearBuilt = yearBuilt;
        this.price = price;
        this.availability = availability;

    }

    public int getPropertyId() {
        return propertyId.get();
    }

    public IntegerProperty propertyIdProperty() {
        return propertyId;
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public double getArea() {
        return area.get();
    }

    public DoubleProperty areaProperty() {
        return area;
    }

    public int getYearBuilt() {
        return yearBuilt.get();
    }

    public IntegerProperty yearBuiltProperty() {
        return yearBuilt;
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public boolean isAvailability() {
        return availability.get();
    }

    public BooleanProperty availabilityProperty() {
        return availability;
    }
}
