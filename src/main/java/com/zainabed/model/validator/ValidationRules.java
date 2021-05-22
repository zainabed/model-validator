package com.zainabed.model.validator;

import java.io.Serializable;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface ValidationRules extends com.zainabed.model.util.Conditions {

    String MIN_VALUE_IS_LESS_THAN_ZERO = "Value can't be zero or negative";
    String ERROR_MESSAGE_IS_EMPTY = "errorMessage can't be null";
    String SUPPLIER_IS_EMPTY = "Supplier is empty";
    String MIN_IS_GREATER_THAN_MAX = "min value is greater than max";

    int ZERO_VALUE = 0;

    private static boolean isSupplierEmpty(Supplier<? extends Serializable> supplier) {
        return supplier == null || supplier.get() == null;
    }

    static String notNull(Supplier<? extends Serializable> supplier, String errorMessage) {
        if (isSupplierEmpty(supplier))
            return SUPPLIER_IS_EMPTY;

        if (errorMessage == null)
            return ERROR_MESSAGE_IS_EMPTY;

        return supplier.get() != null ? null : errorMessage;
    }


    static String minValue(final IntSupplier getter, final int minValue, final String errorMessage) {
        if (getter == null)
            return SUPPLIER_IS_EMPTY;
        if (minValue <= ZERO_VALUE)
            return MIN_VALUE_IS_LESS_THAN_ZERO;

        return getter.getAsInt() >= minValue ? null : errorMessage;
    }


    static String size(Supplier<String> supplier, int min, int max, String errorMessage) {
        if (isSupplierEmpty(supplier))
            return SUPPLIER_IS_EMPTY;

        if (errorMessage == null)
            return ERROR_MESSAGE_IS_EMPTY;

        if (min > max)
            return MIN_IS_GREATER_THAN_MAX;

        int length = supplier.get().length();
        return length >= min && length <= max ? null : errorMessage;
    }
}
