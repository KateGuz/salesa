package com.salesa.service.impl;

import com.salesa.cache.CategoryCache;
import com.salesa.dao.CategoryJdbcDao;
import com.salesa.entity.Category;
import com.salesa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryJdbcDao categoryJdbcDao;
    @Autowired
    private CategoryCache categoryCache;

    public List<Category> getAll() {
        return categoryCache.getAll();
    }

    //@PostConstruct annotation initialize(run) method after bean(CategoryServiceImpl) has been created.
    @PostConstruct
    public void initialize() {
        categoryCache.add(categoryJdbcDao.getAll());
    }
}
