package com.alemal.validation;

public class PasswordChecker {
    private final static int MIN_PASSWORD_LENGTH = 6;
    private final static String SPECIAL_CHARACTERS = "~?!@#$%^&*()_-+=[]{}<>,./: ;'\"";

    public boolean validate(String pwd) {
        return isNotEmpty(pwd)
                && hasValidLength(pwd)
                && hasUppercaseChars(pwd)
                && hasSpecialChars(pwd);
    }

    private boolean isNotEmpty(String password) {
        return password != null && !password.isBlank();
    }

    private boolean hasValidLength(String password) {
        return password.trim().length() >= MIN_PASSWORD_LENGTH;
    }

    private boolean hasUppercaseChars(String password) {
        for (char symbol : password.toCharArray()){
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSpecialChars(String password) {
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
