package com.salesa.dao.mapper;

import com.salesa.entity.Report;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ReportMapperTest {
    @Test
    public void testReportMapper() throws SQLException {
        //prepare
        ReportMapper reportMapper = new ReportMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Report.xlsx");
        byte [] bytes = {0, 1, 0, 0, 1, 0};
        when(resultSet.getBytes("document")).thenReturn(bytes);

        //when
        Report report = reportMapper.mapRow(resultSet, 1);

        //then
        assertEquals(1, report.getId());
        assertEquals("Report.xlsx", report.getName());
        assertEquals(bytes, report.getDocument());
    }

}