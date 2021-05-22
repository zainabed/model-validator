package com.zainabed.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;

import static com.zainabed.model.validator.ValidationRules.minValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinValueRuleTest {

    private IntSupplier invalidSupplier;
    private IntSupplier validSupplier;
    private String errorMessage;
    private int minValue;

    @BeforeEach
    void setUp() {
        invalidSupplier = () -> 1;
        validSupplier = () -> 11;
        errorMessage = "Should not be less";
        minValue = 10;
    }

    @Test
    void shouldValidateMinValueRule() {
        assertNotNull(minValue(invalidSupplier, minValue, errorMessage));
        assertNull(minValue(validSupplier, minValue, errorMessage));
        assertNull(minValue(() -> minValue, minValue, errorMessage));
    }

    @Test
    void shouldReturnErrorForEmptySupplier() {
        assertEquals(ValidationRules.SUPPLIER_IS_EMPTY, minValue(null, minValue, errorMessage));
    }


    @Test
    void shouldReturnErrorForInvalidMinValue() {
        assertEquals(ValidationRules.MIN_VALUE_IS_LESS_THAN_ZERO, minValue(validSupplier, 0, errorMessage));
    }

    @Test
    void shouldReturnErrorForMinValueLessThanZero() {
        assertEquals(ValidationRules.MIN_VALUE_IS_LESS_THAN_ZERO, minValue(validSupplier, -1, errorMessage));
    }
}
