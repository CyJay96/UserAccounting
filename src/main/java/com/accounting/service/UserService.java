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

    public void addUser(List<User> users) {
        Scanner in = new Scanner(System.in);
        UserValidator validate = new UserValidator();

        try {
            // add name
            System.out.print("Enter the name of the user: ");
            String name = in.next();

            // add surname
            System.out.print("Enter the surname of the user: ");
            String surname = in.next();

            // add email
            System.out.print("Enter the email of the user (xxx@xxx.xxx): ");
            String email = validate.inputEmail();

            // add phone numbers
            List<String> phoneNumbers = new ArrayList<>();
            System.out.print("How many phone numbers does the user have? ");
            int countPhoneNumbers = validate.inputIntValue(minCountPhones, maxCountPhones);

            for (int i = 0; i < countPhoneNumbers; ++i) {
                System.out.print("Enter " + (i + 1) + " phone number (375 xx xxxxxxx): ");
                String phoneNumber = validate.inputPhoneNumber();

                phoneNumbers.add(phoneNumber);
            }

            // roles
            List<String> roles = new ArrayList<>();
            System.out.println("Choose an user role:");
            System.out.println(UserRoles.getAllRoles());

            int firstRoleNumber = validate.inputIntValue(minSizeRolesList, maxSizeRolesList);
            roles.add(UserRoles.getNameById(firstRoleNumber - 1));

            if (firstRoleNumber < maxSizeRolesList) {
                System.out.println("Do you want to choose another user role? (yes/no)");
                boolean answer = validate.inputYesNo();

                if (answer) {
                    System.out.println("Choose another user role:");
                    System.out.println(UserRoles.getAllRoles());
                    roles.add(validate.inputAnotherRole(UserRoles.getNameById(firstRoleNumber - 1)));
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
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

    public void editUser(List<User> users, int id) {
        Scanner in = new Scanner(System.in);
        UserValidator validate = new UserValidator();

        int minEditChoice = 1;
        int maxEditChoice = 6;

        boolean workEdit = true;

        while (workEdit) {

            System.out.println("What do you want to edit? (" + minEditChoice + "-" + maxEditChoice + ")");

            System.out.println("1 - Name: " + users.get(id).getName());
            System.out.println("2 - Surname: " + users.get(id).getSurname());
            System.out.println("3 - Email: " + users.get(id).getEmail());
            System.out.println("4 - Phone numbers: " + users.get(id).getPhoneNumbers());
            System.out.println("5 - Roles: " + users.get(id).getRoles());
            System.out.println("6 - Exit");

            int editChoice = validate.inputIntValue(minEditChoice, maxEditChoice);

            switch (editChoice) {
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
                    String email = validate.inputEmail();
                    users.get(id).setEmail(email);
                    break;
                case 4: // edit phone numbers
                    boolean workEditPhones = true;

                    while (workEditPhones) {

                        int minEditPhoneChoice = 1;
                        int maxEditPhoneChoice = 4;

                        System.out.println("Current phone numbers: " + users.get(id).getPhoneNumbers());
                        System.out.println("1-Add 2-Edit existing 3-Remove existing 4-Exit (" + minEditPhoneChoice +
                                "-" + maxEditPhoneChoice + ")");
                        int phoneEditChoice = validate.inputIntValue(minEditPhoneChoice, maxEditPhoneChoice);

                        switch (phoneEditChoice) {
                            case 1: // add phone number
                                if (users.get(id).getPhoneNumbers().size() < maxCountPhones) {
                                    System.out.println("Enter the new phone number (375 xx xxxxxxx): ");
                                    String phoneNumber = validate.inputPhoneNumber();
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
                                int currentPhoneEditChoice = validate.inputIntValue(minCountPhones, currentCountPhones);
                                System.out.print("Enter the new phone number (375 xx xxxxxxx): ");
                                String phoneNumber = validate.inputPhoneNumber();
                                users.get(id).getPhoneNumbers().set(currentPhoneEditChoice - 1, phoneNumber);
                                break;
                            case 3: // removing existing phone number
                                currentCountPhones = users.get(id).getPhoneNumbers().size();

                                if (currentCountPhones == 0) {
                                    System.out.println("No phones to remove");
                                    break;
                                }

                                System.out.print("Which phone number do you want to remove? (1-" + currentCountPhones + "): ");
                                currentPhoneEditChoice = validate.inputIntValue(minCountPhones, currentCountPhones);
                                users.get(id).getPhoneNumbers().remove(currentPhoneEditChoice - 1);
                                break;
                            case 4:
                                workEditPhones = false;
                                break;
                            default:
                                System.out.println("Please, try again");
                        }
                    }
                    break;
                case 5: // edit roles
                    int indexFirstRole = 0;
                    int indexSecondRole = 1;

                    boolean workEditRoles = true;

                    while (workEditRoles) {

                        int minEditRolesChoice = 1;
                        int maxEditRolesChoice = 4;

                        System.out.println("Current roles: " + users.get(id).getRoles());
                        System.out.println("1-Add 2-Edit existing 3-Remove existing 4-Exit (" + minEditRolesChoice +
                                "-" + maxEditRolesChoice + ")");
                        int rolesEditChoice = validate.inputIntValue(minEditRolesChoice, maxEditRolesChoice);

                        switch (rolesEditChoice) {
                            case 1: // add role
                                boolean wasRolesEmpty = false;

                                if (users.get(id).getRoles().isEmpty()) {
                                    wasRolesEmpty = true;

                                    System.out.println("Choose an user role:");
                                    System.out.println(UserRoles.getAllRoles());
                                    int firstRoleNumber = validate.inputIntValue(minSizeRolesList, maxSizeRolesList);

                                    users.get(id).getRoles().add(UserRoles.getNameById(firstRoleNumber - 1));
                                }

                                String firstRoleName = UserRoles.valueOf(users.get(id).getRoles().get(indexFirstRole)).name();

                                if (users.get(id).getRoles().size() < maxCountRoles && !firstRoleName.equals(UserRoles.SUPER_ADMIN.name())) {
                                    if (wasRolesEmpty) {
                                        System.out.println("Do you want to choose another user role? (yes/no)");
                                        boolean answer = validate.inputYesNo();
                                        if (!answer) {
                                            break;
                                        }
                                    }

                                    System.out.println("Choose an user role:");
                                    System.out.println(UserRoles.getAllRoles());

                                    if (!firstRoleName.equals(UserRoles.SUPER_ADMIN.name())) {
                                        users.get(id).getRoles().add(validate.inputAnotherRole(firstRoleName));
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
                                    int newRoleNumber = validate.inputIntValue(minSizeRolesList, maxSizeRolesList);
                                    users.get(id).getRoles().set(indexFirstRole, UserRoles.getNameById(newRoleNumber - 1));
                                } else { // current count of roles = 2
                                    System.out.print("Which role do you want to edit? (1-" + currentCountRoles + "): ");
                                    int currentRoleEditChoice = validate.inputIntValue(minCountRoles, currentCountRoles);

                                    System.out.println("Choose a new user role:");
                                    System.out.println(UserRoles.getAllRoles());

                                    switch (currentRoleEditChoice) {
                                        case 1: // edit the first role
                                            String secondRoleNameEdit = UserRoles.valueOf(users.get(id).getRoles().get(indexSecondRole)).name();
                                            String newFirstRole = validate.inputAnotherRole(secondRoleNameEdit);
                                            users.get(id).getRoles().set(indexFirstRole, newFirstRole);
                                            break;
                                        case 2: // edit the second role
                                            String firstRoleNameEdit = UserRoles.valueOf(users.get(id).getRoles().get(indexFirstRole)).name();
                                            String newSecondRole = validate.inputAnotherRole(firstRoleNameEdit);
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
                                int numberRoleRemove = validate.inputIntValue(minCountRoles, currentCountRoles);
                                users.get(id).getRoles().remove(numberRoleRemove - 1);
                                break;
                            case 4:
                                workEditRoles = false;
                                break;
                            default:
                                System.out.println("Please, try again");
                        }
                    }
                    break;
                case 6:
                    workEdit = false;
                    break;
                default:
                    System.out.println("Please, try again");
            }
        }
    }

    public void removeUser(List<User> users, int id) {
        users.remove(id);
        updateUsersId(users);
    }

    public void viewUserById(List<User> users, int id) {
        System.out.println(users.get(id));
    }

    public void viewAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void updateUsersId(List<User> users) {
        for (int i = 0; i < users.size(); ++i) {
            users.get(i).setId(i + 1);
        }
    }

}
