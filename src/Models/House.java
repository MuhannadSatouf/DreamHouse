package Models;

import javafx.beans.property.BooleanProperty;

public class House extends Property {
    private final BooleanProperty garage;

    public House(BooleanProperty garage) {
        this.garage = garage;
    }


    public boolean isGarage() {
        return garage.get();
    }

    public BooleanProperty garageProperty() {
        return garage;
    }

}
