package com.accounting.service;

import com.accounting.model.User;
import com.accounting.model.UserRoles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserService {

    public void addUser(List<User> users){
        Scanner in = new Scanner(System.in);
        Validator valid = new Validator();

        try {
            System.out.print("Enter the name of the user: ");
            String name = in.next();

            System.out.print("Enter the surname of the user: ");
            String surname = in.next();

            System.out.print("Enter the email of the user: ");
            String email = valid.inputEmail();

            List<String> phoneNumbers = new ArrayList<>();
            System.out.print("How many phone numbers does the user have? ");
            int minCountPhoneNumbers = 1;
            int maxCountPhoneNumbers = 2;
            int countPhoneNumbers = valid.InputIntValue(minCountPhoneNumbers, maxCountPhoneNumbers);

            for (int i = 0; i < countPhoneNumbers; ++i) {
                System.out.print("Enter " + (i + 1) + " phone number: ");
                String phoneNumber = valid.inputPhoneNumber();

                phoneNumbers.add(phoneNumber);
            }

            List<String> roles = new ArrayList<>();
            System.out.println("Choose an user role:");
            System.out.println(UserRoles.getAllRoles());

            int minRoleNumber = 1;
            int maxRoleNumber = UserRoles.values().length;
            int roleNumber = valid.InputIntValue(minRoleNumber, maxRoleNumber);
            roles.add(UserRoles.getNameById(roleNumber - 1));

            if (roleNumber < maxRoleNumber) {
                System.out.println("Do you want to choose another user role? (yes/no)");
                boolean answer = valid.inputYesNo();

                if (answer) {
                    System.out.println("Choose another user role:");
                    System.out.println(UserRoles.getAllRoles());
                    roles.add(valid.inputRole());
                }
            }

            User user = new User.UserBuilder()
                    .withId(users.size() + 1)
                    .withName(name)
                    .withSurname(surname)
                    .withEmail(email)
                    .withPhoneNumbers(phoneNumbers)
                    .withRoles(roles)
                    .build();

            users.add(user);
        } catch (Exception exception) {
            System.out.println("Something went wrong");
        }
    }

    public void addUser(List<User> users, User user) {
        users.add(user);
    }

    public void removeUser(List<User> users, User user) {
        users.remove(user);
    }

    public void removeUser(List<User> users, int index) {
        users.remove(index);
    }

    public void viewAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

}
