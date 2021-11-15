package com.accounting.model;

import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private List<String> phoneNumbers;
    private List<String> roles;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object UserObject) {
        if (this == UserObject) {
            return true;
        }
        if (UserObject == null || getClass() != UserObject.getClass()) {
            return false;
        }
        User user = (User) UserObject;

        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) &&
                Objects.equals(email, user.email) && Objects.equals(phoneNumbers, user.phoneNumbers) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, phoneNumbers, roles);
    }

    public String toString() {
        return "User " + id +
                ": name = '" + name + '\'' +
                ", surname = '" + surname + '\'' +
                ", email = '" + email + '\'' +
                ", phone numbers = " + phoneNumbers +
                ", roles = " + roles;
    }

}
