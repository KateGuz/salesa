package com.salesa.dao;

import com.salesa.dao.mapper.AdvertMapper;
import com.salesa.entity.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdvertJdbcDao implements AdvertDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String getAllAdverts;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Advert> getAll() {
        long startTime = System.currentTimeMillis();
        log.info("Query adverts information");
        List<Advert> adverts = jdbcTemplate.query(getAllAdverts, new AdvertMapper());
        log.info("Query {} adverts took {} ms", adverts.size(), System.currentTimeMillis() - startTime);
        return adverts;
    }
}
