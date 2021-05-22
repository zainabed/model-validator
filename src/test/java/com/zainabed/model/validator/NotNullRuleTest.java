package com.zainabed.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static com.zainabed.model.validator.ValidationRules.SUPPLIER_IS_EMPTY;
import static com.zainabed.model.validator.ValidationRules.notNull;
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

        assertNotNull(notNull(nullSupplier, message));
        assertNull(notNull(nonNullSupplier, message));
    }

    @Test
    void shouldReturnErrorEmptySupplier() {
        assertEquals(SUPPLIER_IS_EMPTY, notNull(null, message));
    }

    @Test
    void shouldReturnErrorForNullSupplier() {
        assertEquals(SUPPLIER_IS_EMPTY, notNull(nullSupplier,message));
    }

    @Test
    void shouldAssertForNullMessage() {
        assertEquals(ValidationRules.ERROR_MESSAGE_IS_EMPTY, notNull(nonNullSupplier, null));
    }

    @Test
    void shouldValidateBoolean() {
        Supplier<Boolean> booleanCallable = () -> true;
        assertNull(notNull(booleanCallable, message));
    }

    @Test
    void shouldValidateInteger() {
        Supplier<Integer> integerCallable = () -> 1;
        assertNull(notNull(integerCallable, message));
    }

}