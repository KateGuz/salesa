package com.salesa.dao.mapper;

import com.salesa.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryExtractorTest {

    @Mock
    private ResultSet resultSet;

    @Test
    public void testMappingCategory() throws SQLException {
        //prepare
        CategoryExtractor categoryExtractor = new CategoryExtractor();
        when(resultSet.getInt("parentId")).thenReturn(1).thenReturn(2).thenReturn(2);
        when(resultSet.getString("parentName")).thenReturn("book").thenReturn("room").thenReturn("room");
        when(resultSet.getInt("childId")).thenReturn(0).thenReturn(3).thenReturn(4);
        when(resultSet.getString("childName")).thenReturn(null).thenReturn("kitchen").thenReturn("bath");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        //when
        List<Category> categories = categoryExtractor.extractData(resultSet);

        //then
        assertEquals(2, categories.size());

        assertEquals(1, (int)categories.get(0).getId());
        assertEquals("book", categories.get(0).getName());
        assertNull(categories.get(0).getChildren());

        assertEquals(2, (int)categories.get(1).getId());
        assertEquals("room", categories.get(1).getName());
        assertEquals(2, categories.get(1).getChildren().size());
        assertEquals("kitchen", categories.get(1).getChildren().get(0).getName());
        assertEquals("bath", categories.get(1).getChildren().get(1).getName());

    }
}
