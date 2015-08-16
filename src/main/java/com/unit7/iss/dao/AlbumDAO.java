package com.unit7.iss.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.unit7.iss.model.entity.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by breezzo on 16.08.15.
 */
@Singleton
public class AlbumDAO extends AbstractDAO<Album> {

    private static final Logger logger = LoggerFactory.getLogger(AlbumDAO.class);

    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    @Inject
    private ImageDAO imageDAO;

    public AlbumDAO() {
        super(Album.class, DATABASE_NAME);
    }

    @Override
    public void create(Album entity) {
        logger.debug("creating entity: {}", entity);

        entity.getSubAlbums()
                .forEach((album) -> AlbumDAO.this.create(album));

        entity.getImages()
                .forEach((image) -> imageDAO.create(image));

        super.create(entity);
    }

    @Override
    public int update(Album entity) {
        throw new UnsupportedOperationException();
    }
}
