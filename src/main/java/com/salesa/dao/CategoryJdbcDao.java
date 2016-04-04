package com.salesa.dao;

import com.salesa.dao.mapper.CategoryMapper;
import com.salesa.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryJdbcDao implements CategoryDao{

    @Autowired
    private String getAllCategories;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAll() {
        return jdbcTemplate.query(getAllCategories, new CategoryMapper());
    }
}
