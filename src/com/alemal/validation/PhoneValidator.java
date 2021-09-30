package com.alemal.validation;

public class PhoneValidator {
    public boolean validate(String phoneNumber) {
        return isNotEmpty(phoneNumber)
                && isNumeric(phoneNumber)
                && isCorrectLength(phoneNumber);
    }

    private boolean isNotEmpty(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isBlank();
    }

    private boolean isNumeric(String phoneNumber) {
        for (char symbol : phoneNumber.toCharArray()){
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }

    public String convert(PhoneNumber.CountryCode code, String phoneNumber) {
        var numberFactory = new PhoneNumberFactory();
        var phoneNum = numberFactory.createPhoneNumber(code, phoneNumber);
        return phoneNum.convert();
    }

    private boolean isCorrectLength(String phoneNumber) {
        return phoneNumber.length() == 9;
    }
}
