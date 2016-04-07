package com.salesa.dao;

import com.salesa.dao.mapper.CategoryMapper;
import com.salesa.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryJdbcDao implements CategoryDao {

    @Autowired
    private String getAllCategories;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getAll() {
        List<Category> categories = jdbcTemplate.query(getAllCategories, new CategoryMapper());

        for (Category category : categories) {
            Category parent = category.getParent();
            if (parent != null) {
                int parentId = parent.getId();
                for (Category parentCategory : categories) {
                    if (parentId == parentCategory.getId()) {
                        category.setParent(parentCategory);
                    }
                }
            }

        }
        return categories;
    }
}