package com.unit7.iss.model.behavior;

import com.unit7.iss.model.ImageModel;
import com.unit7.iss.model.UserModel;
import org.bson.types.Binary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by breezzo on 08.08.15.
 *
 * TODO подумать над паттерном
 */
public class ModelFactory {
    public static ImageModel createImage(Map<String, Object> map) {
        final ImageModel model = new ImageModel();

        model.setName((String) map.get("name"));
        model.setContent(getByteArray(map.get("content")));

        return model;
    }

    public static Map<String, Object> toMap(ImageModel model) {
        final Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", model.getName());
        map.put("content", model.getContent());

        return map;
    }

    public static UserModel createUser(Map<String, Object> map) {
        final UserModel model = new UserModel();

        model.setLogin((String) map.get("login"));
        model.setName((String) map.get("name"));
        model.setPassword((String) map.get("password"));

        return model;
    }

    public static Map<String, Object> toMap(UserModel model) {
        final Map<String, Object> map = new HashMap<>();

        map.put("login", model.getLogin());
        map.put("name", model.getName());
        map.put("password", model.getPassword());

        return map;
    }

    // TODO подумать о корректности получения массива
    private static final byte[] getByteArray(Object data) {
        if (data instanceof Binary)
            return ((Binary) data).getData();

        if (data instanceof byte[])
            return (byte[]) data;

        return null;
    }
}
