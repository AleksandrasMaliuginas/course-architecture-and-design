package com.alemal.validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    static EmailValidator emailValidator;

    @BeforeAll
    static void beforeAll() {
        emailValidator = new EmailValidator();
    }

    @Test
    void test_null() {
        assertFalse(emailValidator.validate(null));
    }

    @Test
    void test_emptyString() {
        assertFalse(emailValidator.validate(""));
    }

    @Test
    void test_emptyOnlyWithSpaceChars() {
        assertFalse(emailValidator.validate(" "));
    }

    /**
     * Further test cases are taken from:
     * https://en.wikipedia.org/wiki/Email_address#Valid_email_addresses
     * AND https://codefool.tumblr.com/post/15288874550/list-of-valid-and-invalid-email-addresses
     * */

    /**
     * Test cases for VALID Email addresses
     * */
    @Test
    void test_valid_usernameSimple() {
        assertTrue(emailValidator.validate("simple@example.com"));
        assertTrue(emailValidator.validate("x@example.com"));
    }

    @Test
    void test_valid_usernameWithDot() {
        assertTrue(emailValidator.validate("very.common@example.com"));
    }

    @Test
    void test_valid_usernameWithPlusSymbol() {
        assertTrue(emailValidator.validate("user.name+tag+sorting@example.com"));
    }

    @Test
    void test_valid_usernameWithHyphen() {
        assertTrue(emailValidator.validate("fully-qualified-domain@example.com"));
        assertTrue(emailValidator.validate("user-@example.org"));
    }

    @Test
    void test_valid_usernameWithSpecialChar() {
        assertTrue(emailValidator.validate("mailhost!username@example.org"));
        assertTrue(emailValidator.validate("user%example.com@example.org"));
        assertTrue(emailValidator.validate("test/test@test.com"));
    }

//    @Test
//    void test_valid_usernameWithQuotationMarks_doubleDot() {
//        assertTrue(emailValidator.validate("\"john..doe\"@example.org"));
//    }

//    @Test
//    void test_valid_usernameWithQuotationMarks_space() {
//        assertTrue(emailValidator.validate("\" \"@example.org"));
//        assertTrue(emailValidator.validate("much.\"more unusual\"@example.com"));
//    }

//    @Test
//    void test_valid_usernameWithQuotationMarks_secondEta() {
//        assertTrue(emailValidator.validate("very.unusual.\"@\".unusual.com@example.com"));
//    }

    @Test
    void test_valid_domainWithHyphen() {
        assertTrue(emailValidator.validate("email@example-one.com"));
    }

    @Test
    void test_valid_domainWithSubdomain() {
        assertTrue(emailValidator.validate("example@s.example.lt"));
        assertTrue(emailValidator.validate("email@subdomain.example.com"));
    }

    @Test
    void test_valid_domainTLD() {
        assertTrue(emailValidator.validate("email@example.name"));
        assertTrue(emailValidator.validate("email@example.museum"));
        assertTrue(emailValidator.validate("email@example.co.jp"));
        assertTrue(emailValidator.validate("email@example.lt"));
    }

//    @Test
//    void test_valid_domainIP() {
//        assertTrue(emailValidator.validate("email@[123.123.123.123]"));
//    }

    /**
     * Test cases for INVALID Email addresses
     * */

    @Test
    void test_invalid_withoutEtaSymbol() {
        assertFalse(emailValidator.validate("plainaddress"));
        assertFalse(emailValidator.validate("email.example.com"));
    }

    @Test
    void test_invalid_tooManyEtas() {
        assertFalse(emailValidator.validate("#@email@d.com"));
        assertFalse(emailValidator.validate("email@example@example.com"));
    }

    @Test
    void test_invalid_doubleDot() {
        assertAll(
                () -> assertFalse(emailValidator.validate("email@example..com")),
                () -> assertFalse(emailValidator.validate("Abc..123@example.com"))
        );
    }

    @Test
    void test_invalid_username() {
        assertFalse(emailValidator.validate("Joe Smith <email@example.com>"));
    }

    @Test
    void test_invalid_usernameEmpty() {
        assertFalse(emailValidator.validate("@example.com"));
    }

    @Test
    void test_invalid_usernameFirstCharIsDot() {
        assertFalse(emailValidator.validate(".email@example.com"));
    }

    @Test
    void test_invalid_usernameLastCharIsDot() {
        assertFalse(emailValidator.validate("email.@example.com"));

    }

    @Test
    void test_invalid_usernameLength() {
        assertFalse(emailValidator.validate("1234567890123456789012345678901234567890123456789012345678901234+x@example.com"));
    }

    @Test
    void test_invalid_usernameQuotationMark() {
        assertFalse(emailValidator.validate("simple\"email@example.com"));
    }

    @Test
    void test_invalid_usernameWithSpecialChar_parenthesis() {
        assertFalse(emailValidator.validate("simple(text)email@example.com"));
    }

    @Test
    void test_invalid_usernameWithSpecialChar_comma() {
        assertFalse(emailValidator.validate("simple,email@example.com"));
    }

    @Test
    void test_invalid_usernameWithSpecialChar_space() {
        assertFalse(emailValidator.validate("simple email@example.com"));
    }

    @Test
    void test_invalid_usernameWithSpecialChar_colon() {
        assertFalse(emailValidator.validate("simple:email@example.com"));
    }

    @Test
    void test_invalid_usernameWithSpecialChar_backslash() {
        assertFalse(emailValidator.validate("simple\\email@example.com"));
    }

    @Test
    void test_invalid_domain() {
        assertFalse(emailValidator.validate("email@example.com (Joe Smith)"));
    }

    @Test
    void test_invalid_domainWithoutTLD() {
        assertFalse(emailValidator.validate("email@example"));
    }

    @Test
    void test_invalid_domainFirstCharIsHyphen() {
        assertFalse(emailValidator.validate("email@-example.com"));
    }

    @Test
    void test_invalid_domainWithUnderscore() {
        assertFalse(emailValidator.validate("email@e_x_a_m_p_l_e.web"));
    }

    @Test
    void test_invalid_domainTLD() {
        assertFalse(emailValidator.validate("email@111.222.333.44444"));
    }
}