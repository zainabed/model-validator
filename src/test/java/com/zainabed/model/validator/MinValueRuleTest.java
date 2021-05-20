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
    void shouldThrowExceptionForEmptySupplier() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            minValue(null, minValue, errorMessage);
        });
        assertEquals(ValidationRules.EMPTY_GETTER_METHOD_ERROR, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidMinValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            minValue(validSupplier, 0, errorMessage);
        });
        assertEquals(ValidationRules.INVALID_MIN_VALUE_ERROR, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForMinValueLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            minValue(validSupplier, -1, errorMessage);
        });
    }
}
