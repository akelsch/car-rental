package de.htwsaar.prog3.carrental.model.car;

import de.htwsaar.prog3.carrental.util.fx.Labelable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tire implements Labelable {
    SUMMER("car.label.tires.summer"),
    WINTER("car.label.tires.winter"),
    ALL_SEASON("car.label.tires.all-season");

    private final String label;
}
