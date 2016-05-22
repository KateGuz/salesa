package com.salesa.dao.impl;

import com.salesa.entity.Advert;
import com.salesa.entity.Report;
import com.salesa.util.report.ReportGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/root-context.xml"})
public class ReportJdbcDaoTest {
    @Autowired
    private ReportJdbcDao reportJdbcDao;
    @Autowired
    private AdvertJdbcDao advertJdbcDao;
    @Autowired
    private ReportGenerator reportGenerator;
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-M-yyyy_hh-mm-ss");
    private final String SEPARATOR = File.separator;
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

    @Test
    public void testCount(){
        int expected = 2;
        String dateFrom = "2016-05-01";
        String dateTo = "2016-05-22";
        assertEquals(expected, reportJdbcDao.getCountOnHold(dateFrom, dateTo));
    }

    @Test
    public void testWithAdvertAndReportGeneratorToFile() throws IOException {
        String dateFrom = "2016-05-01";
        String dateTo = "2016-05-22";
        String path = "reports" + SEPARATOR;
        List<Advert> adverts = advertJdbcDao.getForReport(dateFrom, dateTo);
        File pdf = new File(path, DATE_FORMAT.format(LocalDateTime.now()) + ".pdf");
        FileOutputStream fos = new FileOutputStream(pdf);
        fos.write(reportGenerator.writeIntoPdf(adverts, reportJdbcDao.getCountOnHold(dateFrom, dateTo), reportJdbcDao.getCountActive(dateFrom, dateTo)));
        fos.flush();
        fos.close();
        File xlsx = new File(path, DATE_FORMAT.format(LocalDateTime.now()) + ".xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(xlsx);
        fileOutputStream.write(reportGenerator.writeIntoExcel(adverts, reportJdbcDao.getCountOnHold(dateFrom, dateTo), reportJdbcDao.getCountActive(dateFrom, dateTo)));
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}