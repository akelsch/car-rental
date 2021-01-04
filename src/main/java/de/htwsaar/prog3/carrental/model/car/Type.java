package de.htwsaar.prog3.carrental.model.car;

import de.htwsaar.prog3.carrental.util.fx.Labelable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type implements Labelable {
    SEDAN("car.label.type.sedan"),
    COUPE("car.label.type.coupe"),
    CONVERTIBLE("car.label.type.convertible"),
    WAGON("car.label.type.wagon"),
    HATCHBACK("car.label.type.hatchback"),
    SUV("car.label.type.suv"),
    PICKUP("car.label.type.pickup"),
    VAN("car.label.type.van");

    private final String label;
}
