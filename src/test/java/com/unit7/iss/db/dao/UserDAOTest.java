package com.unit7.iss.db.dao;

import com.mongodb.DuplicateKeyException;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.model.entity.User;
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
        final User user = user();

        dao.delete(user);
        dao.create(user);

        final User fromDb = dao.getById(user.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(user, fromDb));
    }

    @Test
    public void getByLogin() {
        final User user = user();
        final User forDelete = new User();
        forDelete.setLogin(login);

        dao.delete(forDelete);
        dao.create(user);

        final User byLogin = dao.getByLogin(login);

        Assert.assertNotNull(byLogin);
        Assert.assertEquals(login, byLogin.getLogin());
    }

    @Test
    public void delete() {
        final User user = user();

        dao.delete(user);
        dao.create(user);

        Assert.assertEquals(1, dao.delete(user));

        final User fromDb = dao.getById(user.getId());

        Assert.assertNull(fromDb);
    }

    @Test
    public void update() {
        final User user = user();
        final User forDelete = new User();
        forDelete.setLogin("delete1234");

        dao.delete(user);
        dao.delete(forDelete);
        dao.create(user);

        user.setLogin(forDelete.getLogin());
        user.setName("poster123");
        user.setPassword("456666");

        final int updateCount = dao.update(user);
        final User updatedUser = dao.getById(user.getId());

        Assert.assertEquals(1, updateCount);
        Assert.assertTrue(equals(user, updatedUser));
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertNonUnique() {
        final User user = user();

        dao.create(user);
        dao.create(user);
    }

    private User user() {
        final User user = new User();

        user.setLogin(login);
        user.setName("name");
        user.setPassword("123456");

        return user;
    }

    private boolean equals(User a, User b) {
        return  Objects.equals(a.getId(), b.getId()) &&
                Objects.equals(a.getName(), b.getName()) &&
                Objects.equals(a.getPassword(), b.getPassword());
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
