package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ReportDataMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        //prepare
        ReportDataMapper reportDataMapper = new ReportDataMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("advertId")).thenReturn(1);
        when(resultSet.getString("advertTitle")).thenReturn("Kitty");
        when(resultSet.getString("advertText")).thenReturn("nice");
        when(resultSet.getDouble("advertPrice")).thenReturn(50.0);
        when(resultSet.getString("advertCurrency")).thenReturn("UAH");
        when(resultSet.getString("advertStatus")).thenReturn("A");
        when(resultSet.getTimestamp("advertModificationDate")).thenReturn(Timestamp.valueOf("2016-01-01 12:00:05"));
        when(resultSet.getString("userName")).thenReturn("mary");
        when(resultSet.getString("userEmail")).thenReturn("mary@gmail.com");

        //when
        Advert advert = reportDataMapper.mapRow(resultSet, 1);

        //then
        assertEquals(1, advert.getId());
        assertEquals("Kitty", advert.getTitle());
        assertEquals("nice", advert.getText());
        assertEquals(50.0, advert.getPrice(), 0.0);
        assertEquals("UAH", advert.getCurrency());
        assertEquals("A", advert.getStatus());
        assertEquals(LocalDateTime.parse("2016-01-01T12:00:05"), advert.getModificationDate());
        assertEquals("mary", advert.getUser().getName());
        assertEquals("mary@gmail.com", advert.getUser().getEmail());
    }

}