package com.unit7.iss.service.image.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import com.unit7.iss.db.dao.AlbumDAO;
import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.service.AlbumService;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by breezzo on 19.08.15.
 */
@Singleton
public class AlbumServiceImpl implements AlbumService {
    @Inject
    private AlbumDAO albumDAO;

    @Override
    public void create(Album album) {
        albumDAO.create(album);
    }

    @Override
    public Album get(ObjectId id) {
        return albumDAO.getById(id);
    }

    @Override
    public List<Album> getByUser(User user) {
        return albumDAO.getByUser(user);
    }
}
