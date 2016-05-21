package com.salesa.dao.impl;

import com.salesa.entity.Report;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ReportJdbcDaoTest {
    @Autowired
    private ReportJdbcDao reportJdbcDao;
    @Test
     public void testSaveReport(){
        //prepare
        Report report = new Report();
        byte [] expected = {0, 1, 2, -2};
        report.setName("Report");
        report.setDocument(new byte[]{0, 1, 2, -2});

        //when
        int id = reportJdbcDao.save(report);
        Report obtained = reportJdbcDao.get(id);

        //then
        assertEquals("Report", report.getName());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], obtained.getDocument()[i]);
        }
    }




}