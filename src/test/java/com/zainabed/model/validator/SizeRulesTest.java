package com.zainabed.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SizeRulesTest {

    private String testString;
    private String errorMessage;
    private int min;
    private int max;
    private Supplier<String> stringSupplier;

    @BeforeEach
    void setUp() {
        stringSupplier = () -> testString;
        testString = "test";
        errorMessage = "String size error message";
        min = 1;
        max = testString.length();
    }

    @Test
    void shouldValidateSizeRule() {
        String result = ValidationRules.size(stringSupplier, min, max, errorMessage);
        assertNull(result);

    }

    @Test
    void shouldFailSizeRule() {
        String result;
        result = ValidationRules.size(stringSupplier, min, max - 1, errorMessage);
        assertNotNull(result);
    }

    @Test
    void shouldReturnErrorMessageForEmptySupplier() {
        String validationMessage = ValidationRules.size(null, min, max, errorMessage);
        assertNotNull(validationMessage);
        assertEquals(validationMessage, ValidationRules.SUPPLIER_IS_EMPTY);
    }

    @Test
    void shouldReturnErrorMessageForEmptyValueOfSupplier() {
        String validationMessage = ValidationRules.size(() -> null, min, max, errorMessage);
        assertNotNull(validationMessage);
        assertEquals(validationMessage, ValidationRules.SUPPLIER_IS_EMPTY);
    }

    @Test
    void shouldReturnErrorMessageForEmptyInputErrorMessage() {
        String validationMessage = ValidationRules.size(stringSupplier, min, max, null);
        assertNotNull(validationMessage);
        assertEquals(validationMessage, ValidationRules.ERROR_MESSAGE_IS_EMPTY);
    }

    @Test
    void shouldReturnErrorMessageForInvalidMinMax() {
        String validationMessage = ValidationRules.size(stringSupplier, max, min, errorMessage);
        assertNotNull(validationMessage);
        assertEquals(validationMessage, ValidationRules.MIN_IS_GREATER_THAN_MAX);
    }
}