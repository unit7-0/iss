package com.unit7.iss.dao;

import com.mongodb.WriteResult;
import com.unit7.iss.model.entity.UserEntity;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * Created by breezzo on 09.08.15.
 */
public class UserDAO extends AbstractDAO<UserEntity> {

    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public UserDAO() {
        super(UserEntity.class, DATABASE_NAME);

        datastore.ensureIndexes();
    }

    @Override
    public int update(UserEntity entity) {
        final UpdateOperations<UserEntity> updateOps =
                datastore.createUpdateOperations(UserEntity.class)
                        .set("login", entity.getLogin())
                        .set("name", entity.getName())
                        .set("password", entity.getPassword());

        return super.update(entity, updateOps);
    }

    public int delete(UserEntity user) {
        final Query<UserEntity> query = datastore.createQuery(UserEntity.class);

        if (user.getLogin() != null)
            query.filter("login =", user.getLogin());
        if (user.getName() != null)
            query.filter("name =", user.getName());
        if (user.getPassword() != null)
            query.filter("password =", user.getPassword());
        if (user.getId() != null)
            query.filter("id =", user.getId());

        final WriteResult result = datastore.delete(query);

        return result.wasAcknowledged() ? result.getN() : 0;
    }
}
