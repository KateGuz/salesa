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

    private List<Category> categoryCache = new ArrayList<>();

    @Autowired
    private CategoryDao categoryDao;

    // todo: replace synchronized with ReentrantReadWriteLock
    @Scheduled(fixedRate = 1000 * 60 * 60 * 4)
    private synchronized void invalidate() {
        log.info("Start invalidating of category cache");
        categoryCache.clear();
        categoryCache.addAll(categoryDao.getAll());
        log.info("Category cache is invalidated, {} categories have been put to cache", categoryCache.size());
    }

    public synchronized List<Category> getAll() {
        return new ArrayList<>(categoryCache);
    }
}
