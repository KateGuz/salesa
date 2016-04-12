package com.salesa.service.impl;

import com.salesa.dao.AdvertDao;
import com.salesa.dao.FeedbackDao;
import com.salesa.dao.UserDao;
import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import com.salesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
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
        return feedbackDao.getByUserId(userId);
    }
}