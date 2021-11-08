package com.alemal.UserManager.service;

import com.alemal.UserManager.IUserValidation;
import com.alemal.UserManager.UserValidator;
import com.alemal.UserManager.ValidatorHelper;
import com.alemal.UserManager.module.User;
import com.alemal.UserManager.repository.IUserRepository;
import com.alemal.UserManager.repository.UserRepository;
import com.alemal.validation.EmailValidator;
import com.company.interfaces.EmailValidation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    IUserRepository userRepository = mock(UserRepository.class);
    @Mock
    IUserValidation userValidator = mock(UserValidator.class);
    IUserService userService;

    User defaultUser;

    @BeforeEach
    void setUp() {
        userService = new UserService(userValidator, userRepository);
        defaultUser = new User();
        defaultUser.id = 3;
        defaultUser.firstname = "John";
        defaultUser.lastname = "Lennon";
        defaultUser.phoneNumber = "861234567";
        defaultUser.email = "johnlennon@gmail.com";
        defaultUser.password = "pass+W0RD";
    }

    @Test
    void find() {
        when(userRepository.getById(Mockito.anyInt())).thenReturn(defaultUser);
        var user = userService.find(3);

        assertEquals(defaultUser, user);
    }

    @Test
    void add() {
        when(userRepository.save(defaultUser)).thenReturn(defaultUser);
        when(userValidator.validate(Mockito.any())).thenReturn(ValidatorHelper.Status.OK);

        var status = userService.add(defaultUser);
        assertEquals(ValidatorHelper.Status.OK, status);
    }

    @Test
    void addInvalidEmail() {
        when(userRepository.save(defaultUser)).thenReturn(defaultUser);
        when(userValidator.validate(Mockito.any())).thenReturn(ValidatorHelper.Status.INVALID_EMAIL);

        defaultUser.email = "asdfasdf@asdf";

        var status = userService.add(defaultUser);
        assertEquals(ValidatorHelper.Status.INVALID_EMAIL, status);
    }

    @Test
    void addInvalidPhoneNumber() {
        when(userRepository.save(defaultUser)).thenReturn(defaultUser);
        when(userValidator.validate(Mockito.any())).thenReturn(ValidatorHelper.Status.INVALID_PHONE_NUMBER);

        defaultUser.phoneNumber = "1234";

        var status = userService.add(defaultUser);
        assertEquals(ValidatorHelper.Status.INVALID_PHONE_NUMBER, status);
    }

    @Test
    void addInvalidPassword() {
        when(userRepository.save(defaultUser)).thenReturn(defaultUser);
        when(userValidator.validate(Mockito.any())).thenReturn(ValidatorHelper.Status.INVALID_PASSWORD);

        defaultUser.password = "password";

        var status = userService.add(defaultUser);
        assertEquals(ValidatorHelper.Status.INVALID_PASSWORD, status);
    }

    @Test
    void update() {
        when(userRepository.save(defaultUser)).thenReturn(defaultUser);
        when(userValidator.validate(Mockito.any())).thenReturn(ValidatorHelper.Status.OK);

        var status = userService.update(defaultUser);
        assertEquals(ValidatorHelper.Status.OK, status);
    }

    @Test
    void delete() {
        when(userRepository.removeById(Mockito.anyInt())).thenReturn(defaultUser);

        var user = userService.delete(defaultUser.id);
        assertEquals(defaultUser, user);
    }
}