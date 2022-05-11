package com.faceart.faceart.dao.user;

import com.faceart.faceart.entities.User;

import java.util.List;

public interface UserDAO {
    User getUserById(long id);

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

    void save(User user);

    List getAll();

    void remove(User user);
}
