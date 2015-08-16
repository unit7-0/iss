package com.unit7.iss.db.dao;

import com.google.inject.Singleton;
import com.mongodb.WriteResult;
import com.unit7.iss.db.DatabaseFactory;
import com.unit7.iss.db.dao.base.AbstractDAO;
import com.unit7.iss.model.entity.User;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 * Created by breezzo on 09.08.15.
 */
@Singleton
public class UserDAO extends AbstractDAO<User> {

    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public UserDAO() {
        super(User.class, DATABASE_NAME);

        datastore.ensureIndexes();
    }

    @Override
    public int update(User entity) {
        final UpdateOperations<User> updateOps =
                datastore.createUpdateOperations(User.class)
                        .set("login", entity.getLogin())
                        .set("name", entity.getName())
                        .set("password", entity.getPassword());

        return super.update(entity, updateOps);
    }

    public int delete(User user) {
        final Query<User> query = datastore.createQuery(User.class);

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

    public User getByLogin(String login) {
        return datastore.find(User.class, "login", login).get();
    }
}
