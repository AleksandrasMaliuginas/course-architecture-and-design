package com.alemal.UserManager.service;

import com.alemal.UserManager.IUserValidation;
import com.alemal.UserManager.ValidatorHelper.Status;
import com.alemal.UserManager.module.User;
import com.alemal.UserManager.repository.IUserRepository;

public class UserService implements IUserService {
    private final IUserValidation userValidator;
    private final IUserRepository userRepository;

    public UserService(IUserValidation userValidation, IUserRepository userRepository) {
        userValidator = userValidation;
        this.userRepository = userRepository;
    }

    @Override
    public User find(int usedId) {
        return userRepository.getById(usedId);
    }

    @Override
    public Status add(User user) {
        var status = userValidator.validate(user);

        if (status == Status.OK) {
            user.id = 0;
            user = userRepository.save(user);
        }

        return status;
    }

    @Override
    public Status update(User user) {
        var status = userValidator.validate(user);

        if (status == Status.OK) {
            user = userRepository.save(user);
        }

        return status;
    }

    @Override
    public User delete(int userId) {
        return userRepository.removeById(userId);
    }
}
