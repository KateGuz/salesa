package com.salesa.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/root-context.xml"})
public class OperateReportTest {
    @Autowired
    private ReportDao reportDao;

    @Test
    public void testSaveImage() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src/test/resources/report/1-kenny006.jpg"));
        byte[] rawImageBytes = new byte[5_000_000];

    }


}