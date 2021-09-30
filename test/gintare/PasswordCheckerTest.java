package gintare;

import com.alemal.gintare.validation.PasswordChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Abbreviation TM stands for 'tested method'

public class PasswordCheckerTest {
    //    TM checks if there was data to be validated
    @Test
    void TestHasInput(){
//        Data was entered
        assertTrue(PasswordChecker.hasInput("12345678"));
//        Empty input
        assertFalse(PasswordChecker.hasInput(""));
//        Input contains whitespaces only
        assertFalse(PasswordChecker.hasInput("  "));
//        Nothing was assigned to passed variable
        assertFalse(PasswordChecker.hasInput(null));
    }
    /*   TM checks if password length meets the minimum length criteria.
        Assuming that the minimum length X=7*/
    @Test
    void TestIsLengthValid(){
//        Password length is above minimumLength
        assertTrue(PasswordChecker.isLengthValid("12345678"));
//        Password length is equal to minimumLength
        assertTrue(PasswordChecker.isLengthValid("1234567"));
//        Password is too short
        assertFalse(PasswordChecker.isLengthValid("123456"));
    }

    //    TM checks if password contains Uppercase letters
    @Test
    void TestContainsUppercaseLetters(){
//        Contains Uppercase letter at the beginning
        assertTrue(PasswordChecker.containsUppercaseLetters("Password"));
//        Contains Uppercase letter in the middle
        assertTrue(PasswordChecker.containsUppercaseLetters("passWord"));
//        Does not contain Uppercase letter
        assertFalse(PasswordChecker.containsUppercaseLetters("password"));
    }

    //    TM checks if password contains special character
    @Test
    void TestContainsSpecialCharacter(){
        char validSpecialChar = '@';
        char invalidSpecialChar = '/';
//        Contains a valid special character
        assertTrue(PasswordChecker.containsSpecialCharacter("p" + validSpecialChar + "ssword"));
//        Contains a special character that was not in the list
        assertFalse(PasswordChecker.containsSpecialCharacter("p" + invalidSpecialChar + "ssword"));
    }
}