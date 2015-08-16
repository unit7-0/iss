package com.unit7.iss.db.dao.base;

import com.unit7.iss.model.entity.base.Entity;
import org.bson.types.ObjectId;

/**
 * Created by breezzo on 15.08.15.
 */
public interface DAO<T extends Entity> {

    void create(T entity);

    int update(T entity);

    boolean deleteById(ObjectId id);

    T getById(ObjectId id);
}
