package com.salesa.cache;

import com.salesa.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

 @RunWith(MockitoJUnitRunner.class)
public class CategoryCacheTest {
    @Test
    public void testAdd() throws Exception {
        //prepare
        CategoryCache categoryCache = new CategoryCache();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        //when
        categoryCache.add(categoryList);
        //then
        assertNotNull(categoryCache.getAll());
        assertEquals(3,categoryCache.getAll().size());
    }

    @Test
    public void testGetAll() throws Exception {
        //prepare
        CategoryCache categoryCache = new CategoryCache();
        List<Category> categoryList = new ArrayList<>();
        categoryCache.add(categoryList);
        //when
        List<Category> all = categoryCache.getAll();
        //then
        assertEquals(categoryList, all);
    }
}