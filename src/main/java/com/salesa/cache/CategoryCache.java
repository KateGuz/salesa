package com.salesa.cache;

import com.salesa.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryCache {
    private static CategoryCache INSTANCE = new CategoryCache();
    private static List<Category> categoryList = new ArrayList<>();

    public static CategoryCache getInstance() {
        return INSTANCE;
    }

    private CategoryCache() {
    }

    public void add(List<Category> categories) {
        categoryList.addAll(categories);
    }
    public List<Category> getAll() {
        return categoryList;
    }
}
