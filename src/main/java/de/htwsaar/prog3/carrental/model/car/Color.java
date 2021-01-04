package de.htwsaar.prog3.carrental.model.car;

import de.htwsaar.prog3.carrental.util.fx.Labelable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color implements Labelable {
    BLACK("car.label.color.black"),
    BLUE("car.label.color.blue"),
    GRAY("car.label.color.gray"),
    GREEN("car.label.color.green"),
    ORANGE("car.label.color.orange"),
    PURPLE("car.label.color.purple"),
    RED("car.label.color.red"),
    SILVER("car.label.color.silver"),
    WHITE("car.label.color.white"),
    YELLOW("car.label.color.yellow");

    private final String label;
}
