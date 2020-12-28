package sample.controller;

import sample.model.User;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationChecker {
    private static List<User> allUsers = new ArrayList<>();

    public static boolean isExistUserWithThisUsername(String username) {
        return allUsers.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public static User findUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }
}
