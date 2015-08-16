package com.unit7.iss.dao;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.unit7.iss.model.entity.AlbumEntity;
import com.unit7.iss.model.entity.ImageEntity;
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
        final AlbumEntity album = albumFull();

        dao.create(album);

        final AlbumEntity fromDb = dao.getById(album.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(album, fromDb));
    }

    @Test
    public void createWithoutChilds() {
        final AlbumEntity album = albumWithoutChilds();

        dao.create(album);

        final AlbumEntity fromDb = dao.getById(album.getId());

        Assert.assertNotNull(fromDb);
        Assert.assertTrue(equals(album, fromDb));
    }

    private AlbumEntity albumFull() {
        final AlbumEntity album = new AlbumEntity();

        album.setName("album1");
        album.setSubAlbums(ImmutableList.of(albumWithoutChilds()));
        album.setImages(ImmutableList.of(image(), image()));

        return album;
    }

    private AlbumEntity albumWithoutChilds() {
        final AlbumEntity album = new AlbumEntity();

        album.setName("album2");

        return album;
    }

    private ImageEntity image() {
        final ImageEntity image = new ImageEntity();

        image.setName("image1");
        image.setContent(new byte[] { 1, 2, 3 });

        return image;
    }

    private boolean equals(AlbumEntity a, AlbumEntity b) {
        // TODO develop tool for compare entities

        final BiFunction<ImageEntity, ImageEntity, Boolean> compareImages = (f, s) ->
                Objects.equals(f.getName(), s.getName()) &&
                Objects.equals(f.getId(), s.getId()) &&
                Arrays.equals(f.getContent(), s.getContent());

        final BiFunction<List<ImageEntity>, List<ImageEntity>, Boolean> compareImageLists = (f, s) -> {
            if (f.size() != s.size())
                return false;

            for (int i = 0; i < f.size(); ++i) {
                if (!compareImages.apply(f.get(i), s.get(i)))
                    return false;
            }

            return true;
        };

        final BiFunction<AlbumEntity, AlbumEntity, Boolean> compareAlbums = (f, s) -> {
            if (!(compareImageLists.apply(f.getImages(), s.getImages())))
                return false;

            if (f.getSubAlbums().size() != s.getSubAlbums().size())
                return false;

            for (int i = 0; i < f.getSubAlbums().size(); ++i) {
                if (!(equals(f.getSubAlbums().get(i), s.getSubAlbums().get(i))))
                    return false;
            }

            return Objects.equals(f.getName(), s.getName()) && Objects.equals(f.getId(), s.getId());
        };

        return compareAlbums.apply(a, b);
    }

    @After
    public void tearDown() {
        DatabaseFactory.instance().destroy();
    }
}
