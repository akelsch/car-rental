package de.htwsaar.prog3.carrental.model.car;

import de.htwsaar.prog3.carrental.util.fx.Labelable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Fuel implements Labelable {
    PETROL("car.label.fuel.petrol"),
    DIESEL("car.label.fuel.diesel"),
    ELECTRIC("car.label.fuel.electric"),
    HYBRID("car.label.fuel.hybrid");

    private final String label;
}
