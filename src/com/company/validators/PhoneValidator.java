package com.company.validators;

import com.company.interfaces.PhoneValidation;

import java.util.*;

public class PhoneValidator implements PhoneValidation {

    private Map<String, Integer> countryCodeAndLength = new HashMap<>();
    private Map<String, List<String>> prefixesToChange = new HashMap<>();
    private String phoneNumber;

    public PhoneValidator() {
        List<String> prefixToChangeListLt = new ArrayList<>();
        prefixToChangeListLt.add("8");
        setCountryCodePrefixAndLength("+370", 12);
        setPrefixToChange("+370", prefixToChangeListLt);
    }

    public void setCountryCodePrefixAndLength(String countryCode, int length) {
        countryCodeAndLength.put(countryCode, length);
    }

    public void setPrefixToChange(String countryCode, List<String> prefixToChange) {
        prefixesToChange.put(countryCode, prefixToChange);
    }

    public Map<String, Integer> getCountryCodeAndLength() {
        return countryCodeAndLength;
    }

    public Map<String, List<String>> getPrefixesToChange() {
        return prefixesToChange;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String[] prefixToChange(String phoneNumber) {
        for (String prefixToChange :
                prefixesToChange.keySet()) {
            List<String> prefixToChangeValueList = this.prefixesToChange.get(prefixToChange);
            for (String prefixToChangeValue :
                    prefixToChangeValueList) {
                if (phoneNumber.startsWith(prefixToChangeValue)) {
                    return new String[]{prefixToChange, prefixToChangeValue};
                }
            }
        }
        return null;
    }

    private String changePhoneNumberPrefix(String phoneNumber, String countryCode, String prefixToChange) {
        String phoneNumberWithoutPrefix = phoneNumber.substring(prefixToChange.length());

        return countryCode + phoneNumberWithoutPrefix;
    }

    private boolean isPhoneNumberContainsOnlyDigits(String phoneNumber) {
        char[] phoneNumberSymbols = phoneNumber.toCharArray();
        for (int i = 0; i < phoneNumberSymbols.length; i++) {
            if (i == 0 && phoneNumberSymbols[i] == '+') {
                i++;
            } else if (!Character.isDigit(phoneNumberSymbols[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isPhoneNumberNotEmpty(boolean isNotNull, String phoneNumber) {
        if (isNotNull) {
            return !phoneNumber.isBlank();
        }
        return false;
    }

    private boolean isPhoneLengthCorrect(String phoneNumber){
        for (String countryCode:
        countryCodeAndLength.keySet()) {
            if(phoneNumber.startsWith(countryCode)){
                return phoneNumber.length()== countryCodeAndLength.get(countryCode);
            }
        }
        return false;
    }

    private boolean isPhoneNumberWithCorrectCountryCode(String phoneNumber)
    {
        for (String countryCode:
        countryCodeAndLength.keySet()) {
            if(phoneNumber.startsWith(countryCode)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validatePhone(String phoneNumber, String countryCode, int length)
    {
        countryCodeAndLength.put(countryCode, length);
        return validatePhone(phoneNumber);
    }

    @Override
    public boolean validatePhone(String phoneNumber)
    {
        boolean isNotNull = phoneNumber != null;
        boolean isNotEmpty = isPhoneNumberNotEmpty(isNotNull, phoneNumber);
        boolean isCountryCodeCorrect = false;
        boolean isDigitsOnly = false;
        boolean isLengthCorrect = false;

        if(isNotNull && isNotEmpty) {
            String[] prefixToChange = prefixToChange(phoneNumber);
            boolean isTherePrefixToChange = prefixToChange!=null;
            if (isTherePrefixToChange) {
                phoneNumber = changePhoneNumberPrefix(phoneNumber, prefixToChange[0],prefixToChange[1]);
            }
            isCountryCodeCorrect = isPhoneNumberWithCorrectCountryCode(phoneNumber);
            isDigitsOnly = isPhoneNumberContainsOnlyDigits(phoneNumber);
            isLengthCorrect = isPhoneLengthCorrect(phoneNumber);
        }
        this.phoneNumber = phoneNumber;

        return isDigitsOnly && isCountryCodeCorrect && isLengthCorrect;
    }
}