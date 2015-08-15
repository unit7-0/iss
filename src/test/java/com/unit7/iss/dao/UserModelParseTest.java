package com.unit7.iss.dao;

import com.unit7.iss.model.UserModel;
import com.unit7.iss.model.behavior.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by breezzo on 09.08.15.
 */
public class UserModelParseTest {

    private Map<String, Object> map;
    private UserModel standart;

    @Before
    public void setup() {
        map = new HashMap<>();
        standart = new UserModel();

        standart.setLogin("login");
        standart.setName("name");
        standart.setPassword("1234");

        map.put("name", standart.getName());
        map.put("login", standart.getLogin());
        map.put("password", standart.getPassword());
    }

    @Test
    public void toMap() {
        final Map<String, Object> parsed = ModelFactory.toMap(standart);

        assertEquals(map, parsed);
    }

    @Test
    public void fromMap() {
        final UserModel model = ModelFactory.createUser(map);

        assertTrue(equals(standart, model));
    }

    private boolean equals(UserModel standart, UserModel that) {
        return Objects.equals(standart.getLogin(), that.getLogin()) &&
                Objects.equals(standart.getName(), that.getName()) &&
                Objects.equals(standart.getPassword(), that.getPassword());
    }
}
