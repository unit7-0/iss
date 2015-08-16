package com.unit7.iss.model.entity;

import com.google.common.base.MoreObjects;
import org.mongodb.morphia.annotations.*;

/**
 * Created by breezzo on 09.08.15.
 */
@org.mongodb.morphia.annotations.Entity("users")
public class User extends AbstractEntity {

    @Indexed(unique = true)
    private String login;

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("login", login)
                .add("password", password)
                .toString();
    }
}
