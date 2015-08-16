package com.unit7.iss.model.entity.base;

import org.bson.types.ObjectId;

/**
 * Created by breezzo on 15.08.15.
 */
public interface Entity {
    ObjectId getId();

    void setId(ObjectId id);

}
