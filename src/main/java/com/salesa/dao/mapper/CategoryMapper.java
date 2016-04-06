package com.salesa.dao.mapper;

import com.salesa.dao.CategoryJdbcDao;
import com.salesa.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryMapper implements RowMapper<Category> {

    @Autowired
    private CategoryJdbcDao categoryJdbcDao;

    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();

        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setParentId(resultSet.getInt("parentId"));
        // todo : remove mapping logic from here to service or dao
//        List<Category> allCategories = categoryJdbcDao.getAll();
//        for (Category aCategory : allCategories) {
//            if(aCategory.getId() == parentId){
//                category.setParent(aCategory);
//            }
//        }

        return category;
    }
}
