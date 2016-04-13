package com.salesa.dao.impl;

import com.salesa.dao.UserDao;
import com.salesa.dao.mapper.UserMapper;
import com.salesa.dao.util.QueryAndParams;
import com.salesa.dao.util.QueryGenerator;
import com.salesa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcDao implements UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private QueryGenerator queryGenerator;

    @Autowired
    private String getAllUsersSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(getAllUsersSQL, new UserMapper());
    }

    @Override
    public User get(int userId){
        QueryAndParams queryAndParams = queryGenerator.generateUserById(userId);
        return namedParameterJdbcTemplate.queryForObject(queryAndParams.query, queryAndParams.params, new UserMapper());
    }
}
