package com.salesa.service.impl;

import com.salesa.dao.FeedbackDao;
import com.salesa.entity.Feedback;
import com.salesa.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public void saveFeedback(Feedback feedback) {
        log.info("Applying query to send feedback {}", feedback);
        feedbackDao.saveFeedback(feedback);
    }
}
