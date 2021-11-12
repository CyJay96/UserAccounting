package com.accounting.service;

import com.accounting.model.UserRoles;

import java.util.Scanner;

public class Validator {

    public String inputEmail() throws Exception {
        Scanner in = new Scanner(System.in);

        String email = in.next();

        return email;
    }

    public String inputPhoneNumber() throws Exception {
        Scanner in = new Scanner(System.in);

        String phoneNumber = in.next();

        return phoneNumber;
    }

    public String inputRole() throws Exception {
        int minRoleNumber = 1;
        int maxRoleNumber = UserRoles.values().length;

        return "2";
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

    public int InputIntValue(int lowerBound, int upperBound) throws Exception {
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
