package com.salesa.dao.impl;

import com.salesa.dao.FeedbackDao;
import com.salesa.dao.mapper.FeedbackMapper;
import com.salesa.entity.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackJdbcDao implements FeedbackDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private String getFeedbackByUserIdSQL;

    @Autowired
    private String saveFeedbackSQL;

    @Override
    public List<Feedback> getByUserId(int userId) {
        log.info("Getting feedbacks by user with id {}", userId);
        return namedParameterJdbcTemplate.query(getFeedbackByUserIdSQL, new MapSqlParameterSource("userId", userId), new FeedbackMapper());
    }

    public void saveFeedback(Feedback feedback) {
        log.info("Making query for save feedback");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("text", feedback.getText());
        mapSqlParameterSource.addValue("authorId", feedback.getAuthor().getId());
        mapSqlParameterSource.addValue("userId", feedback.getUser().getId());
        mapSqlParameterSource.addValue("creationDate", feedback.getCreationDate());
        namedParameterJdbcTemplate.update(saveFeedbackSQL, mapSqlParameterSource);
        log.info("Feedback by author {} to user {} was successfully saved",
                feedback.getAuthor().getName(), feedback.getUser().getName());
    }
}
