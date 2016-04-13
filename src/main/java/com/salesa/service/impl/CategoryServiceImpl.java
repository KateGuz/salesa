package com.salesa.service.impl;

import com.salesa.service.cache.CategoryCache;
import com.salesa.entity.Category;
import com.salesa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryCache categoryCache;

    public List<Category> getAll() {
        return categoryCache.getCategoryTree();
    }
}
