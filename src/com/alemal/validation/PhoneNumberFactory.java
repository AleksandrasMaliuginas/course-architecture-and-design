package com.alemal.validation;

public class PhoneNumberFactory implements PhoneNumberFactoryService {
    @Override
    public PhoneNumber createPhoneNumber(PhoneNumber.CountryCode code, String phoneNumber) {
        switch (code) {
            case LT -> { return new PhoneNumberLT(phoneNumber); }
            default -> throw new IllegalArgumentException();
        }
    }
}
