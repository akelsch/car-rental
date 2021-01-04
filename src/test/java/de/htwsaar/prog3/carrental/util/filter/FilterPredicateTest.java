package de.htwsaar.prog3.carrental.util.filter;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterPredicateTest {

    @Test
    void testIntegerEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "4711");
        assertTrue(predicate.test(4711));
    }

    @Test
    void testIntegerEqualFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "4712");
        assertFalse(predicate.test(4711));
    }

    @Test
    void testLongEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "4711");
        assertTrue(predicate.test(4711L));
    }

    @Test
    void testNegativeIntegerEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "-4711");
        assertTrue(predicate.test(-4711));
    }

    @Test
    void testNonIntegerEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "abc");
        assertFalse(predicate.test(4711));
    }

    @Test
    void testIntegerNotEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "4712");
        assertTrue(predicate.test(4711));
    }

    @Test
    void testIntegerNotEqualFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "4711");
        assertFalse(predicate.test(4711));
    }

    @Test
    void testIntegerGreaterThan() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.GREATER_THAN, "4710");
        assertTrue(predicate.test(4711));
    }

    @Test
    void testIntegerGreaterThanFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.GREATER_THAN, "4711");
        assertFalse(predicate.test(4711));
    }

    @Test
    void testIntegerLessThan() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.LESS_THAN, "4712");
        assertTrue(predicate.test(4711));
    }

    @Test
    void testIntegerLessThanFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.LESS_THAN, "4711");
        assertFalse(predicate.test(4711));
    }

    @Test
    void testIntegerInvalidOperator() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.CONTAINS, "4711");
        assertFalse(predicate.test(4711));
    }

    @Test
    void testStringEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "Cayman S");
        assertTrue(predicate.test("Cayman S"));
    }

    @Test
    void testStringEqualFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "Cayman");
        assertFalse(predicate.test("Cayman S"));
    }

    @Test
    void testStringEqualIgnoringCase() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "cayman s");
        assertTrue(predicate.test("Cayman S"));
    }

    @Test
    void testStringNotEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "Cayman");
        assertTrue(predicate.test("Cayman S"));
    }

    @Test
    void testStringNotEqualFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "Cayman S");
        assertFalse(predicate.test("Cayman S"));
    }

    @Test
    void testStringNotEqualIgnoringCase() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "cayman");
        assertTrue(predicate.test("Cayman S"));
    }

    @Test
    void testStringContains() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.CONTAINS, "Cayman");
        assertTrue(predicate.test("Cayman S"));
    }

    @Test
    void testStringContainsFalse() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.CONTAINS, "GTS");
        assertFalse(predicate.test("Cayman S"));
    }

    @Test
    void testStringContainsIgnoringCase() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.CONTAINS, "cayman");
        assertTrue(predicate.test("Cayman S"));
    }

    @Test
    void testStringInvalidOperator() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.GREATER_THAN, "Cayman");
        assertFalse(predicate.test("Cayman S"));
    }
}
