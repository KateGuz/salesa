package com.salesa.service.impl;

import com.salesa.dao.FeedbackDao;
import com.salesa.dao.UserDao;
import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public User get(int userId) {
        return userDao.get(userId);
    }

    @Override
    public List<Feedback> getByUserId(int userId) {
        log.info("Applying query to get feedbacks by user with id {}", userId);
        return feedbackDao.getByUserId(userId);
    }

    @Override

    public int save(User user) {
        log.info("Applying saving user {}", user);
        return userDao.save(user);
    }

    @Override
    public User get(String email) {
        return userDao.get(email);
    }

    @Override
    public String getEmail(String email) {
        return get(email).getEmail();
    }

    @Override
    public void updateUsersDislike(User user) {
        userDao.updateUsersDislike(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateUserType(User user) {
        log.info("Applying updating users rights to user {}", user);
        userDao.updateUserType(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
