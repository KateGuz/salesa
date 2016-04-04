package com.salesa.dao;

import com.salesa.dao.mapper.UserMapper;
import com.salesa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserJdbcDao implements UserDao {

    @Autowired
    private String getAllUsers;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {

        return jdbcTemplate.query(getAllUsers, new UserMapper());

    }
}
