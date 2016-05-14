package com.salesa.service.cache;

import com.salesa.dao.CategoryDao;
import com.salesa.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryCache {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<Category> categoryTreeCache = new ArrayList<>();
    private List<Category> allCategoriesCache = new ArrayList<>();

    @Autowired
    private CategoryDao categoryDao;

    // todo: replace synchronized with ReentrantReadWriteLock
    @Scheduled(fixedRate = 1000 * 60 * 60 * 4)
    private synchronized void invalidate() {
        log.info("Start invalidating of category cache");
        categoryTreeCache.clear();
        categoryTreeCache.addAll(categoryDao.getAll());
        allCategoriesCache.clear();
        for (Category parent : categoryTreeCache) {
            allCategoriesCache.add(parent);
            List<Category> children = parent.getChildren();
            if (children != null) {
                allCategoriesCache.addAll(children);
            }
        }
        log.info("Category cache is invalidated, {} categories have been put to cache", categoryTreeCache.size());
    }

    public synchronized List<Category> getCategoryTree() {
        ArrayList<Category> categories = new ArrayList<>(categoryTreeCache);
        log.trace("categories in the cache : {}", categories);
        return categories;
    }

    public synchronized Category getCategoryById(int id) {
        return allCategoriesCache.stream().filter((t) -> t.getId() == id).findAny().get();
    }
}