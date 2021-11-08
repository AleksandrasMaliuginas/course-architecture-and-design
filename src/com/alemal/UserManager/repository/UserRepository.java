package com.alemal.UserManager.repository;

import com.alemal.UserManager.module.User;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private final String filename = "users.data";
    private final List<User> users;

    public UserRepository() {
        users = new LinkedList<>();
        loadUsers();
    }

    private void loadUsers() {
        try {
            serializeData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveUsers() {
        try {
            writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData() throws IOException {
        var fos = new FileOutputStream(filename);
        var oos = new ObjectOutputStream(fos);

        for (var user : users) {
            oos.writeObject(user);
        }
    }

    private void serializeData() throws IOException, ClassNotFoundException {
        var fin = new FileInputStream(filename);
        var ois = new ObjectInputStream(fin);

        Object user;
        while ((user = ois.readObject()) != null) {
            users.add((User) user);
        }

        ois.close();
    }

    private int nextUserId() {
        return users.size() <= 0 ? 1 :
                users.stream()
                        .max(Comparator.comparing(user -> user.id))
                        .get().id;
    }

    @Override
    public User save(User user) {
        if (user.id == 0) {
            user.id = nextUserId();
        } else {
            users.remove(getById(user.id));
        }
        users.add(user);

        saveUsers();
        return user;
    }

    @Override
    public User getById(int userId) {
        return users.stream()
                .filter(user -> user.id == userId)
                .findFirst().orElse(null);
    }

    @Override
    public User removeById(int userId) {
        var user = getById(userId);
        var removed = users.remove(user);
        saveUsers();

        return removed ? user : null;
    }
}
