package com.alemal.UserManager;

import com.alemal.UserManager.module.User;
import com.alemal.UserManager.ValidatorHelper.Status;

public interface IUserService {
    User find(int usedId);
    Status add(User user);
    Status update(User user);
    User delete(int userId);
}
