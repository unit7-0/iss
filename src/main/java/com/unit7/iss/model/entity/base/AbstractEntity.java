package com.unit7.iss.model.entity.base;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by breezzo on 16.08.15.
 */
public class AbstractEntity implements Entity {

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    @Id
    private ObjectId id;
}
