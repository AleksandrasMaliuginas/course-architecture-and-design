package com.alemal.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PhoneValidatorTest {

    static PhoneValidator phoneValidator;

    @BeforeAll
    static void beforeAll() {
        phoneValidator = new PhoneValidator();
    }

    @Test
    void test_null() {
        assertFalse(phoneValidator.validate(null));
    }

    @Test
    void test_emptyString() {
        assertFalse(phoneValidator.validate(""));
    }

    @Test
    void test_emptyOnlyWithSpaceChars() {
        assertFalse(phoneValidator.validate(" "));
        assertFalse(phoneValidator.validate("         "));
    }

    @Test
    void test_invalid_isNumericWithLetters() {
        assertFalse(phoneValidator.validate("86123A567"));
        assertFalse(phoneValidator.validate("@86232567"));
        assertFalse(phoneValidator.validate("86232567B"));
    }

    @Test
    void test_invalid_isNumericWithSpaces() {
        assertFalse(phoneValidator.validate(" 861234567"));
        assertFalse(phoneValidator.validate("861234567 "));
        assertFalse(phoneValidator.validate("86123 4567"));
    }

    @Test
    void test_valid_LT() {
        assertEquals("+37061234567", phoneValidator.convert(PhoneNumber.CountryCode.LT, "861234567"));
    }

    @Test
    void test_invalid_lengthLT() {
        assertFalse(phoneValidator.validate("86123456"));
    }
}