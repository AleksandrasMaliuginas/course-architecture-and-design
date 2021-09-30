package com.alemal.validation;

public class PhoneNumberLT extends PhoneNumber {

    private final int PHONE_NUMBER_LENGTH = 9;

    public PhoneNumberLT(String phoneNumber) {
        super(CountryCode.LT, phoneNumber);
    }

    @Override
    public boolean validate() {
        return validateLength(phoneNumber);
    }

    @Override
    public String convert() {
        return "+370" + phoneNumber.substring(1);
    }

    private boolean validateLength(String phoneNumber) {
        return false;
    }
}
