package com.unit7.iss.dao;

import com.unit7.iss.model.entity.ImageEntity;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by breezzo on 15.08.15.
 */
public class ImageDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(ImageDAOTest.class);

    private ImageDAO dao;

    @Before
    public void setup() {
        dao = new ImageDAO();
    }

    @Test
    public void create() {
        logger.debug("create");

        final ImageEntity model = image();

        dao.create(model);

        logger.debug("created model: {}", model);

        Assert.assertNotNull(model.getId());
    }

    @Test
    public void getById() {
        logger.debug("getById");

        final ImageEntity model = image();

        dao.create(model);
        final ImageEntity persist = dao.getById(model.getId());

        Assert.assertEquals(model.getId(), persist.getId());
        Assert.assertEquals(model.getName(), persist.getName());
        Assert.assertTrue(Arrays.equals(model.getContent(), persist.getContent()));
    }

    @Test
    public void update() {
        final ImageEntity model = image();

        dao.create(model);

        final ImageEntity newModel = image();
        newModel.setName("321");
        newModel.setContent(new byte[]{1});
        newModel.setId(model.getId());

        final int updatedCount = dao.update(newModel);

        Assert.assertEquals(1, updatedCount);

        final ImageEntity persist = dao.getById(model.getId());

        Assert.assertEquals(newModel.getName(), persist.getName());
        Assert.assertTrue(Arrays.equals(newModel.getContent(), persist.getContent()));
    }

    @Test
    public void deletebyId() {
        logger.debug("deleteById");

        final ImageEntity model = image();

        dao.create(model);

        Assert.assertTrue(dao.deleteById(model.getId()));
        Assert.assertNull(dao.getById(model.getId()));
    }

    @Test
    public void deleteNotExisted() {
        logger.debug("deleteNotExisted");

        final ImageEntity model = image();

        Assert.assertFalse(dao.deleteById(model.getId()));
    }

    private ImageEntity image() {
        final ImageEntity model = new ImageEntity();

        model.setName("123");
        model.setContent(new byte[]{1, 2, 3});

        return model;
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
