package com.unit7.iss.db.dao;

import com.unit7.iss.db.DBConstants;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.db.dao.base.AbstractDAO;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by breezzo on 16.08.15.
 */
@Singleton
public class AlbumDAO extends AbstractDAO<Album> {

    private static final Logger logger = LoggerFactory.getLogger(AlbumDAO.class);

    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    @Inject
    private ImageDAO imageDAO;

    @Inject
    private UserDAO userDAO;

    public AlbumDAO() {
        super(Album.class, DATABASE_NAME);
    }

    @Override
    public void create(Album entity) {
        logger.debug("creating entity: {}", entity);

        entity.getSubAlbums()
                .forEach((album) -> AlbumDAO.this.create(album));

        entity.getImages()
                .forEach((image) -> imageDAO.create(image.getOriginal()));

        userDAO.create(entity.getUser());

        super.create(entity);
    }

    @Override
    public int update(Album entity) {
        throw new UnsupportedOperationException();
    }

    public List<Album> getByUser(User user) {
        return datastore.find(Album.class, "user", user)
                        .limit(DBConstants.ENTITY_LIST_LIMIT_SIZE)
                        .asList();
    }
}
