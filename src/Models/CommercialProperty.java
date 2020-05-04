package Models;

import javafx.beans.property.*;

;
;
public class CommercialProperty extends Property {
    public SimpleStringProperty type;
    public SimpleStringProperty floor;
    public SimpleIntegerProperty year_Built;


    public CommercialProperty(int property_ID, String region, String address, int area, int price, int fees,
                              boolean propertyAvailability, String type, String floor, int year_Built) {
        super(property_ID, region, address, area, price, fees, propertyAvailability);
        this.type = new SimpleStringProperty(type);
        this.floor = new SimpleStringProperty(floor);
        this.year_Built = new SimpleIntegerProperty(year_Built);

    }


    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getFloor() {
        return floor.get();
    }

    public SimpleStringProperty floorProperty() {
        return floor;
    }

    public int getYear_Built() {
        return year_Built.get();
    }

    public SimpleIntegerProperty year_BuiltProperty() {
        return year_Built;
    }
}
