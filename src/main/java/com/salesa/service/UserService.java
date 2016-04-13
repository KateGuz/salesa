package com.salesa.service;

import com.salesa.entity.Feedback;
import com.salesa.entity.User;

import java.util.List;

public interface UserService {
    User get(int userId);
    List<Feedback> getByUserId(int userId);
    User get(String email);
}
