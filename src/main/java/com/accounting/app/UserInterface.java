package com.accounting.app;

import com.accounting.model.User;
import com.accounting.service.DataBaseService;
import com.accounting.service.UserService;
import com.accounting.validation.UserValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    public void start() {
        UserService userService = new UserService();
        UserValidator validate = new UserValidator();
        DataBaseService dataBaseService = new DataBaseService();

        List<User> users = new ArrayList<>();

        String dataBaseName = "src\\main\\resources\\database.txt";

        boolean work = true;

        while (work) {

            if (users.isEmpty() && new File(dataBaseName).length() != 0) {
                dataBaseService.readDataBase(users, dataBaseName);
            }

            int minOption = 1;
            int maxOption = 5;

            System.out.println("Select an option (" + minOption + "-" + maxOption + "):");
            System.out.println("1 - Add the user");
            System.out.println("2 - Edit the user");
            System.out.println("3 - Remove the user");
            System.out.println("4 - Viewing existing users");
            System.out.println("5 - Exit");

            int userChoice = 0;
            userChoice = validate.inputIntValue(minOption, maxOption);
            System.out.println();

            int minCountUsers = 1;
            int maxCountUsers = users.size();

            switch (userChoice) {
                case 1: // add the user
                    userService.addUser(users);
                    dataBaseService.writeDataBase(users, dataBaseName);
                    break;
                case 2: // edit the user
                    if (users.isEmpty()) {
                        System.out.println("No users to edit");
                        break;
                    }

                    System.out.println("Select the user to edit (" + minCountUsers + "-" + maxCountUsers + "):");
                    userService.viewAllUsers(users);
                    int editUserChoice = 0;
                    try {
                        editUserChoice = validate.inputIntValue(minCountUsers, maxCountUsers);
                    } catch (Exception exception) {
                        System.out.println("Something went wrong");
                    }

                    userService.editUser(users, editUserChoice - 1);
                    userService.updateUsersId(users);
                    dataBaseService.writeDataBase(users, dataBaseName);
                    break;
                case 3: // remove the user
                    if (users.isEmpty()) {
                        System.out.println("No users to remove");
                        break;
                    }

                    System.out.println("Select the user to remove (" + minCountUsers + "-" + maxCountUsers + "):");
                    userService.viewAllUsers(users);
                    int removeUserChoice = 0;
                    removeUserChoice = validate.inputIntValue(minCountUsers, maxCountUsers);

                    userService.removeUser(users, removeUserChoice - 1);
                    userService.updateUsersId(users);
                    dataBaseService.writeDataBase(users, dataBaseName);
                    break;
                case 4: // viewing existing users
                    if (users.isEmpty()) {
                        System.out.println("No users to view");
                    } else {
                        System.out.println("Existing users:");
                        userService.viewAllUsers(users);
                    }
                    break;
                default: // exit
                    work = false;
                    break;
            }

            System.out.println();

        }
    }

}
