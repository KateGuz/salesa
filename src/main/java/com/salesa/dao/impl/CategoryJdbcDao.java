package com.salesa.dao.impl;

import com.salesa.dao.CategoryDao;
import com.salesa.dao.mapper.CategoryExtractor;
import com.salesa.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CategoryJdbcDao implements CategoryDao {

    @Autowired
    private String getAllCategoriesSQL;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAll() {
        return jdbcTemplate.query(getAllCategoriesSQL, new CategoryExtractor());
    }
}