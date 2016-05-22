package com.salesa.dao.impl;

import com.salesa.entity.Advert;
import com.salesa.entity.Report;
import com.salesa.entity.User;
import com.salesa.util.report.ReportGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/root-context.xml"})
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