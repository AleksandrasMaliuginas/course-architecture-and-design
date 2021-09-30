package gintare;

import com.alemal.gintare.validation.EmailValidator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


//Abbreviation TM stands for 'tested method'

public class EmailValidatorTest {
    //    TM checks if there was data to be validated
    @Test
    void TestHasInput(){
//        Data was entered
        assertTrue(EmailValidator.hasInput("12345678"));
//        Empty input
        assertFalse(EmailValidator.hasInput(""));
//        Input contains whitespaces only
        assertFalse(EmailValidator.hasInput("  "));
//        Nothing was assigned to passed variable
        assertFalse(EmailValidator.hasInput(null));
    }
    //    TM checks if email address contains @ sign
    @Test
    void TestContainsAtSign(){
//        Correct email
        assertTrue(EmailValidator.containsAtSign("name@email.com"));
//        Does not contain @ sign
        assertFalse(EmailValidator.containsAtSign("name.email.com"));
    }

    //    TM checks if email does not contain any of the invalid characters
    @Test
    void TestContainsNoInvalidChars(){
        char invalidChar = '#';
//        Correct email
        assertTrue(EmailValidator.containsNoInvalidChars("name@email.com"));
//        Contains invalid character
        assertFalse(EmailValidator.containsNoInvalidChars("n" + invalidChar +"me@email.com"));
    }

    //    TM checks if email address contains correct domain
    @Test
    void TestIsDomainCorrect(){
        String validDomain = "gmail";
        String invalidDomain = "random";
//        Correct email
        assertTrue(EmailValidator.isDomainCorrect("name@" + validDomain + ".com"));
//        Invalid domain
        assertFalse(EmailValidator.isDomainCorrect("name@" + invalidDomain + ".com"));
    }

    //    TM checks if email contains correct TLD
    @Test
    void TestIsTLDCorrect(){
        String validTLD = ".com";
        String invalidTLD = ".random";
//        Correct email
        assertTrue(EmailValidator.isTLDCorrect("name@gmail" + validTLD));
//        Invalid TLD
        assertFalse(EmailValidator.isTLDCorrect("name@gmail" + invalidTLD));
    }
}