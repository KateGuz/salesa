package com.salesa.dao;

import com.salesa.dao.filter.UserFilter;
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
    public List<Advert> get(UserFilter userFilter) {
        int categoryId = userFilter.getCategoryId();

        if (categoryId == 0) {
            return jdbcTemplate.query(GET_ALL_SQL, new AdvertMapper());
        } else {
            String GET_ALL_BY_FILTER = "SELECT * FROM advert WHERE categoryId = " + categoryId;
            return jdbcTemplate.query(GET_ALL_BY_FILTER, new AdvertMapper());
        }
    }
}
