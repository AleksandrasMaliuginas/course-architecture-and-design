package com.alemal.validation;

public class PhoneNumberLT extends PhoneNumber {

    private final int PHONE_NUMBER_LENGTH = 9;

    public PhoneNumberLT(String phoneNumber) {
        super("+370", phoneNumber);
    }

    @Override
    public boolean validate() {
        return validateLength(phoneNumber);
    }

    private boolean validateLength(String phoneNumber) {
        return false;
    }

    private void convertNumber(String phoneNumber) {

    }
}
