package com.alemal.validation;

public abstract class PhoneNumber {
    public enum CountryCode { LT }
    protected String phoneNumber;
    protected CountryCode code;

    public PhoneNumber(CountryCode countryCode, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.code = countryCode;
    }

    public abstract boolean validate();
    public abstract String convert();
}
