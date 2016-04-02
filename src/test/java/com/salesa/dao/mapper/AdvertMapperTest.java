package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AdvertMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        // prepare
        AdvertMapper advertMapper = new AdvertMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("title")).thenReturn("Ad1");
        when(resultSet.getString("text")).thenReturn("text");
        when(resultSet.getInt("categoryId")).thenReturn(2);
        when(resultSet.getDouble("price")).thenReturn(1.1);
        when(resultSet.getString("currency")).thenReturn("UAH");
        when(resultSet.getInt("userId")).thenReturn(3);
        when(resultSet.getString("status")).thenReturn("A");

        // when
        Advert advert = advertMapper.mapRow(resultSet, 1);

        // then
        assertEquals(1, advert.getId());
        assertEquals("Ad1", advert.getTitle());
        assertEquals("text", advert.getText());
        assertEquals(2, advert.getCategoryId());
        assertEquals(1.1, advert.getPrice(), 0.1);
        assertEquals("UAH", advert.getCurrency());
        assertEquals(3, advert.getUserId());
        assertEquals("A", advert.getStatus());


    }

}
