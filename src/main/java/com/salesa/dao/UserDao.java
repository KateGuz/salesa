package com.salesa.dao;

import com.salesa.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User get(int userId);
    int save(User user);
    User get(String email);
    void updateUsersDislike(User user);
    void update(User user);
}
