package de.htwsaar.prog3.carrental.util.filter;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class FilterPredicate {

    public static Predicate<Object> of(Operator operator, String right) {
        return left -> {
            if (left instanceof Integer) {
                int num = (int) left;
                return switch (operator) {
                    case EQUAL -> num == Integer.parseInt(right);
                    case NOT_EQUAL -> num != Integer.parseInt(right);
                    case GREATER_THAN -> num > Integer.parseInt(right);
                    case LESS_THAN -> num < Integer.parseInt(right);
                    default -> false;
                };
            }

            return switch (operator) {
                case EQUAL -> StringUtils.equalsIgnoreCase(left.toString(), right);
                case NOT_EQUAL -> !StringUtils.equalsIgnoreCase(left.toString(), right);
                case CONTAINS -> StringUtils.containsIgnoreCase(left.toString(), right);
                default -> false;
            };
        };
    }
}
