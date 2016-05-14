package com.salesa.service.impl;
import com.salesa.service.cache.CategoryCache;
import com.salesa.entity.Category;
import com.salesa.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryCache categoryCache;

    public List<Category> getAll() {
        log.info("Get categories from cache");
        long startTime = System.currentTimeMillis();
        List<Category> categoryTree = categoryCache.getCategoryTree();
        log.info("Got {} categories from cache, took {}", categoryTree.size(),
                System.currentTimeMillis() - startTime);
        return categoryTree;
    }

}