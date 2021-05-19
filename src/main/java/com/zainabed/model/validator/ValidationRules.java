package com.zainabed.model.validator;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface ValidationRules {

    String EMPTY_GETTER_METHOD_ERROR = "Getter method can not be null";
    String INVALID_MIN_VALUE_ERROR = "Value can't be zero or negative";

    static String notNull(Supplier<? extends Serializable> getter, String errorMessage) {
        if (Objects.isNull(getter) || Objects.isNull(errorMessage)) {
            throw new IllegalArgumentException(EMPTY_GETTER_METHOD_ERROR);
        }
        return getter.get() != null ? null : errorMessage;
    }

    static String minValue(IntSupplier getter, int minValue, String errorMessage) {
        if(Objects.isNull(getter)) {
            throw new IllegalArgumentException(EMPTY_GETTER_METHOD_ERROR);
        }

        if(minValue <= 0) {
            throw new IllegalArgumentException(INVALID_MIN_VALUE_ERROR);
        }

        return getter.getAsInt() >= minValue ? null : errorMessage;
    }
}
