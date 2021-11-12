package com.accounting.app;

import com.accounting.model.User;
import com.accounting.service.UserService;
import com.accounting.service.Validator;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public void start() {
        List<User> users = new ArrayList<>();
        UserService userService = new UserService();
        Validator valid = new Validator();

        int countUsers = 1;

        for (int i = 0; i < countUsers; ++i) {
            userService.addUser(users);
        }

        userService.viewAllUsers(users);
    }

}
