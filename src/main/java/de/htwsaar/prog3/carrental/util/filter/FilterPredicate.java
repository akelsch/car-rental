package de.htwsaar.prog3.carrental.util.filter;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.function.Predicate;

public class FilterPredicate {

    private FilterPredicate() {
    }

    public static Predicate<Object> of(Operator operator, String right) {
        return left -> {
            try {
                if (left instanceof Number) {
                    return FilterPredicate.handleNumber((Number) left, operator, Long.parseLong(right));
                } else if (left instanceof Year) {
                    return FilterPredicate.handleComparable((Year) left, operator, Year.parse(right));
                } else if (left instanceof YearMonth) {
                    return FilterPredicate.handleComparable((YearMonth) left, operator, YearMonth.parse(right));
                } else if (left instanceof LocalDate) {
                    return FilterPredicate.handleComparable((LocalDate) left, operator, LocalDate.parse(right));
                } else {
                    return FilterPredicate.handleArbitraryObject(left, operator, right);
                }
            } catch (Exception e) {
                return false;
            }
        };
    }

    private static boolean handleNumber(Number left, Operator operator, long right) {
        long l = left.longValue();
        return switch (operator) {
            case EQUAL -> l == right;
            case NOT_EQUAL -> l != right;
            case CONTAINS -> false;
            case GREATER_THAN -> l > right;
            case LESS_THAN -> l < right;
        };
    }

    private static <T> boolean handleComparable(Comparable<T> left, Operator operator, T right) {
        return switch (operator) {
            case EQUAL -> left.equals(right);
            case NOT_EQUAL -> !left.equals(right);
            case CONTAINS -> false;
            case GREATER_THAN -> left.compareTo(right) > 0;
            case LESS_THAN -> left.compareTo(right) < 0;
        };
    }

    private static boolean handleArbitraryObject(Object left, Operator operator, String right) {
        return switch (operator) {
            case EQUAL -> StringUtils.equalsIgnoreCase(left.toString(), right);
            case NOT_EQUAL -> !StringUtils.equalsIgnoreCase(left.toString(), right);
            case CONTAINS -> StringUtils.containsIgnoreCase(left.toString(), right);
            case GREATER_THAN, LESS_THAN -> false;
        };
    }
}
