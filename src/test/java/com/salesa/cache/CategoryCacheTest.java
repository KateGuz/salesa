package com.salesa.cache;

import com.salesa.dao.CategoryJdbcDao;
import com.salesa.entity.Category;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;


public class CategoryCacheTest {
    @Mock
    private CategoryJdbcDao categoryJdbcDao;
    @Test
    public void testAdd() throws Exception {
        //prepare
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        categoryList.add(new Category());
        categoryList.add(new Category());
        //when
        CategoryCache.getInstance().add(categoryList);
        //then
        assertNotNull(CategoryCache.getInstance().getAll());
        assertEquals(3,CategoryCache.getInstance().getAll().size());
    }

    @Test
    public void testGetAll() throws Exception {
        //prepare
        List<Category> categoryList = new ArrayList<>();
        CategoryCache.getInstance().add(categoryList);
        //when
        List<Category> all = CategoryCache.getInstance().getAll();
        //then
        assertEquals(categoryList, all);
    }
}