package com.accounting.service;

import com.accounting.builder.UserBuilder;
import com.accounting.model.User;
import com.accounting.validation.UserValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService {

    private final int minCountPhones = 1;
    private final int maxCountPhones = 3;

    private final int minCountRoles = 1;
    private final int maxCountRoles = 2;

    public void writeDataBase(List<User> users, File file) {
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            for (User user : users) {
                bufferedWriter.write(String.valueOf(user.getId()));
                bufferedWriter.newLine();

                bufferedWriter.write(user.getName());
                bufferedWriter.newLine();
                bufferedWriter.write(user.getSurname());
                bufferedWriter.newLine();
                bufferedWriter.write(user.getEmail());
                bufferedWriter.newLine();

                bufferedWriter.write(String.valueOf(user.getPhoneNumbers().size()));
                bufferedWriter.newLine();
                for (String phoneNumber : user.getPhoneNumbers()) {
                    bufferedWriter.write(phoneNumber);
                    bufferedWriter.newLine();
                }

                bufferedWriter.write(String.valueOf(user.getRoles().size()));
                bufferedWriter.newLine();
                for (String role : user.getRoles()) {
                    bufferedWriter.write(role);
                    bufferedWriter.newLine();
                }

                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Something went wrong: " + exception);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException exception) {
                System.out.println("Something went wrong: " + exception);
            }
        }
    }

    public void readDataBase(List<User> users,  File file) {
        UserValidator validate = new UserValidator();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                int id = Integer.parseInt(line);

                line = bufferedReader.readLine();
                String name = line;
                line = bufferedReader.readLine();
                String surname = line;
                line = bufferedReader.readLine();
                String email = null;
                if (validate.validateEmail(line)) {
                    email = line;
                }

                line = bufferedReader.readLine();
                int countPhones = Integer.parseInt(line);
                List<String> phoneNumbers = new ArrayList<>();
                for (int i = 0; i < countPhones && i < maxCountPhones; ++i) {
                    line = bufferedReader.readLine();
                    if (validate.validatePhone(line)) {
                        phoneNumbers.add(line);
                    }
                }

                line = bufferedReader.readLine();
                int countRoles = Integer.parseInt(line);
                List<String> roles = new ArrayList<>();
                for (int i = 0; i < countRoles && i < maxCountRoles; ++i) {
                    line = bufferedReader.readLine();
                    if (i == 1) { // if the second role is reading
                        if (validate.validateAnotherRole(roles.get(0), line)) {
                            roles.add(line);
                        }
                    } else {
                        roles.add(line);
                    }
                }

                User user = new UserBuilder()
                        .withId(id)
                        .withName(name)
                        .withSurname(surname)
                        .withEmail(email)
                        .withPhoneNumbers(phoneNumbers)
                        .withRoles(roles)
                        .build();

                users.add(user);
            }
        } catch (IOException exception) {
            System.out.println("Something went wrong: " + exception);
        }
    }

    public void clearDataBase(File file) {
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            bufferedWriter = new BufferedWriter(new FileWriter(file, false));

            bufferedWriter.write("");
        } catch (IOException exception) {
            System.out.println("Something went wrong: " + exception);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException exception) {
                System.out.println("Something went wrong: " + exception);
            }
        }
    }

}
