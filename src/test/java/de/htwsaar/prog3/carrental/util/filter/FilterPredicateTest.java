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
    void testLongEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "4711");
        assertTrue(predicate.test(4711L));
    }

    @Test
    void testIntegerNotEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "4711");
        assertTrue(predicate.test(4712));
    }

    @Test
    void testIntegerGreaterThan() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.GREATER_THAN, "4711");
        assertTrue(predicate.test(4712));
    }

    @Test
    void testIntegerLessThan() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.LESS_THAN, "4711");
        assertTrue(predicate.test(4710));
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
    void testStringEqualIgnoringCase() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.EQUAL, "Cayman S");
        assertTrue(predicate.test("cayman s"));
    }

    @Test
    void testStringNotEqual() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "Cayman S");
        assertTrue(predicate.test("Cayman"));
    }

    @Test
    void testStringNotEqualIgnoringCase() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.NOT_EQUAL, "Cayman S");
        assertTrue(predicate.test("cayman"));
    }

    @Test
    void testStringContains() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.CONTAINS, "Cayman S");
        assertFalse(predicate.test("Cayman"));
    }

    @Test
    void testStringContainsIgnoringCase() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.CONTAINS, "Cayman S");
        assertFalse(predicate.test("cayman"));
    }

    @Test
    void testStringInvalidOperator() {
        Predicate<Object> predicate = FilterPredicate.of(Operator.GREATER_THAN, "Cayman S");
        assertFalse(predicate.test("Cayman"));
    }
}
