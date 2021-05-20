package com.zainabed.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class NotNullRuleTest {

    private Supplier<String> nullSupplier;
    private Supplier<String> nonNullSupplier;
    private String message;

    @BeforeEach
    void setUp() {
        nullSupplier = () -> null;
        nonNullSupplier = () -> "data";
        message = "message";
    }

    @Test
    void shouldValidateNotNull() {

        assertNotNull(ValidationRules.notNull(nullSupplier, message));
        assertNull(ValidationRules.notNull(nonNullSupplier, message));
    }

    @Test
    void shouldAssertForNullSupplier() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationRules.notNull(null, message);
        });
        assertEquals(exception.getMessage(), ValidationRules.EMPTY_GETTER_METHOD_ERROR);
    }

    @Test
    void shouldAssertForNullMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationRules.notNull(nonNullSupplier, null);
        });
        assertEquals(exception.getMessage(), ValidationRules.ERROR_MESSAGE_NULL_ERROR);
    }

    @Test
    void shouldValidateBoolean() {
        Supplier<Boolean> booleanCallable = () -> true;
        assertNull(ValidationRules.notNull(booleanCallable, message));
    }

    @Test
    void shouldValidateInteger() {
        Supplier<Integer> integerCallable = () -> 1;
        assertNull(ValidationRules.notNull(integerCallable, message));
    }

}