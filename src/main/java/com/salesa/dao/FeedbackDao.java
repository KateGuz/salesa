package com.salesa.dao;


import com.salesa.entity.Feedback;

import java.util.List;

public interface FeedbackDao {
    List<Feedback> getByUserId(int userId);
}
