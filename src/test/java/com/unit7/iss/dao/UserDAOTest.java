package com.unit7.iss.dao;

import com.mongodb.DuplicateKeyException;
import com.unit7.iss.model.entity.UserModel;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

/**
 * Created by breezzo on 16.08.15.
 */
public class UserDAOTest {
    private UserDAO dao;

    private final String login = "testUser";

    @Before
    public void setup() {
        dao = new UserDAO();
    }

    @Test
    public void create() {
        final UserModel user = user();

        dao.delete(user);
        dao.create(user);

        final UserModel fromDb = dao.getById(user.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(user, fromDb));
    }

    @Test
    public void getByLogin() {
        final UserModel user = user();
        final UserModel forDelete = new UserModel();
        forDelete.setLogin(login);

        dao.delete(forDelete);
        dao.create(user);

        final UserModel byLogin = dao.getByLogin(login);

        Assert.assertNotNull(byLogin);
        Assert.assertEquals(login, byLogin.getLogin());
    }

    @Test
    public void delete() {
        final UserModel user = user();

        dao.delete(user);
        dao.create(user);

        Assert.assertEquals(1, dao.delete(user));

        final UserModel fromDb = dao.getById(user.getId());

        Assert.assertNull(fromDb);
    }

    @Test
    public void update() {
        final UserModel user = user();
        final UserModel forDelete = new UserModel();
        forDelete.setLogin("delete1234");

        dao.delete(user);
        dao.delete(forDelete);
        dao.create(user);

        user.setLogin(forDelete.getLogin());
        user.setName("poster123");
        user.setPassword("456666");

        final int updateCount = dao.update(user);
        final UserModel updatedUser = dao.getById(user.getId());

        Assert.assertEquals(1, updateCount);
        Assert.assertTrue(equals(user, updatedUser));
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertNonUnique() {
        final UserModel user = user();

        dao.create(user);
        dao.create(user);
    }

    private UserModel user() {
        final UserModel user = new UserModel();

        user.setLogin(login);
        user.setName("name");
        user.setPassword("123456");

        return user;
    }

    private boolean equals(UserModel a, UserModel b) {
        return  Objects.equals(a.getId(), b.getId()) &&
                Objects.equals(a.getName(), b.getName()) &&
                Objects.equals(a.getPassword(), b.getPassword());
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
