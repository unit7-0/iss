package com.unit7.iss.dao;

import com.google.inject.Singleton;
import com.unit7.iss.model.entity.ImageEntity;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * Created by breezzo on 15.08.15.
 */
@Singleton
public class ImageDAO extends AbstractDAO<ImageEntity> {
    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public ImageDAO() {
        super(ImageEntity.class, DATABASE_NAME);
    }

    @Override
    public int update(ImageEntity entity) {
        final UpdateOperations<ImageEntity> updateOps = datastore.createUpdateOperations(ImageEntity.class)
                                                                    .set("name", entity.getName())
                                                                    .set("content", entity.getContent());

        return update(entity, updateOps);
    }

    public List<ImageEntity> getImageListByName(String name) {
        return datastore.find(ImageEntity.class, "name", name).asList();
    }

    @Deprecated
    public ImageEntity getFirstImageByName(String name) {
        return datastore.find(ImageEntity.class, "name", name).get();
    }
}
