package com.salesa.cache;

import com.salesa.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryCache {
    private static List<Category> categoryList;

    public CategoryCache() {
        categoryList = new ArrayList<>();
    }

    public void add(List<Category> categories) {
        categoryList.addAll(categories);
    }

    public List<Category> getAll() {
        return categoryList;
    }
}
