package com.salesa.dao;

import com.salesa.dao.mapper.AdvertMapper;
import com.salesa.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertJdbcDao implements AdvertDao {
    private static final String GET_ALL_SQL = "SELECT * FROM advert;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Advert> getAll() {
        return jdbcTemplate.query(GET_ALL_SQL, new AdvertMapper());
    }
}
