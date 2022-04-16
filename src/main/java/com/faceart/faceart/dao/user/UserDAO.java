package com.faceart.faceart.dao.user;

import com.faceart.faceart.entities.User;

public interface UserDAO {
    void addUser();

    User getUserById(long id);

    User getUserByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

    void save(User user);
}
