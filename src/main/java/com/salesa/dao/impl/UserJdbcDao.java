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
    private String saveUserSQL;

    @Autowired
    private String getUserByEmailSQL;

    @Autowired
    private String updateUsersDislikesSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(getAllUsersSQL, new UserMapper());
    }

    @Override
    public User get(int userId) {
        QueryAndParams queryAndParams = queryGenerator.generateUserByIdQuery(userId);
        return namedParameterJdbcTemplate.queryForObject(queryAndParams.query, queryAndParams.params, new UserMapper());
    }

    public int save(User user) {
        log.info("Query save user with email: ", user.getEmail());
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());

        namedParameterJdbcTemplate.update(saveUserSQL, params);
        return namedParameterJdbcTemplate.queryForObject(getUserByEmailSQL, new MapSqlParameterSource("email", user.getEmail()), new UserMapper()).getId();
    }

    @Override
    public User get(String email) {
        return namedParameterJdbcTemplate.queryForObject(getUserByEmailSQL, new MapSqlParameterSource("email", email), new UserMapper());
    }

    @Override
    public void updateUsersDislike(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("dislike", user.getDislikeAmount());
        params.put("email", user.getEmail());

        namedParameterJdbcTemplate.update(updateUsersDislikesSQL, params);
    }
}
