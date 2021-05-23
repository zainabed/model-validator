package com.zainabed.model.util;

import java.util.Objects;
import java.util.function.Consumer;

public interface Conditions {
    static Consumer<String> ILLEGAL_ARGUMENT_EXCEPTION_CONSUMER =
            s -> {
                throw new IllegalArgumentException(s);
            };

    static ExceptionConsumer<Consumer<String>, String> ifNull(Object o) {
        return (consumer, input) -> {
            if (Objects.isNull(o)) {
                consumer.accept(input);
            }
        };

    }

    static ExceptionConsumer<Consumer<String>, String> ifLessThanEqualTo(Integer a, Integer b) {
        return (consumer, input) -> {
            if (a <= b)
                consumer.accept(input);
        };
    }


}
