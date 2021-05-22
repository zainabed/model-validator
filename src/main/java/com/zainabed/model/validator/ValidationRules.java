package com.zainabed.model.validator;

import com.zainabed.model.util.Conditions;

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
    String SUPPLIER_IS_EMPTY_ERROR = "Supplier is empty";
    String MIN_MAX_ERROR = "min value is greater than max";

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


    static String size(Supplier<String> supplier, int min, int max, String errorMessage) {
        if (supplier == null || supplier.get() == null)
            return SUPPLIER_IS_EMPTY_ERROR;

        if (errorMessage == null)
            return ERROR_MESSAGE_NULL_ERROR;

        if (min > max)
            return MIN_MAX_ERROR;

        int length = supplier.get().length();
        return length >= min && length <= max ? null : errorMessage;
    }
}
