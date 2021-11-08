package com.alemal.UserManager.repository;

import com.alemal.UserManager.module.User;

public interface IUserRepository {
    User save(User user);

    User getById(int userId);

    User removeById(int userId);
}
