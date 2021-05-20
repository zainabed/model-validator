package com.zainabed.model.validator;

import java.io.Serializable;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import static com.zainabed.model.util.Conditions.ifLessThanEqualTo;
import static com.zainabed.model.util.Conditions.ifNull;

public interface ValidationRules extends com.zainabed.model.util.Conditions {

    String EMPTY_GETTER_METHOD_ERROR = "Getter method can not be null";
    String INVALID_MIN_VALUE_ERROR = "Value can't be zero or negative";
    String ERROR_MESSAGE_NULL_ERROR = "errorMessage can't be null";
    int ZERO_VALUE = 0;

    static String notNull(Supplier<? extends Serializable> getter, String errorMessage) {
        ifNull(getter).thenThrow(ILLEGAL_ARGUMENT_EXCEPTION_CONSUMER, EMPTY_GETTER_METHOD_ERROR);
        ifNull(errorMessage).thenThrow(ILLEGAL_ARGUMENT_EXCEPTION_CONSUMER, ERROR_MESSAGE_NULL_ERROR);

        return getter.get() != null ? null : errorMessage;
    }

    static String minValue(final IntSupplier getter, final int minValue, final String errorMessage) {
        ifNull(getter).thenThrow(ILLEGAL_ARGUMENT_EXCEPTION_CONSUMER, EMPTY_GETTER_METHOD_ERROR);
        ifLessThanEqualTo(minValue, ZERO_VALUE).thenThrow(ILLEGAL_ARGUMENT_EXCEPTION_CONSUMER, INVALID_MIN_VALUE_ERROR);

        return getter.getAsInt() >= minValue ? null : errorMessage;
    }


}
