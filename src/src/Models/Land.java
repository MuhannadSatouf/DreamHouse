package src.Models;

import Models.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Land extends Property {
    private  SimpleStringProperty type;
    private  SimpleBooleanProperty irrigated;
    private  SimpleBooleanProperty includesResidence;

    public Land(int property_ID, String region, String address, int area, int price,int fees, String type,boolean irrigated,boolean includesResidence,
                boolean propertyAvailability) {
        super (property_ID, region, address, area, price,fees, propertyAvailability);
        this.type=new SimpleStringProperty (type);
        this.irrigated=new SimpleBooleanProperty (irrigated);
        this.includesResidence=new SimpleBooleanProperty (includesResidence);


    }



    public String getType() {
        return type.get ();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public boolean isIrrigated() {
        return irrigated.get ();
    }

    public SimpleBooleanProperty irrigatedProperty() {
        return irrigated;
    }

    public boolean isIncludesResidence() {
        return includesResidence.get ();
    }

    public SimpleBooleanProperty includesResidenceProperty() {
        return includesResidence;
    }
}
