package com.unit7.iss.db.dao;

import com.carlosbecker.guice.GuiceModules;
import com.carlosbecker.guice.GuiceTestRunner;
import com.google.common.io.ByteStreams;
import com.unit7.iss.app.conf.GuiceMainModule;
import javax.inject.Inject;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.service.ImageService;
import com.unit7.iss.stub.image.AlbumStubBuilder;
import com.unit7.iss.stub.image.ImageStubBuilder;
import com.unit7.iss.stub.user.UserStubBuilder;
import com.unit7.iss.util.compare.ReflectionComparator;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by breezzo on 16.08.15.
 */
@RunWith(GuiceTestRunner.class)
@GuiceModules(GuiceMainModule.class)
public class AlbumDAOTest {

    @Inject
    private AlbumDAO dao;

    @Inject
    private UserDAO userDAO;

    @Inject
    private ImageService imageService;

    private AlbumStubBuilder albumBuilder;
    private ImageStubBuilder imageBuilder;
    private UserStubBuilder userBuilder;

    private byte[] imageContent;

    @Before
    public void setup() throws IOException {
        try (final FileInputStream fin = new FileInputStream("/home/breezzo/img2.jpg")) {
            imageContent = ByteStreams.toByteArray(fin);
        }

        userBuilder = UserStubBuilder.newInstance()
                            .setLogin("login")
                            .setName("album_user")
                            .setPassword("123456");

        imageBuilder = ImageStubBuilder.newInstance()
                            .setContent(imageContent)
                            .setName("album_image1")
                            .setListInstanceCount(2);

        albumBuilder = AlbumStubBuilder.newInstance()
                            .setImageBuilder(imageBuilder)
                            .setUserBuilder(userBuilder)
                            .setImageService(imageService)
                            .setName("album1")
                            .setRecurseDepth(1)
                            .setListInstanceCount(2);
    }

    @Test
    public void createWithChilds() {
        final Album album = albumFull();

        dao.create(album);

        final Album fromDb = dao.getById(album.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(album, fromDb));
    }

    @Test
    public void createWithoutChilds() {
        final Album album = albumWithoutChilds();

        dao.create(album);

        final Album fromDb = dao.getById(album.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(album, fromDb));
    }

    private Album albumFull() {
        albumBuilder.setListInstanceCount(2);
        albumBuilder.setRecurseDepth(1);
        imageBuilder.setListInstanceCount(2);
        userBuilder.setStub(user());

        return albumBuilder.build();
    }

    private Album albumWithoutChilds() {
        albumBuilder.setListInstanceCount(0);
        imageBuilder.setListInstanceCount(0);
        userBuilder.setStub(user());

        return albumBuilder.build();
    }

    private User user() {
        User user = userDAO.getByLogin("login");

        if (user == null) {
            user = userBuilder.build();
        }

        return user;
    }

    private boolean equals(Album a, Album b) {
        return new ReflectionComparator().compare(a, b) == 0;
    }

    @AfterClass
    public static void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
