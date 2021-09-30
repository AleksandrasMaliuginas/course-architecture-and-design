package com.alemal.gintare.validation;

public class PhoneValidator {

    public static boolean hasInput(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isBlank();
    }

    public static boolean containsNumbersOnly(String phoneNumber) {
        for (char symbol : phoneNumber.toCharArray()){
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }

    public static String checkPrefix(String countryCode, String phoneNumber) {
        if (countryCode.equals("LT") && phoneNumber.toCharArray()[0] == '8') {
            return "+370" + phoneNumber.substring(1);
        }

        return phoneNumber;
    }

    public static boolean isCorrectLength(String countryCode, String phoneNumber) {
        return countryCode.equals("LT") && phoneNumber.length() == 9;
    }
}
