package com.unit7.iss.dao;

import com.mongodb.WriteResult;
import com.unit7.iss.model.entity.UserModel;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * Created by breezzo on 09.08.15.
 */
public class UserDAO extends AbstractDAO<UserModel> {

    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public UserDAO() {
        super(UserModel.class, DATABASE_NAME);

        datastore.ensureIndexes();
    }

    @Override
    public int update(UserModel entity) {
        final UpdateOperations<UserModel> updateOps =
                datastore.createUpdateOperations(UserModel.class)
                        .set("login", entity.getLogin())
                        .set("name", entity.getName())
                        .set("password", entity.getPassword());

        return super.update(entity, updateOps);
    }

    public int delete(UserModel user) {
        final Query<UserModel> query = datastore.createQuery(UserModel.class);

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
