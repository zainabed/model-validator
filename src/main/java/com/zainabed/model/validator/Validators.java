package com.zainabed.model.validator;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface Validators {
    static List<String> validate(List<Supplier<String>> validationRules) {
        return validationRules.stream()
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
