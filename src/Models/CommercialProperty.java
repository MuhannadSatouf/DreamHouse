package Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

enum CommercialType{OFFICES,RETAIL_PROPERTIES,GAS_STATION,STORES,RESTAURANTS};
enum Floor{GROUND_FLOOR,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,NONE,MORE};
public class CommercialProperty extends Property {

    private enum CommercialType {}
    private enum  Floor {}

    public CommercialProperty(IntegerProperty propertyId, StringProperty region, StringProperty address, DoubleProperty area, IntegerProperty yearBuilt, IntegerProperty price, BooleanProperty availability, BooleanProperty availability1) {
        super(propertyId, region, address, area, yearBuilt, price, availability, availability1);
    }
}
