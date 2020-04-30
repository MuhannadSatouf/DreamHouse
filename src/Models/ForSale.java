package Models;

import javafx.beans.property.SimpleIntegerProperty;

public class ForSale extends Property {

    private SimpleIntegerProperty fees;


    public ForSale(int property_ID, String region, String address, int area, int yearBuilt, int price, boolean propertyAvailability, int fees) {
        super (property_ID, region, address, area, yearBuilt, price, propertyAvailability);
        this.fees = new SimpleIntegerProperty (fees);
    }


    public int getFees() {
        return fees.get ();
    }

    public SimpleIntegerProperty feesProperty() {
        return fees;
    }
}
