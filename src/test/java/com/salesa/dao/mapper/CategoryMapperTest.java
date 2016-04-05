package com.salesa.dao.mapper;

import com.salesa.entity.Category;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryMapperTest {

    @Test
    public void testMappingCategory() throws SQLException {
        //prepare
        CategoryMapper categoryMapper = new CategoryMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1).thenReturn(2);
        when(resultSet.getString("name")).thenReturn("room").thenReturn("kitchen");
        when(resultSet.getInt("parentId")).thenReturn(0).thenReturn(1);
        
        //when
        Category categoryOne = categoryMapper.mapRow(resultSet, 1);
        Category categoryTwo = categoryMapper.mapRow(resultSet, 2);

        //then
        assertEquals(1, categoryOne.getId());
        assertEquals("room", categoryOne.getName());
        assertEquals(0, categoryOne.getParent().getId());

        assertEquals(2, categoryTwo.getId());
        assertEquals("kitchen", categoryTwo.getName());
        assertEquals(1, categoryTwo.getParent().getId());
    }
}
