package com.alemal.gintare.validation;

public class EmailValidator {
    private final static String INVALID_CHARACTERS = "#";
    private final static String[] VALID_TLDs = {".com", ".net", ".org", ".lt"};

    public static boolean hasInput(String email) {
        return email != null && !email.isBlank();
    }

    public static boolean containsAtSign(String email) {
        return email.contains("@");
    }

    public static boolean containsNoInvalidChars(String email) {
        for (var invalidChar : INVALID_CHARACTERS.split("")) {
            if (email.contains(invalidChar)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDomainCorrect(String email) {
        var emailParts = email.split("@");
        var domain = emailParts[emailParts.length - 1];
        return domain.contains("gmail");
    }

    public static boolean isTLDCorrect(String email) {
        var emailParts = email.split("\\.");
        var domain = emailParts[emailParts.length - 1];
        return !domain.contains("random");
    }
}
