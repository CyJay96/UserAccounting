package com.accounting.builder;

import com.accounting.model.User;

import java.util.List;

public interface UserBuilderImpl {

    public UserBuilder withId(int id);

    public UserBuilder withName(String name);

    public UserBuilder withSurname(String surname);

    public UserBuilder withEmail(String email);

    public UserBuilder withPhoneNumbers(List<String> phoneNumbers);

    public UserBuilder withRoles(List<String> roles);

    public User build();

}
