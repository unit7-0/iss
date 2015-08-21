package com.unit7.iss.service.user.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import com.unit7.iss.db.dao.UserDAO;
import com.unit7.iss.model.entity.User;
import com.unit7.iss.service.user.UserService;

/**
 * Created by breezzo on 19.08.15.
 */
@Singleton
public class UserServiceImpl implements UserService {
    @Inject
    private UserDAO userDAO;

    @Override
    public User getByLogin(String userLogin) {
        return userDAO.getByLogin(userLogin);
    }
}
