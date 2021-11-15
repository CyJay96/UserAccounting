package com.accounting.service;

import com.accounting.builder.UserBuilder;
import com.accounting.model.User;
import com.accounting.model.UserRoles;
import com.accounting.validation.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private final int minCountPhones = 1;
    private final int maxCountPhones = 3;

    private final int minCountRoles = 1;
    private final int maxCountRoles = 2;

    private final int minSizeRolesList = 1;
    private final int maxSizeRolesList = UserRoles.values().length;

    public void addUser(List<User> users){
        Scanner in = new Scanner(System.in);
        UserValidator valid = new UserValidator();

        try {
            // add name
            System.out.print("Enter the name of the user: ");
            String name = in.next();

            // add surname
            System.out.print("Enter the surname of the user: ");
            String surname = in.next();

            // add email
            System.out.print("Enter the email of the user (xxx@xxx.xxx): ");
            String email = valid.inputEmail();

            // add phone numbers
            List<String> phoneNumbers = new ArrayList<>();
            System.out.print("How many phone numbers does the user have? ");
            int countPhoneNumbers = valid.inputIntValue(minCountPhones, maxCountPhones);

            for (int i = 0; i < countPhoneNumbers; ++i) {
                System.out.print("Enter " + (i + 1) + " phone number (375 xx xxxxxxx): ");
                String phoneNumber = valid.inputPhoneNumber();

                phoneNumbers.add(phoneNumber);
            }

            // roles
            List<String> roles = new ArrayList<>();
            System.out.println("Choose an user role:");
            System.out.println(UserRoles.getAllRoles());

            int firstRoleNumber = valid.inputIntValue(minSizeRolesList, maxSizeRolesList);
            roles.add(UserRoles.getNameById(firstRoleNumber - 1));

            if (firstRoleNumber < maxSizeRolesList) {
                System.out.println("Do you want to choose another user role? (yes/no)");
                boolean answer = valid.inputYesNo();

                if (answer) {
                    System.out.println("Choose another user role:");
                    System.out.println(UserRoles.getAllRoles());
                    roles.add(valid.inputAnotherRole(UserRoles.getNameById(firstRoleNumber - 1)));
                }
            }

            // build user
            User user = new UserBuilder()
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

    public void editUser(List<User> users, int id) {
        Scanner in = new Scanner(System.in);
        UserValidator valid = new UserValidator();

        int minEditChoice = 1;
        int maxEditChoice = 6;

        System.out.println("What do you want to edit? (" + minEditChoice + "-" + maxEditChoice + ")");

        System.out.println("1 - Name: " + users.get(id).getName());
        System.out.println("2 - Surname: " + users.get(id).getSurname());
        System.out.println("3 - Email: " + users.get(id).getEmail());
        System.out.println("4 - Phone numbers: " + users.get(id).getPhoneNumbers());
        System.out.println("5 - Roles: " + users.get(id).getRoles());
        System.out.println("6 - Exit");

        try {
            int editChoice = valid.inputIntValue(minEditChoice, maxEditChoice);

            switch (editChoice) { // edit user
                case 1: // edit name
                    System.out.println("Current name: " + users.get(id).getName());
                    System.out.print("Enter the new name: ");
                    String name = in.next();
                    users.get(id).setName(name);
                    break;
                case 2: // edit surname
                    System.out.println("Current surname: " + users.get(id).getSurname());
                    System.out.print("Enter the new surname: ");
                    String surname = in.next();
                    users.get(id).setSurname(surname);
                    break;
                case 3: // edit email
                    System.out.println("Current email: " + users.get(id).getEmail());
                    System.out.print("Enter the new email (xxx@xxx.xxx): ");
                    String email = valid.inputEmail();
                    users.get(id).setEmail(email);
                    break;
                case 4: // edit phone numbers
                    System.out.println("Current phone numbers: " + users.get(id).getPhoneNumbers());
                    System.out.println("1-Add 2-Edit existing 3-Remove existing (1-3)");
                    int minEditPhoneChoice = 1;
                    int maxEditPhoneChoice = 3;
                    int phoneEditChoice = valid.inputIntValue(minEditPhoneChoice, maxEditPhoneChoice);

                    switch (phoneEditChoice) {
                        case 1: // add phone number
                            if (users.get(id).getPhoneNumbers().size() < maxCountPhones) {
                                System.out.println("Enter the new phone number (375 xx xxxxxxx): ");
                                String phoneNumber = valid.inputPhoneNumber();
                                users.get(id).getPhoneNumbers().add(phoneNumber);
                            } else {
                                System.out.println("No more phone numbers can be added");
                            }
                            break;
                        case 2: // editing existing phone number
                            int currentCountPhones = users.get(id).getPhoneNumbers().size();

                            if (currentCountPhones == 0) {
                                System.out.println("No phones to edit");
                                break;
                            }

                            System.out.print("Which phone number do you want to edit? (1-" + currentCountPhones + "): ");
                            int currentPhoneEditChoice = valid.inputIntValue(minCountPhones, currentCountPhones);
                            System.out.print("Enter the new phone number (375 xx xxxxxxx): ");
                            String phoneNumber = valid.inputPhoneNumber();
                            users.get(id).getPhoneNumbers().set(currentPhoneEditChoice - 1, phoneNumber);
                            break;
                        case 3: // removing existing phone number
                            currentCountPhones = users.get(id).getPhoneNumbers().size();

                            if (currentCountPhones == 0) {
                                System.out.println("No phones to remove");
                                break;
                            }

                            System.out.print("Which phone number do you want to remove? (1-" + currentCountPhones + "): ");
                            currentPhoneEditChoice = valid.inputIntValue(minCountPhones, currentCountPhones);
                            users.get(id).getPhoneNumbers().remove(currentPhoneEditChoice - 1);
                            break;
                        default:
                            System.out.println("Please, try again");
                    }
                    break;
                case 5: // edit roles
                    int indexFirstRole = 0;
                    int indexSecondRole = 1;

                    System.out.println("Current roles: " + users.get(id).getRoles());
                    int minEditRolesChoice = 1;
                    int maxEditRolesChoice = 4;
                    System.out.println("1-Add 2-Edit existing 3-Remove existing 4-Exit (" + minEditRolesChoice +
                            "-" + maxEditRolesChoice + ")");
                    int rolesEditChoice = valid.inputIntValue(minEditRolesChoice, maxEditRolesChoice);

                    switch (rolesEditChoice) {
                        case 1: // add role
                            boolean wasRolesEmpty = false;

                            if (users.get(id).getRoles().isEmpty()) {
                                wasRolesEmpty = true;

                                System.out.println("Choose an user role:");
                                System.out.println(UserRoles.getAllRoles());
                                int firstRoleNumber = valid.inputIntValue(minSizeRolesList, maxSizeRolesList);

                                users.get(id).getRoles().add(UserRoles.getNameById(firstRoleNumber - 1));
                            }

                            if (users.get(id).getRoles().size() < maxCountRoles) {
                                if (wasRolesEmpty) {
                                    System.out.println("Do you want to choose another user role? (yes/no)");
                                    boolean answer = valid.inputYesNo();
                                    if (!answer) {
                                        break;
                                    }
                                }

                                System.out.println("Choose an user role:");
                                System.out.println(UserRoles.getAllRoles());
                                String firstRoleName = UserRoles.valueOf(users.get(id).getRoles().get(indexFirstRole)).name();
                                if (!firstRoleName.equals(UserRoles.SUPER_ADMIN.name())) {
                                    users.get(id).getRoles().add(valid.inputAnotherRole(firstRoleName));
                                } else {
                                    System.out.println("No more roles can be added");
                                }
                            } else {
                                System.out.println("No more roles can be added");
                            }
                            break;
                        case 2: // editing existing role
                            int currentCountRoles = users.get(id).getRoles().size();

                            if (currentCountRoles == 0) {
                                System.out.println("No roles to edit");
                                break;
                            }

                            System.out.println("Current roles: " + users.get(id).getRoles());

                            if (currentCountRoles < maxCountRoles) { // current count of roles = 1
                                System.out.println("Choose a new user role:");
                                System.out.println(UserRoles.getAllRoles());
                                int newRoleNumber = valid.inputIntValue(minSizeRolesList, maxSizeRolesList);
                                users.get(id).getRoles().set(indexFirstRole, UserRoles.getNameById(newRoleNumber - 1));
                            } else { // current count of roles = 2
                                System.out.print("Which role do you want to edit? (1-" + currentCountRoles + "): ");
                                int currentRoleEditChoice = valid.inputIntValue(minCountRoles, currentCountRoles);

                                System.out.println("Choose a new user role:");
                                System.out.println(UserRoles.getAllRoles());

                                switch (currentRoleEditChoice) {
                                    case 1: // edit the first role
                                        String secondRoleName = UserRoles.valueOf(users.get(id).getRoles().get(indexSecondRole)).name();
                                        String newFirstRole = valid.inputAnotherRole(secondRoleName);
                                        users.get(id).getRoles().set(indexFirstRole, newFirstRole);
                                        break;
                                    case 2: // edit the second role
                                        String firstRoleName = UserRoles.valueOf(users.get(id).getRoles().get(indexFirstRole)).name();
                                        String newSecondRole = valid.inputAnotherRole(firstRoleName);
                                        users.get(id).getRoles().set(indexSecondRole, newSecondRole);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        case 3: // removing existing role
                            currentCountRoles = users.get(id).getRoles().size();

                            if (currentCountRoles == 0) {
                                System.out.println("No roles to remove");
                                break;
                            }

                            System.out.println("Current roles: " + users.get(id).getRoles());
                            System.out.print("Which role do you want to remove? (1-" + currentCountRoles + "): ");
                            int numberRoleRemove = valid.inputIntValue(minCountRoles, currentCountRoles);
                            users.get(id).getRoles().remove(numberRoleRemove - 1);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong");
        }
    }

    public void removeUser(List<User> users, User user) {
        users.remove(user);
    }

    public void removeUser(List<User> users, int id) {
        users.remove(id);
    }

    public void viewUserById(List<User> users, int id) {
        System.out.println(users.get(id));
    }

    public void viewAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

}
