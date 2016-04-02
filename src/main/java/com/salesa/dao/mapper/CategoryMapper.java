package com.salesa.dao.mapper;

import com.salesa.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category>{
    @Override
    public Category mapRow(ResultSet resultSet, int i){
        Category category = new Category();
        try{
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            category.setParentId(resultSet.getInt("parentId"));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return category;
    }
}
