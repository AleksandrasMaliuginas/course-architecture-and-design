package com.alemal.validation;

public class PhoneNumberFactory implements PhoneNumberFactoryService {
    @Override
    public PhoneNumber createPhoneNumber(String phoneNumber) {
        return new PhoneNumberLT(phoneNumber);
    }
}
