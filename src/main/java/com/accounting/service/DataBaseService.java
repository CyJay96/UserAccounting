package com.accounting.service;

import com.accounting.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class DataBaseService {

    public void writeDataBase(List<User> users, String dataBaseName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseName))) {
            objectOutputStream.writeObject(users);
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

    public void readDataBase(List<User> users, String dataBaseName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseName))) {
            for (User user : (List<User>) objectInputStream.readObject()) {
                users.add(user);
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

}
