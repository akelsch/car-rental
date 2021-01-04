package de.htwsaar.prog3.carrental.util.filter;

import de.htwsaar.prog3.carrental.util.fx.Labelable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operator implements Labelable {
    EQUAL("search.operator.equal"),
    NOT_EQUAL("search.operator.not-equal"),
    CONTAINS("search.operator.contains"),
    GREATER_THAN("search.operator.greater-than"),
    LESS_THAN("search.operator.less-than");

    private final String label;
}
