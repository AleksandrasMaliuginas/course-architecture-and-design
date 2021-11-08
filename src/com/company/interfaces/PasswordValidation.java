package com.company.interfaces;

public interface PasswordValidation {
    boolean checkPassword(String password);
    boolean checkPassword(String password, int length);
    boolean checkPassword(String password, String specialSymbols);
    boolean checkPassword(String password, String specialSymbols, int length);
}

