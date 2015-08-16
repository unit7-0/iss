package com.unit7.iss.dao;

import com.mongodb.WriteResult;
import com.unit7.iss.model.entity.Entity;
import com.unit7.iss.model.entity.UserEntity;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by breezzo on 15.08.15.
 */
public abstract class AbstractDAO<T extends Entity> implements DAO<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    protected Class<T> entityClass;
    protected String datastoreName;
    protected Datastore datastore;

    public AbstractDAO(Class<T> entityClass, String datastoreName) {

        logger.debug("create DAO [ entityClass: {}, datastoreName: {} ]", entityClass, datastoreName);

        this.entityClass = entityClass;
        this.datastoreName = datastoreName;
        this.datastore = DatabaseFactory.instance().createDatastore(datastoreName);
    }

    @Override
    public void create(T entity) {
        logger.debug("creating entity: {}", entity);

        datastore.save(entity);
    }

    protected int update(T entity, UpdateOperations<T> updateOps) {
        logger.debug("updating entity: {}", entity);

        final UpdateResults result = datastore.update(entity, updateOps);

        return result.getUpdatedCount();
    }

    @Override
    public boolean deleteById(ObjectId id) {
        logger.debug("deleting entity with id: {}", id);

        final WriteResult result = datastore.delete(entityClass, id);

        return result.wasAcknowledged() && result.getN() != 0;
    }

    @Override
    public T getById(ObjectId id) {
        logger.debug("getting entity with id: {}", id);

        return datastore.get(entityClass, id);
    }

    public UserEntity getByLogin(String login) {
        return datastore.find(UserEntity.class, "login", login).get();
    }
}
