package de.htwsaar.prog3.autoverwaltung;

/**
 * This class is used to create Car Objects that this software will use.
 *
 * @author lukas
 */
public class Car {
    private Color color;
    private Manufacturer manufacturer;

    /**
     * Constructor for a car object.
     *
     * @param color    this cars color
     * @param manufacturer this cars manufacturer
     */
    public Car(Color color, Manufacturer manufacturer) {
        setColor(color);
        setManufacturer(manufacturer);
    }

    public Color getColor() {
        return this.color;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    private void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
