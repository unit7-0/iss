package com.unit7.iss.db.dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.Image;
import com.unit7.iss.util.compare.ReflectionComparator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Created by breezzo on 16.08.15.
 */
public class AlbumDAOTest {

    private AlbumDAO dao;

    @Before
    public void setup() {

        // TODO use running framework
        final Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(ImageDAO.class);
                bind(AlbumDAO.class);
            }
        });

        dao = injector.getInstance(AlbumDAO.class);
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
        final Album album = new Album();

        album.setName("album1");
        album.setSubAlbums(ImmutableList.of(albumWithoutChilds()));
        album.setImages(ImmutableList.of(image(), image()));

        return album;
    }

    private Album albumWithoutChilds() {
        final Album album = new Album();

        album.setName("album2");

        return album;
    }

    private Image image() {
        final Image image = new Image();

        image.setName("image1");
        image.setContent(new byte[]{1, 2, 3});

        return image;
    }

    private boolean equals(Album a, Album b) {
        return new ReflectionComparator().compare(a, b) == 0;
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
