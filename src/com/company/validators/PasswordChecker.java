package com.company.validators;

import com.company.interfaces.PasswordValidation;

import java.util.ArrayList;
import java.util.List;

public class PasswordChecker implements PasswordValidation {

    private int length = 8;

    private List<String> specialSymbols = new ArrayList<>();

    public PasswordChecker() {
        addSpecialSymbols("+");
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void addSpecialSymbols(String symbols)
    {
        specialSymbols.add(symbols);
    }

    public int getLength() {
        return length;
    }

    public List<String> getSpecialSymbols() {
        return specialSymbols;
    }

    private boolean isPasswordNotEmpty(String password) {
        return !password.isEmpty();
    }

    private boolean hasPasswordUpperCase(String password)
    {
        char[] passwordSymbols = password.toCharArray();
        for (char passwordSymbol:
        passwordSymbols) {
            if(Character.isUpperCase(passwordSymbol))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordNotEmptyAndNotNull(String password)
    {
        boolean isNotNull = password != null;
        boolean isNotEmpty = false;
             if(isNotNull)  {
                 isNotEmpty = isPasswordNotEmpty(password);
             }
        return isNotNull && isNotEmpty;
    }

    private boolean isPasswordLengthCorrect(String password)
    {
        return password.length() >= length;
    }

    private boolean isPasswordContainsSpecialSymbol(String password) {
        for (String specialSymbols:
             specialSymbols) {
                char[] specialSymbolsArray = specialSymbols.toCharArray();
                for (char specialSymbol:
                    specialSymbolsArray) {
                    if(password.contains(Character.toString(specialSymbol))){
                        return true;
                }
            }
        }
        return false;
    }

    private boolean checkIsPasswordValid(String password)
    {
        boolean isNotEmptyAndNotNull = isPasswordNotEmptyAndNotNull(password);
        boolean isLengthCorrect = false;
        boolean isSpecialSymbolIncluded = false;
        boolean hasUpperCase = false;

        if(isNotEmptyAndNotNull)
        {
            isLengthCorrect = isPasswordLengthCorrect(password);
            isSpecialSymbolIncluded = isPasswordContainsSpecialSymbol(password);
            hasUpperCase = hasPasswordUpperCase(password);
        }

        return isNotEmptyAndNotNull && isLengthCorrect && isSpecialSymbolIncluded && hasUpperCase;
    }

    @Override
    public boolean checkPassword(String password)
    {
        return checkIsPasswordValid(password);
    }

    @Override
    public boolean checkPassword(String password, int length) {
        setLength(length);
        checkIsPasswordValid(password);

        return checkIsPasswordValid(password);
    }

    @Override
    public boolean checkPassword(String password, String specialSymbols)
    {
        addSpecialSymbols(specialSymbols);

        return checkIsPasswordValid(password);
    }

    @Override
    public boolean checkPassword(String password, String specialSymbols, int length)
    {
        addSpecialSymbols(specialSymbols);
        setLength(length);

        return checkIsPasswordValid(password);
    }
}
