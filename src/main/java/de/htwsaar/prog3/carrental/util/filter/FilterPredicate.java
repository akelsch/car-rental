package de.htwsaar.prog3.carrental.util.filter;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class FilterPredicate {

    private FilterPredicate() {
    }

    public static Predicate<Object> of(Operator operator, String right) {
        return left -> {
            if (left instanceof Number) {
                long num1 = ((Number) left).longValue();
                long num2;
                try {
                    num2 = Long.parseLong(right);
                } catch(NumberFormatException e) {
                    return false;
                }
                return switch (operator) {
                    case EQUAL -> num1 == num2;
                    case NOT_EQUAL -> num1 != num2;
                    case GREATER_THAN -> num1 > num2;
                    case LESS_THAN -> num1 < num2;
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
