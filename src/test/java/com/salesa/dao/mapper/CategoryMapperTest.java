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
    public void testMappingCategory() throws SQLException{
        //prepare
        CategoryMapper categoryMapper = new CategoryMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("room");
        when(resultSet.getInt("parentId")).thenReturn(0);
        //when
        Category category = categoryMapper.mapRow(resultSet, 1);
        //then
        assertEquals(1, category.getId());
        assertEquals("room", category.getName());
        assertEquals((Integer) 0, category.getParentId());
    }
}
