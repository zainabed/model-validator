package com.zainabed.model.validator;

import java.util.Objects;
import java.util.function.Supplier;

public interface ValidationRules {

    static String notNull(Supplier<String> getter, String errorMessage) {
        if (Objects.isNull(getter) || Objects.isNull(errorMessage)) {
            throw new IllegalArgumentException("method or error message is empty");
        }

        return getter.get() != null ? null : errorMessage;
    }
}
