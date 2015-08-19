package com.unit7.iss.stub.user;

import com.unit7.iss.base.creation.Builder;
import com.unit7.iss.model.entity.User;

/**
 * Created by breezzo on 19.08.15.
 */
public class UserStubBuilder implements Builder<User> {

    public static UserStubBuilder newInstance() {
        return new UserStubBuilder();
    }

    @Override
    public User build() {
        if (stub != null)
            return stub;

        final User user = new User();

        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);

        return user;
    }

    public UserStubBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserStubBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserStubBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * if stub is not null then build always return this instance
     */
    public UserStubBuilder setStub(User stub) {
        this.stub = stub;
        return this;
    }

    private String login;
    private String name;
    private String password;

    private User stub;
}
