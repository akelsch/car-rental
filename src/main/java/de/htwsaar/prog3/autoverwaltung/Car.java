package de.htwsaar.prog3.autoverwaltung;

/**
 * This class is used to create Car Objects that this software will use.
 *
 * @author lukas
 */
public class Car {
    private Color color;
    private Producer producer;

    /**
     * Constructor for a car object.
     *
     * @param color    this cars color
     * @param producer this cars producer
     */
    public Car(Color color, Producer producer) {
        setColor(color);
        setProducer(producer);
    }

    public Color getColor() {
        return this.color;
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public Producer getProducer() {
        return this.producer;
    }

    private void setProducer(Producer producer) {
        this.producer = producer;
    }
}
