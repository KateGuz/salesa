package com.salesa.dao.impl;

import com.salesa.dao.CategoryDao;
import com.salesa.dao.mapper.CategoryExtractor;
import com.salesa.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CategoryJdbcDao implements CategoryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String getAllCategoriesSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAll() {
        log.info("Applying query for getting all categories");
        return jdbcTemplate.query(getAllCategoriesSQL, new CategoryExtractor());
    }
}