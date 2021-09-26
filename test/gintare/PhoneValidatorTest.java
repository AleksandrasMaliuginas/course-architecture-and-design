package gintare;

import com.alemal.gintare.validation.PhoneValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Abbreviation TM stands for 'tested method'

public class PhoneValidatorTest {
    //    TM checks if there was data to be validated
    @Test
    void TestHasInput(){
//        Data was entered
        assertTrue(PhoneValidator.hasInput("12345678"));
//        Empty input
        assertFalse(PhoneValidator.hasInput(""));
//        Input contains whitespaces only
        assertFalse(PhoneValidator.hasInput("  "));
//        Nothing was assigned to passed variable
        assertFalse(PhoneValidator.hasInput(null));
    }
    //    TM checks if input contains numbers only
    @Test
    void TestContainsNumbersOnly(){
//        Contains only numbers
        assertTrue(PhoneValidator.containsNumbersOnly("12346789"));
//        Contains a letter
        assertFalse(PhoneValidator.containsNumbersOnly("a2346789"));
    }

    //    Test checks if TM changes prefix according to country code
    @Test
    void TestCheckPrefix(){
        String phoneNumber = "12345678";
        String countryCode = "LT";

//        We're testing LT phone number validation, so it should change prefix from 8 to +370

//        Should change the prefix
        assertEquals("+370" + phoneNumber, PhoneValidator.checkPrefix(countryCode, "8" + phoneNumber));
//        Should not change the prefix
        assertEquals("6" + phoneNumber, PhoneValidator.checkPrefix(countryCode, "6" + phoneNumber));
    }

    //    TM checks if input meets phone number length requirements based on country code
    @Test
    void TestIsCorrectLength(){
        String phoneNumber_Correct = "861234567";
        String phoneNumber_Incorrect = "86123456";
        String countryCode = "LT";

//        Correct phone number
        assertTrue(PhoneValidator.isCorrectLength(countryCode, phoneNumber_Correct));
//        Incorrect phone number
        assertFalse(PhoneValidator.isCorrectLength(countryCode, phoneNumber_Incorrect));
    }
}