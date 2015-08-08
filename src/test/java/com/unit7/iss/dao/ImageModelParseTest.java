package com.unit7.iss.dao;

import com.google.common.base.Objects;
import com.unit7.iss.model.ImageModel;
import com.unit7.iss.model.behavior.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        final ImageModel model = ModelFactory.toModel(data);

        assertTrue(equals(standart, model));
    }

    @Test
    public void parseToMap() {
        final Map<String, Object> map = ModelFactory.toMap(standart);

        assertEquals(data, map);
    }

    private ImageModel imageModel() {
        final ImageModel model = new ImageModel();

        model.setName("name");
        model.setContent(new byte[]{1, 2, 3, 4, 5, 6});

        return model;
    }

    private Map<String, Object> mapModel(ImageModel model) {
        final Map<String, Object> map = new HashMap<>();

        map.put("name", model.getName());
        map.put("content", model.getContent());

        return map;
    }

    private boolean equals(ImageModel standart, ImageModel that) {
        return Objects.equal(standart.getName(), that.getName()) &&
                Arrays.equals(standart.getContent(), that.getContent());
    }
}
