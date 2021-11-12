package com.accounting.service;

import com.accounting.model.UserRoles;

import java.util.Scanner;

public class Validator {

    public String inputEmail() throws Exception {
        Scanner in = new Scanner(System.in);

        String email;

        while (true) {
             email = in.next();

            int pos = 0;

            int firstPartEmailLength = 0;
            while (pos < email.length() && email.charAt(pos) != '@') {
                firstPartEmailLength++;
                pos++;
            }
            pos++;

            int secondPartEmailLength = 0;
            while (pos < email.length() && email.charAt(pos) != '.') {
                secondPartEmailLength++;
                pos++;
            }
            pos++;

            int thirdPartEmailLength = 0;
            while (pos++ < email.length()) {
                thirdPartEmailLength++;
            }

            if (firstPartEmailLength < 1 || firstPartEmailLength >= email.length() ||
                    secondPartEmailLength < 1 || secondPartEmailLength >= email.length() ||
                    thirdPartEmailLength < 1 || thirdPartEmailLength >= email.length()) {
                System.out.println("PLease, try again");
            } else {
                break;
            }
        }

        return email;
    }

    public String inputPhoneNumber() throws Exception {
        Scanner in = new Scanner(System.in);

        String phoneNumber;

        while (true) {
            phoneNumber = in.nextLine();

            int pos = 0;
            boolean onlyNumbers = true;

            int firstPartPhoneLength = 0;
            while (pos < phoneNumber.length() && onlyNumbers && phoneNumber.charAt(pos) != ' ') {
                firstPartPhoneLength++;
                if (phoneNumber.charAt(pos) < '0' || phoneNumber.charAt(pos) > '9') {
                    onlyNumbers = false;
                }
                pos++;
            }
            pos++;

            int secondPartPhoneLength = 0;
            while (pos < phoneNumber.length() && onlyNumbers && phoneNumber.charAt(pos) != ' ') {
                secondPartPhoneLength++;
                if (phoneNumber.charAt(pos) < '0' || phoneNumber.charAt(pos) > '9') {
                    onlyNumbers = false;
                }
                pos++;
            }
            pos++;

            int thirdPartPhoneLength = 0;
            while (pos < phoneNumber.length() && onlyNumbers) {
                thirdPartPhoneLength++;
                if (phoneNumber.charAt(pos) < '0' || phoneNumber.charAt(pos) > '9') {
                    onlyNumbers = false;
                }
                pos++;
            }

            if (firstPartPhoneLength != 3 || secondPartPhoneLength != 2 || thirdPartPhoneLength != 7 || !onlyNumbers) {
                System.out.println("PLease, try again");
            } else {
                break;
            }
        }

        return phoneNumber;
    }

    public String inputSecondRole(String firstRoleName) throws Exception {
        int minRoleNumber = 1;
        int maxRoleNumber = UserRoles.values().length;

        int firstRoleLevel = UserRoles.valueOf(firstRoleName).getLevel();

        String secondRoleName;

        while (true) {
            int secondRoleNumber = inputIntValue(minRoleNumber, maxRoleNumber);
            secondRoleName = UserRoles.getNameById(secondRoleNumber - 1);
            int secondRoleLevel = UserRoles.valueOf(secondRoleName).getLevel();

            if (firstRoleLevel == secondRoleLevel) {
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
                if (data < lowerBound && data > upperBound) {
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
