package com.unit7.iss.dao;

import com.unit7.iss.model.entity.ImageModel;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * Created by breezzo on 15.08.15.
 */
public class ImageDAO extends AbstractDAO<ImageModel> {
    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public ImageDAO() {
        super(ImageModel.class, DATABASE_NAME);
    }

    @Override
    public int update(ImageModel entity) {
        final UpdateOperations<ImageModel> updateOps = datastore.createUpdateOperations(ImageModel.class)
                                                                    .set("name", entity.getName())
                                                                    .set("content", entity.getContent());

        return update(entity, updateOps);
    }

    public ImageModel getImage(String name) {
        return datastore.find(ImageModel.class, "name", name).get();
    }
}
