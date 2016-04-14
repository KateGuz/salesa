package com.salesa.dao.impl;

import com.salesa.dao.UserDao;
import com.salesa.dao.mapper.UserMapper;
import com.salesa.dao.util.QueryAndParams;
import com.salesa.dao.util.QueryGenerator;
import com.salesa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserJdbcDao implements UserDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private QueryGenerator queryGenerator;

    @Autowired
    private String getAllUsersSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String getUserByEmailSQL;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(getAllUsersSQL, new UserMapper());
    }

    @Override
    public User get(int userId){
        QueryAndParams queryAndParams = queryGenerator.generateUserById(userId);
        return namedParameterJdbcTemplate.queryForObject(queryAndParams.query, queryAndParams.params, new UserMapper());
    }

    @Override
    public User get(String email){
        log.info("getting user by email " + email);
        return namedParameterJdbcTemplate.queryForObject(getUserByEmailSQL, new MapSqlParameterSource("email", email),new UserMapper());
    }
}
