package com.alemal.validation;

public abstract class PhoneNumber {
    protected String phoneNumber;

    public PhoneNumber(String countryCode, String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public abstract boolean validate();
}
