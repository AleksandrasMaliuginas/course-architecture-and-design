package com.alemal;

import com.alemal.UserManager.UserValidator;
import com.alemal.UserManager.repository.UserRepository;
import com.alemal.UserManager.service.UserService;

public class Main {
    public static void main(String[] args) {
        var app = new LibraryApp(new UserService(new UserValidator(), new UserRepository()));
        app.run();
    }
}
