package com.salesa.dao;

import com.salesa.dao.mapper.FeedbackMapper;
import com.salesa.dao.util.QueryAndParams;
import com.salesa.dao.util.QueryGenerator;
import com.salesa.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackJdbcDao implements FeedbackDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public List<Feedback> getByUserId(int userId) {
        QueryAndParams queryAndParams = queryGenerator.generateFeedbacks(userId);
        return namedParameterJdbcTemplate.query(queryAndParams.query, queryAndParams.params, new FeedbackMapper());
    }
}
