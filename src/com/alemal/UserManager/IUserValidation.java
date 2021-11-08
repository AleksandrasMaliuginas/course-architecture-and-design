package com.alemal.UserManager;

import com.alemal.UserManager.module.User;
import com.alemal.UserManager.ValidatorHelper.Status;

public interface IUserValidation {
    Status validate(User user);
}
