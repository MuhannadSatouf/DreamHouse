package Models;


import javafx.beans.property.SimpleStringProperty;

public class CommercialProperty extends Property {

    private SimpleStringProperty commercialType;
    private SimpleStringProperty commercialFloor;

    public CommercialProperty(int property_ID, String region, String address, int area, int price,
                              int fees, boolean propertyAvailability, String commercialType,
                              String commercialFloor) {
        super(property_ID, region, address, area, price, fees, propertyAvailability);
        this.commercialType = new SimpleStringProperty(commercialType);
        this.commercialFloor = new SimpleStringProperty(commercialFloor);
    }

    public String getCommercialType() {
        return commercialType.get();
    }

    public SimpleStringProperty commercialTypeProperty() {
        return commercialType;
    }

    public void setCommercialType(String commercialType) {
        this.commercialType.set(commercialType);
    }

    public String getCommercialFloor() {
        return commercialFloor.get();
    }

    public SimpleStringProperty commercialFloorProperty() {
        return commercialFloor;
    }

    public void setCommercialFloor(String commercialFloor) {
        this.commercialFloor.set(commercialFloor);
    }
}
