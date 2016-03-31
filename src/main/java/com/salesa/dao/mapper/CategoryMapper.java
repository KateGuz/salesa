package com.salesa.dao.mapper;

import com.salesa.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper {
    public static Category map(ResultSet resultSet) {
        Category category = new Category();
        try {
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("category"));
            category.setParentId(resultSet.getInt("parentId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}
