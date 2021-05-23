package com.zainabed.model.validator.model;

import com.zainabed.model.validator.ValidationRules;
import com.zainabed.model.validator.Validator;
import com.zainabed.model.validator.Validators;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.zainabed.model.validator.ValidationRules.minValue;
import static com.zainabed.model.validator.ValidationRules.notNull;

public class UserModel implements Validator, IUserModel {
    public static final String NAME_FIELD_SHOULD_NOT_BE_NULL = "Name field should not be null";
    public static final String MINIMUM_AGE_VALUE_ERROR = "minimum age value should be 5";
    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> validate() {
        List<Supplier<String>> validationRules = Arrays.asList(
                notNull(this::getName, NAME_FIELD_SHOULD_NOT_BE_NULL),
                minValue(this::getAge, 5, MINIMUM_AGE_VALUE_ERROR)
        );

        return Validators.validate(validationRules);
    }
}
