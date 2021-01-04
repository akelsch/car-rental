package de.htwsaar.prog3.carrental.model.car;

import de.htwsaar.prog3.carrental.util.fx.Labelable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Transmission implements Labelable {
    MANUAL("car.label.transmission.manual"),
    SEMI_AUTOMATIC("car.label.transmission.semi-automatic"),
    AUTOMATIC("car.label.transmission.automatic");

    private final String label;
}
