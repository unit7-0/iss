package com.unit7.iss.db.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.stub.image.AlbumStubBuilder;
import com.unit7.iss.stub.image.ImageStubBuilder;
import com.unit7.iss.stub.user.UserStubBuilder;
import com.unit7.iss.util.compare.ReflectionComparator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by breezzo on 16.08.15.
 */
public class AlbumDAOTest {

    private AlbumDAO dao;
    private UserDAO userDAO;

    private AlbumStubBuilder albumBuilder;
    private ImageStubBuilder imageBuilder;
    private UserStubBuilder userBuilder;

    @Before
    public void setup() {

        // TODO use running framework
        final Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ImageDAO.class);
                bind(AlbumDAO.class);
                bind(UserDAO.class);
            }
        });

        dao = injector.getInstance(AlbumDAO.class);
        userDAO = injector.getInstance(UserDAO.class);

        userBuilder = UserStubBuilder.newInstance()
                            .setLogin("login")
                            .setName("album_user")
                            .setPassword("123456");

        imageBuilder = ImageStubBuilder.newInstance()
                            .setContent(new byte[] { 1, 2, 3 })
                            .setName("album_image1")
                            .setListInstanceCount(2);

        albumBuilder = AlbumStubBuilder.newInstance()
                            .setImageBuilder(imageBuilder)
                            .setUserBuilder(userBuilder)
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

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
