package com.alemal.validation;

public interface PhoneNumberFactoryService {
    PhoneNumber createPhoneNumber(PhoneNumber.CountryCode code, String phoneNumber);
}
