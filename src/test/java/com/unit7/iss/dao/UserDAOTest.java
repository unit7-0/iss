package com.unit7.iss.dao;

import com.mongodb.DuplicateKeyException;
import com.unit7.iss.model.entity.UserEntity;
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
        final UserEntity user = user();

        dao.delete(user);
        dao.create(user);

        final UserEntity fromDb = dao.getById(user.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(user, fromDb));
    }

    @Test
    public void getByLogin() {
        final UserEntity user = user();
        final UserEntity forDelete = new UserEntity();
        forDelete.setLogin(login);

        dao.delete(forDelete);
        dao.create(user);

        final UserEntity byLogin = dao.getByLogin(login);

        Assert.assertNotNull(byLogin);
        Assert.assertEquals(login, byLogin.getLogin());
    }

    @Test
    public void delete() {
        final UserEntity user = user();

        dao.delete(user);
        dao.create(user);

        Assert.assertEquals(1, dao.delete(user));

        final UserEntity fromDb = dao.getById(user.getId());

        Assert.assertNull(fromDb);
    }

    @Test
    public void update() {
        final UserEntity user = user();
        final UserEntity forDelete = new UserEntity();
        forDelete.setLogin("delete1234");

        dao.delete(user);
        dao.delete(forDelete);
        dao.create(user);

        user.setLogin(forDelete.getLogin());
        user.setName("poster123");
        user.setPassword("456666");

        final int updateCount = dao.update(user);
        final UserEntity updatedUser = dao.getById(user.getId());

        Assert.assertEquals(1, updateCount);
        Assert.assertTrue(equals(user, updatedUser));
    }

    @Test(expected = DuplicateKeyException.class)
    public void insertNonUnique() {
        final UserEntity user = user();

        dao.create(user);
        dao.create(user);
    }

    private UserEntity user() {
        final UserEntity user = new UserEntity();

        user.setLogin(login);
        user.setName("name");
        user.setPassword("123456");

        return user;
    }

    private boolean equals(UserEntity a, UserEntity b) {
        return  Objects.equals(a.getId(), b.getId()) &&
                Objects.equals(a.getName(), b.getName()) &&
                Objects.equals(a.getPassword(), b.getPassword());
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
