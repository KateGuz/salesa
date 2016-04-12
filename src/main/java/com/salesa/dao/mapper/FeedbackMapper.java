package com.salesa.dao.mapper;

import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements RowMapper<Feedback>{
    @Override
    public Feedback mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Feedback feedback = new Feedback(resultSet.getInt("id"));
        feedback.setText(resultSet.getString("text"));
        feedback.setAuthorId(new User(resultSet.getInt("authorId")));
        feedback.setUserId(new User(resultSet.getInt("userId")));
        feedback.setCreationDate((resultSet.getTimestamp("creationDate")).toLocalDateTime());
        return feedback;
    }
}
