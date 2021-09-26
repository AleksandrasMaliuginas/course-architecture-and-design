package com.alemal.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordCheckerTest {

    static PasswordChecker passwordChecker;

    @BeforeAll
    static void beforeAll() {
        passwordChecker = new PasswordChecker();
    }

    @Test
    void test_null() {
        assertFalse(passwordChecker.validate(null));
    }

    @Test
    void test_emptyString() {
        assertFalse(passwordChecker.validate(""));
    }

    @Test
    void test_emptyOnlyWithSpaceChars() {
        assertFalse(passwordChecker.validate(" "));
        assertFalse(passwordChecker.validate("         "));
    }

    @Test
    void test_invalid_length() {
        assertFalse(passwordChecker.validate("A?!"));
        assertFalse(passwordChecker.validate("Abc?!"));
    }

    @Test
    void test_invalid_lengthWithSpaces() {
        assertFalse(passwordChecker.validate("Abc?! "));
        assertFalse(passwordChecker.validate(" Abc?!"));
    }

    @Test
    void test_valid_length() {
        assertTrue(passwordChecker.validate("!@#%AbDFa ?!a"));
    }

    @Test
    void test_valid_uppercaseChars() {
        assertTrue(passwordChecker.validate("abc@?!Xbc@?!"));
        assertTrue(passwordChecker.validate("Abc@?!abc@?!"));
        assertTrue(passwordChecker.validate("abc@?!abc@?A"));
    }

    @Test
    void test_invalid_uppercaseChars() {
        assertFalse(passwordChecker.validate("abc@?!abc@?!"));
    }

    @Test
    void test_valid_SpecialChars() {
        assertTrue(passwordChecker.validate("bcabc$Abcabc"));
        assertTrue(passwordChecker.validate("?bcabcAbcabc"));
        assertTrue(passwordChecker.validate("AbcabcAbcab?"));
    }

    @Test
    void test_invalid_SpecialChars() {
        assertFalse(passwordChecker.validate("AbcabcAbcabc"));
    }
}