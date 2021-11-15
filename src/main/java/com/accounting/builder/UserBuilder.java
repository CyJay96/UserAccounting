package com.accounting.builder;

import com.accounting.model.User;

import java.util.List;

public class UserBuilder implements UserBuilderImpl {

    private final User user;

    public UserBuilder() {
        user = new User();
    }

    @Override
    public UserBuilder withId(int id) {
        user.setId(id);
        return this;
    }

    @Override
    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    @Override
    public UserBuilder withSurname(String surname) {
        user.setSurname(surname);
        return this;
    }

    @Override
    public UserBuilder withEmail(String email) {
        user.setEmail(email);
        return this;
    }

    @Override
    public UserBuilder withPhoneNumbers(List<String> phoneNumbers) {
        user.setPhoneNumbers(phoneNumbers);
        return this;
    }

    @Override
    public UserBuilder withRoles(List<String> roles) {
        user.setRoles(roles);
        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
