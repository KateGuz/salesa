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
    private String updateUserSQL;

    @Autowired
    private String updateUserTypeSQL;

    @Autowired
    private String deleteUserSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(getAllUsersSQL, new UserMapper());
    }

    @Override
    public User get(int userId) {
        QueryAndParams queryAndParams = queryGenerator.generateUserById(userId);
        log.info("Query for user with id {}", userId);
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
        params.put("id", user.getId());
        params.put("dislike", user.getDislikeAmount());
        params.put("status", user.getStatus());
        log.info("Updating dislikes for user with params {}", params);
        namedParameterJdbcTemplate.update(updateUsersDislikesSQL, params);
    }

    @Override
    public void updateUser(User user) {
        log.info("Query update user with id: ", user.getId());
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("email", user.getEmail());
        params.put("phone", user.getPhone());
        params.put("password", user.getPassword());
        params.put("id", user.getId());

        namedParameterJdbcTemplate.update(updateUserSQL, params);
    }

    @Override
    public void updateUserType(User user){
        log.info("Query update user with id {}", user.getId());
        Map<String, Object> params = new HashMap<>();
        params.put("type", user.getType());
        params.put("id", user.getId());
        namedParameterJdbcTemplate.update(updateUserTypeSQL, params);
        log.info("User type was updated to {}", user.getType());
    }

    @Override
    public void deleteUser(User user){
        log.info("Query for deleting user by id: ", user.getId());
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        namedParameterJdbcTemplate.update(deleteUserSQL, params);
    }
}
