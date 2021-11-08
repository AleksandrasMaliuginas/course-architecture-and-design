package com.alemal.UserManager;

import com.alemal.UserManager.module.User;
import com.company.interfaces.EmailValidation;
import com.company.interfaces.PasswordValidation;
import com.company.interfaces.PhoneValidation;
import com.company.validators.EmailValidator;
import com.company.validators.PasswordChecker;
import com.company.validators.PhoneValidator;
import com.alemal.UserManager.ValidatorHelper.Status;


public class UserValidator implements IUserValidation {
    private final EmailValidation emailValidator;
    private final PasswordValidation passwordValidator;
    private final PhoneValidation phoneValidator;

    public UserValidator() {
        this.emailValidator = new EmailValidator();
        this.passwordValidator = new PasswordChecker();
        this.phoneValidator = new PhoneValidator();
    }

    public UserValidator(EmailValidation emailValidator, PasswordValidation passwordValidator, PhoneValidation phoneValidator) {
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.phoneValidator = phoneValidator;
    }

    @Override
    public Status validate(User user) {
        if (!emailValidator.validateEmail(user.email))
            return Status.INVALID_EMAIL;

        if (!passwordValidator.checkPassword(user.password))
            return Status.INVALID_PASSWORD;

        if (!phoneValidator.validatePhone(user.phoneNumber))
            return Status.INVALID_PHONE_NUMBER;

        return Status.OK;
    }
}
