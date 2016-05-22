package com.salesa.dao.mapper;

import com.salesa.entity.Image;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ImageMapperTest {

    @Test
    public void testRowMap() throws SQLException {
        //prepare
        ImageMapper imageMapper = new ImageMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        byte[] bytes = {0, 0, 0, 1, 1, 1};
        when(resultSet.getBytes("picture")).thenReturn(bytes);
        when(resultSet.getString("type")).thenReturn("M");

        //when
        Image image = imageMapper.mapRow(resultSet, 1);

        //then
        assertEquals(1, image.getId());
        assertEquals(bytes, image.getContent());
        assertEquals("M", image.getType());
    }

}
