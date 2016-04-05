package com.salesa.dao;

import com.salesa.dao.mapper.CategoryMapper;
import com.salesa.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryJdbcDaoTest {

    @InjectMocks
    private CategoryDao categoryDao = new CategoryJdbcDao();

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testGetAll() {
        // prepare
        Category categoryOne = new Category();
        categoryOne.setId(1);
        categoryOne.setName("room");


        Category categoryTwo = new Category();
        categoryTwo.setId(2);
        categoryTwo.setName("kitchen");
        categoryTwo.setParent(new Category(1));

        List<Category> mockList = Arrays.asList(categoryOne, categoryTwo);
        when(jdbcTemplate.query(any(String.class), any(CategoryMapper.class))).thenReturn(mockList);

        // when
        List<Category> result = categoryDao.getAll();

        // then
        assertEquals(2, result.size());
        assertEquals(result.get(0), categoryOne);
        assertEquals(result.get(1), categoryTwo);
        assertEquals(result.get(1).getParent(), categoryOne);

    }

}