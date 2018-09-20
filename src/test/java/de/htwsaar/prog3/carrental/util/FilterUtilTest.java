package de.htwsaar.prog3.carrental.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the {@link FilterUtil} class.
 *
 * @author Julian Quint
 */
class FilterUtilTest {
    @Test
    void testConvertField() {
        String field = "ID";
        String actualField = FilterUtil.convertField(field);
        String expectedField = "id";

        assertThat(actualField, is(equalTo(expectedField)));
    }

    @Test
    void testConvertValueWithLike() {
        String value = "1";
        String actualValue = FilterUtil.convertValue(value, "LIKE");
        String expectedValue = "'%1%'";

        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @Test
    void testConvertValueWithoutLike() {
        String value = "1";
        String actualValue = FilterUtil.convertValue(value, "=");
        String expectedValue = "'1'";

        assertThat(actualValue, is(equalTo(expectedValue)));
    }
}
