package com.unit7.iss.dao;

import com.unit7.iss.model.entity.UserModel;

/**
 * Created by breezzo on 09.08.15.
 */
public class UserDAO extends AbstractDAO<UserModel> {

    private static final String DATABASE_NAME = DatabaseFactory.ISS_DATABASE_NAME;

    public UserDAO() {
        super(UserModel.class, DATABASE_NAME);
    }

    @Override
    public int update(UserModel entity) {
        return 0; // TODO
    }
}
