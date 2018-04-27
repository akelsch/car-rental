package de.htwsaar.prog3.autoverwaltung;

/**
 * This class is used to create Car Objects that this software will use
 *
 * @author lukas
 */
public class Car {

	// instance varibles
	private Color color;
	private Producer producer;

	/**
	 * Constructor for a car object
	 *
	 * @param color this cars color
	 * @param producer this cars producer
	 */
	public Car(Color color, Producer producer) {
		setColor(color);
		setProducer(producer);
	}


	//**********SETTER**********
	private void setColor(Color color) {
		this.color = color;
	}

	private void setProducer(Producer producer) {
		this.producer = producer;
	}

	//**********GETTER**********
	public Color getColor() {
		return this.color;
	}

	public Producer getProducer() {
		return this.producer;
	}
}
