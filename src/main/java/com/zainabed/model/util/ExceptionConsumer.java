package com.zainabed.model.util;

@FunctionalInterface
public interface ExceptionConsumer<T, S> {
    void thenThrow(T t, S s);
}
