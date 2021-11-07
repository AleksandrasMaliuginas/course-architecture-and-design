package com.alemal.aivaras.interfaces;

public interface PhoneValidation {
    boolean validatePhone(String phoneNumber);
    boolean validatePhone(String phoneNumber, String countryCode, int length);
}
