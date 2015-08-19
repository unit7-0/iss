package com.unit7.iss.db.dao;

import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.model.entity.Image;
import com.unit7.iss.stub.image.ImageStubBuilder;
import com.unit7.iss.util.compare.ReflectionComparator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by breezzo on 15.08.15.
 */
public class ImageDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(ImageDAOTest.class);

    private ImageDAO dao;

    private ImageStubBuilder builder;

    @Before
    public void setup() {
        dao = new ImageDAO();

        builder = ImageStubBuilder.newInstance()
                            .setContent(new byte[] { 1, 2, 3 })
                            .setName("image1");
    }

    @Test
    public void create() {
        logger.debug("create");

        final Image model = image();

        dao.create(model);

        logger.debug("created model: {}", model);

        Assert.assertNotNull(model.getId());
    }

    @Test
    public void getById() {
        logger.debug("getById");

        final Image model = image();

        dao.create(model);
        final Image persist = dao.getById(model.getId());

        Assert.assertTrue(equals(model.getContent(), persist.getContent()));
    }

    @Test
    public void update() {
        final Image model = image();

        dao.create(model);

        final Image newModel = image();
        newModel.setName("321");
        newModel.setContent(new byte[]{1});
        newModel.setId(model.getId());

        final int updatedCount = dao.update(newModel);

        Assert.assertEquals(1, updatedCount);

        final Image persist = dao.getById(model.getId());

        Assert.assertTrue(equals(newModel.getContent(), persist.getContent()));
    }

    @Test
    public void deletebyId() {
        logger.debug("deleteById");

        final Image model = image();

        dao.create(model);

        Assert.assertTrue(dao.deleteById(model.getId()));
        Assert.assertNull(dao.getById(model.getId()));
    }

    @Test
    public void deleteNotExisted() {
        logger.debug("deleteNotExisted");

        final Image model = image();

        Assert.assertFalse(dao.deleteById(model.getId()));
    }

    private Image image() {
        return builder.build();
    }

    private boolean equals(Object a, Object b) {
        return new ReflectionComparator().compare(a, b) == 0;
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
