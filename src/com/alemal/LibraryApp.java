package com.alemal;

import com.alemal.UserManager.IUserService;
import com.alemal.UserManager.module.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryApp {
    private Scanner scanner;
    private BufferedReader input;
    private final IUserService userService;

    public LibraryApp(IUserService userService) {
        this.userService = userService;
    }

    public void run() {
        input = new BufferedReader(new InputStreamReader(System.in));
        scanner = new Scanner(System.in);
        handleInput();

        System.out.println("Goodbye");
    }

    private void handleInput() {
        outputCommandList();

        try {
            handleCommand(scanner.nextInt());
        } catch (InputMismatchException | IOException e) {
            invalidCommand();
            handleInput();
        }
    }

    private void handleCommand(int cmd) throws IOException {
        switch (cmd) {
            case 0 -> { return; }
            case 1 -> findUser();
            case 8 -> createUser();
            case 2 -> createDummy();
            case 3 -> updateUser();
            case 4 -> deleteUser();
            default -> invalidCommand();
        }

        handleInput();
    }

    private void findUser() {
        System.out.print("User ID: ");
        var userId = scanner.nextInt();

        var user = userService.find(userId);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("No user found with ID = " + userId);
        }
    }

    private void updateUser() throws IOException {
        System.out.print("User ID: ");
        var userId = scanner.nextInt();
        var user = userService.find(userId);

        if (user == null) {
            System.out.println("No user found with ID = " + userId);
            return;
        }

        var updatedUser = new User();
        updatedUser.id = user.id;

        System.out.print("Email: [" + user.email + "] ");
        var email = input.readLine();
        if (email.length() > 0)
            updatedUser.email = email;

        System.out.print("Password: [" + user.password + "] ");
        var password = input.readLine();
        if (password.length() > 0)
            updatedUser.password = password;

        System.out.print("Phone number: [" + user.phoneNumber + "] ");
        var phoneNumber = input.readLine();
        if (phoneNumber.length() > 0)
            updatedUser.phoneNumber = phoneNumber;

        var status = userService.update(user);
        switch (status) {
            case OK -> {
                System.out.println("User updated:");
                System.out.println(user);
            }
            case INVALID_EMAIL -> {
                System.out.println("Invalid email.");
            }
            case INVALID_PASSWORD -> {
                System.out.println("Invalid password.");
            }
            case INVALID_PHONE_NUMBER -> {
                System.out.println("Invalid phone number.");
            }
        }
    }

    private void createUser() throws IOException {
        var user = new User();
        System.out.print("Email: ");
        user.email = input.readLine();

        System.out.print("Password: ");
        user.password = input.readLine();

        System.out.print("Phone number: ");
        user.phoneNumber = input.readLine();

        var status = userService.add(user);
        switch (status) {
            case OK -> {
                System.out.println("New User created:");
                System.out.println(user);
            }
            case INVALID_EMAIL -> {
                System.out.println("Invalid email.");
            }
            case INVALID_PASSWORD -> {
                System.out.println("Invalid password.");
            }
            case INVALID_PHONE_NUMBER -> {
                System.out.println("Invalid phone number.");
            }
        }
    }

    private void createDummy() {
        var user = new User();
        user.email = "asdf@gmail.com";
        user.password = "asdf+ASDF+1234";
        user.phoneNumber = "861234567";

        userService.add(user);
        System.out.println("Dummy user created:");
        System.out.println(user);
    }

    private void deleteUser() {
        System.out.print("User ID: ");
        var userId = scanner.nextInt();

        var user = userService.delete(userId);

        if (user != null) {
            System.out.println("User deleted successfully.");
            System.out.println(user);
        } else {
            System.out.println("Cannot delete User with ID = " + userId);
        }
    }

    private void invalidCommand() {
        System.err.println("Invalid command or input.");
    }

    private void outputCommandList() {
        var commands = new String[] {"[1] Find User", "[2] Create new User", "[3] Update User", "[4] Delete User", "[0] EXIT"};
        System.out.println("Choose command:");

        for (var cmdStr: commands) {
            System.out.println(cmdStr);
        }
    }
}
