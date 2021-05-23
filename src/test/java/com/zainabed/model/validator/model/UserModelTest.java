package com.zainabed.model.validator.model;

import com.zainabed.model.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    private UserModel userModel;
    private List<String> validations;

    @BeforeEach
    void setUp() {

        userModel = new UserModel();
        userModel.setName("test name");
        userModel.setAge(23);
    }

    @Test
    void shouldBeInstanceOfValidator() {
        assertTrue(userModel instanceof Validator);
    }

    @Test
    void shouldValidateUserModel() {
        validations = userModel.validate();
        assertEquals(0, validations.size());
    }

    @Test
    void shouldFailForEmptyName() {
        userModel.setName(null);
        validations = userModel.validate();
        assertEquals(1, validations.size());
        assertEquals(UserModel.NAME_FIELD_SHOULD_NOT_BE_NULL, validations.get(0));
    }

    @Test
    void shouldFailForInvalidAge() {
        userModel.setAge(1);
        validations = userModel.validate();
        assertEquals(1, validations.size());
        assertEquals(UserModel.MINIMUM_AGE_VALUE_ERROR,validations.get(0));
    }


    @Disabled("Not yet implemented")
    @Test
    void shouldFailForMaxAgeValue() {
        userModel.setAge(30);
        validations = userModel.validate();
        assertEquals(1, validations.size());
    }
}