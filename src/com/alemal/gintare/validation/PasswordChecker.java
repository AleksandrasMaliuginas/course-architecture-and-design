package com.alemal.gintare.validation;

public class PasswordChecker {
    private final static int MIN_PASSWORD_LENGTH = 7;
    private final static String SPECIAL_CHARACTERS = "!@#$%";

    public static boolean hasInput(String password) {
        return password != null && !password.isBlank();
    }

    public static boolean isLengthValid(String password) {
        return password.trim().length() >= MIN_PASSWORD_LENGTH;
    }

    public static boolean containsUppercaseLetters(String password) {
        for (char symbol : password.toCharArray()){
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsSpecialCharacter(String password) {
        for (char symbol : password.toCharArray()){
            if (!Character.isLetterOrDigit(symbol)
                    && SPECIAL_CHARACTERS.contains(String.valueOf(symbol))
            ) {
                return true;
            }
        }
        return false;
    }
}
