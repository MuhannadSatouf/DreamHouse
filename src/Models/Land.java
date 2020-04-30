package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

enum LandType{VACANT_LAND,RANCH,FARM,TIMBERLAND};

public class Land extends Property {

    private final BooleanProperty irrigated;
    private final BooleanProperty residiantal;
    private enum LandType {}

    public Land(IntegerProperty propertyId, StringProperty region, StringProperty address, DoubleProperty area, IntegerProperty yearBuilt, IntegerProperty price, BooleanProperty availability, BooleanProperty availability1, BooleanProperty irrigated, BooleanProperty residiantal) {
        super(propertyId, region, address, area, yearBuilt, price, availability, availability1);
        this.irrigated = irrigated;
        this.residiantal = residiantal;
    }

    public boolean isIrrigated() {
        return irrigated.get();
    }

    public BooleanProperty irrigatedProperty() {
        return irrigated;
    }

    public boolean isResidiantal() {
        return residiantal.get();
    }

    public BooleanProperty residiantalProperty() {
        return residiantal;
    }
}
