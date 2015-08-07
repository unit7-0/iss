package com.unit7.iss.dao;

import com.unit7.iss.model.ImageModel;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by breezzo on 07.08.15.
 */
public class ImageModelParseTest {

    private ImageModel standart;
    private Map<String, Object> data;

    @Before
    public void setup() {
        standart = imageModel();
        data = mapModel(standart);
    }

    @Test
    public void parseFromMap() {
        final ImageModel model = new ImageModel(data);

        assertEquals(standart, model);
    }

    @Test
    public void parseToMap() {
        final Map<String, Object> map = standart.toMap();

        assertEquals(data, map);
    }

    private ImageModel imageModel() {
        final ImageModel model = new ImageModel();

        model.setName("name");
        model.setContent(new byte[] { 1, 2, 3, 4, 5, 6 });

        return model;
    }

    private Map<String, Object> mapModel(ImageModel model) {
        final Map<String, Object> map = new HashMap<>();

        map.put("name", model.getName());
        map.put("content", model.getContent());

        return map;
    }
}
