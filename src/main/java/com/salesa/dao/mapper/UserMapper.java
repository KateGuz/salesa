package com.salesa.dao.mapper;


import com.salesa.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getInt("id"));
        /*user.setId(resultSet.getInt("id"));*/
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getString("phone"));
        user.setAvatar(resultSet.getBinaryStream("avatar"));
        user.setStatus(resultSet.getString("status"));
        user.setType(resultSet.getString("type"));
        user.setDislikeAmount(resultSet.getInt("dislikeAmount"));

        return user;
    }
}
