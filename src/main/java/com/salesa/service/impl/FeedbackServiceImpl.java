package com.salesa.service.impl;

import com.salesa.dao.FeedbackDao;
import com.salesa.entity.Feedback;
import com.salesa.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackDao.saveFeedback(feedback);
    }
}
