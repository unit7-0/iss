package com.unit7.iss.dao;

import com.unit7.iss.model.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by breezzo on 09.08.15.
 */
public class UserDAOTest {

    private UserDAO userDAO;

    @Before
    public void setup() {
        userDAO = new UserDAO();
    }

    @Test
    public void createUser() {
        final UserModel model = new UserModel();

        model.setLogin("login");
        model.setName("name");
        model.setPassword("password");

        final String id = userDAO.createUser(model);

        assertNotNull(id);
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
