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

    public String inputEmail() throws Exception {
        Scanner in = new Scanner(System.in);

        String email;

        while (true) {
             email = in.next();

             if (!validateEmail(email)) {
                 System.out.println("PLease, try again");
             } else {
                 break;
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

    public String inputPhoneNumber() throws Exception {
        Scanner in = new Scanner(System.in);

        String phoneNumber;

        while (true) {
            phoneNumber = in.nextLine();

            if (!validatePhone(phoneNumber)) {
                System.out.println("PLease, try again");
            } else {
                break;
            }
        }

        return phoneNumber;
    }

    public String inputAnotherRole(String existingRoleName) throws Exception {
        int firstRoleLevel = UserRoles.valueOf(existingRoleName).getLevel();
        int superAdminRoleLevel = UserRoles.SUPER_ADMIN.getLevel();

        String secondRoleName;

        while (true) {
            int secondRoleNumber = inputIntValue(minSizeRolesList, maxSizeRolesList);
            secondRoleName = UserRoles.getNameById(secondRoleNumber - 1);
            int secondRoleLevel = UserRoles.valueOf(secondRoleName).getLevel();

            if (firstRoleLevel == superAdminRoleLevel || secondRoleLevel == superAdminRoleLevel || secondRoleLevel == firstRoleLevel) {
                System.out.println("Choose another user role");
            } else {
                break;
            }
        }

        return secondRoleName;
    }

    public boolean inputYesNo() throws Exception {
        Scanner in = new Scanner(System.in);

        String answer = in.next();

        String yes = new String("yes");
        String no = new String("no");

        while (!answer.equals(yes) && !answer.equals(no)) {
            System.out.println("Please, try again");
            answer = in.next();
        }

        if (answer.equals(yes)) {
            return true;
        } else {
            return false;
        }
    }

    public int inputIntValue() throws Exception {
        Scanner in = new Scanner(System.in);

        int data = 0;

        while (in.hasNext()) {
            if (in.hasNextInt()) {
                data = in.nextInt();
                break;
            } else {
                System.out.println("Please, try again");
                in.next();
            }
        }

        return data;
    }

    public int inputIntValue(int lowerBound) throws Exception {
        Scanner in = new Scanner(System.in);

        int data = 0;

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

        return data;
    }

    public int inputIntValue(int lowerBound, int upperBound) throws Exception {
        Scanner in = new Scanner(System.in);

        int data = 0;

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

        return data;
    }

}
