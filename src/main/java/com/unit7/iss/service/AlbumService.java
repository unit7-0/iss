package com.unit7.iss.service;

import com.unit7.iss.model.entity.Album;
import com.unit7.iss.model.entity.User;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by breezzo on 19.08.15.
 */
public interface AlbumService {
    void create(Album album);

    Album get(ObjectId id);

    List<Album> getByUser(User user);
}
