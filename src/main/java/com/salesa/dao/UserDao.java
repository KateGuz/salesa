package com.salesa.dao;

import com.salesa.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User get(int userId);
    int save(User user);
    User get(String email);
    void updateUsersDislike(User user);
    void updateUserType(User user);
    void deleteUser(int userId);
    void update(User user);
}
