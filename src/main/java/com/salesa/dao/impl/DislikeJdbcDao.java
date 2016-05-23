package com.salesa.dao.impl;

import com.salesa.dao.DislikeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DislikeJdbcDao implements DislikeDao{

    @Autowired
    private String checkDislikeSQL;

    @Autowired
    private String updateDislikeTableSQL;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer checkDislike(int senderId, int receiverId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("senderId", senderId);
        parameterMap.addValue("receiverId", receiverId);
        return namedParameterJdbcTemplate.queryForObject(checkDislikeSQL, parameterMap, Integer.class);
    }

    @Override
    public void updateDislike(int senderId, int receiverId) {
        MapSqlParameterSource parameterMap = new MapSqlParameterSource();
        parameterMap.addValue("senderId", senderId);
        parameterMap.addValue("receiverId", receiverId);
        namedParameterJdbcTemplate.update(updateDislikeTableSQL, parameterMap);
    }
}
