package com.accounting.validation;

import com.accounting.model.UserRoles;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private final int minSizeRolesList = 1;
    private final int maxSizeRolesList = UserRoles.values().length;

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public String inputEmail() {
        Scanner in = new Scanner(System.in);

        String email;

        while (true) {
            try {
                email = in.next();

                if (!validateEmail(email)) {
                    System.out.println("PLease, try again");
                } else {
                    break;
                }
            } catch (Exception exception) {
                System.out.println("Something went wrong: " + exception.getMessage());
            }
        }

        return email;
    }

    public boolean validatePhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        String emailRegex = "([375]{3})([ ])([0-9]{2})\\2([0-9]{7})";

        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public String inputPhoneNumber() {
        Scanner in = new Scanner(System.in);

        String phoneNumber;

        while (true) {
            try {
                phoneNumber = in.nextLine();

                if (!validatePhone(phoneNumber)) {
                    System.out.println("PLease, try again");
                } else {
                    break;
                }
            } catch (Exception exception) {
                System.out.println("Something went wrong: " + exception.getMessage());
            }
        }

        return phoneNumber;
    }

    public boolean validateAnotherRole(String existingRoleName, String anotherRoleName) {
        int existingRoleLevel = UserRoles.valueOf(existingRoleName).getLevel();
        int anotherRoleLevel = UserRoles.valueOf(anotherRoleName).getLevel();
        int superAdminRoleLevel = UserRoles.SUPER_ADMIN.getLevel();

        if (existingRoleLevel != superAdminRoleLevel && anotherRoleLevel != superAdminRoleLevel &&
                anotherRoleLevel != existingRoleLevel) {
            return true;
        } else {
            return false;
        }
    }

    public String inputAnotherRole(String existingRoleName) {
        String anotherRoleName;

        while (true) {
            int secondRoleNumber = inputIntValue(minSizeRolesList, maxSizeRolesList);
            anotherRoleName = UserRoles.getNameById(secondRoleNumber - 1);

            if (validateAnotherRole(existingRoleName, anotherRoleName)) {
                break;
            } else {
                System.out.println("Choose another user role");
            }
        }

        return anotherRoleName;
    }

    public boolean inputYesNo() {
        Scanner in = new Scanner(System.in);

        String answer = null;

        try {
            answer = in.next();
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        String yes = new String("yes");
        String no = new String("no");

        while (!answer.equals(yes) && !answer.equals(no)) {
            System.out.println("Please, try again");
            try {
                answer = in.next();
            } catch (Exception exception) {
                System.out.println("Something went wrong: " + exception.getMessage());
            }
        }

        if (answer.equals(yes)) {
            return true;
        } else {
            return false;
        }
    }

    public int inputIntValue() {
        Scanner in = new Scanner(System.in);

        int data = 0;

        try {
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    data = in.nextInt();
                    break;
                } else {
                    System.out.println("Please, try again");
                    in.next();
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        return data;
    }

    public int inputIntValue(int lowerBound) {
        Scanner in = new Scanner(System.in);

        int data = 0;

        try {
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    data = in.nextInt();
                    if (data < lowerBound) {
                        System.out.println("Please, try again");
                        continue;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Please, try again");
                    in.next();
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        return data;
    }

    public int inputIntValue(int lowerBound, int upperBound) {
        Scanner in = new Scanner(System.in);

        int data = 0;

        try {
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    data = in.nextInt();
                    if (data < lowerBound || data > upperBound) {
                        System.out.println("Please, try again");
                        continue;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Please, try again");
                    in.next();
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }

        return data;
    }

}
