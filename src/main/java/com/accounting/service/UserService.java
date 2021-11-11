package com.accounting.service;

import com.accounting.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private List<String> userRoles = new ArrayList<>();
    private List<Integer> roleLevers = new ArrayList<>();

    public UserService() { //USER (ур1), CUSTOMER(ур1), ADMIN (ур2), PROVIDER(ур2), SUPER_ADMIN
        userRoles.add("USER");
        userRoles.add("CUSTOMER");
        userRoles.add("ADMIN");
        userRoles.add("PROVIDER");
        userRoles.add("SUPER_ADMIN");

        roleLevers.add(1);
        roleLevers.add(1);
        roleLevers.add(2);
        roleLevers.add(2);
        roleLevers.add(3);
    }

    public void addUser(List<User> users){
        Scanner in = new Scanner(System.in);

        try {
            System.out.print("Enter the name of the user: ");
            String name = in.next();

            System.out.print("Enter the surname of the user: ");
            String surname = in.next();

            System.out.print("Enter the email of the user: ");
            String email = in.next();

            System.out.print("How many phone numbers does the user have? ");
            int countPhoneNumbers = in.nextInt();

            List<String> phoneNumbers = new ArrayList<>();
            for (int i = 0; i < countPhoneNumbers; ++i) {
                System.out.print("Enter " + (i + 1) + " phone number: ");
                String phoneNumber = in.next();

                phoneNumbers.add(phoneNumber);
            }

            System.out.println("How many roles does the user have? ");
            int countRoles = in.nextInt();

            List<String> roles = new ArrayList<>();
            for (int i = 0; i < countRoles; ++i) {
                System.out.println("Choose an user role:");
                for (int j = 0; j < userRoles.size(); ++j) {
                    System.out.print((j + 1) + "-" + userRoles.get(j) + " ");
                }
                System.out.println();

                int roleNumber = in.nextInt();
                roles.add(userRoles.get(roleNumber - 1));
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
