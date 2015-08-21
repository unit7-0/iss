package com.unit7.iss.db.dao;

import javax.inject.Singleton;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.db.dao.base.AbstractDAO;
import com.unit7.iss.model.entity.Image;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

/**
 * Created by breezzo on 15.08.15.
 */
@Singleton
public class ImageDAO extends AbstractDAO<Image> {
    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public ImageDAO() {
        super(Image.class, DATABASE_NAME);
    }

    @Override
    public int update(Image entity) {
        final UpdateOperations<Image> updateOps = datastore.createUpdateOperations(Image.class)
                                                                    .set("name", entity.getName())
                                                                    .set("content", entity.getContent());

        return update(entity, updateOps);
    }

    public List<Image> getImageListByName(String name) {
        return datastore.find(Image.class, "name", name).asList();
    }

    @Deprecated
    public Image getFirstImageByName(String name) {
        return datastore.find(Image.class, "name", name).get();
    }
}
