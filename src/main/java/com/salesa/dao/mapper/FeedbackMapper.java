package com.salesa.dao.mapper;

import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setText(resultSet.getString("text"));
        User author = new User();
        author.setId(resultSet.getInt("authorId"));
        author.setName(resultSet.getString("authorName"));
        feedback.setAuthor(author);
        feedback.setCreationDate((resultSet.getTimestamp("creationDate")).toLocalDateTime());
        return feedback;
    }
}
