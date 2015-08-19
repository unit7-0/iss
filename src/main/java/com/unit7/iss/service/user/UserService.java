package com.unit7.iss.service.user;

import com.unit7.iss.model.entity.User;

/**
 * Created by breezzo on 19.08.15.
 */
public interface UserService {
    User getByLogin(String userLogin);
}
