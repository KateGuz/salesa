package com.salesa.util.report;

import com.salesa.entity.Advert;
import com.salesa.entity.User;
import com.salesa.util.report.ReportGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReportGeneratorTest {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-M-yyyy_hh-mm-ss");
    private ReportGenerator reportGenerator = new ReportGenerator();
    private final String SEPARATOR = File.separator;

    private List<Advert> generateAdvert(int count) {
        List<Advert> adverts = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Advert advert = new Advert();
            advert.setId(i);
            advert.setPrice(500 * i);
            advert.setText("текст на русском" + i);
            advert.setTitle("title" + i);
            User user = new User();
            user.setName("user" + i);
            user.setEmail(user.getName() + "@" + "com");
            advert.setUser(user);
            advert.setCurrency(i % 2 == 0 ? "USD" : "EUR");
            adverts.add(advert);
        }
        return adverts;
    }


    @Test
    public void testAdvertsWriteIntoExcelFile() throws Exception {
        //prepare
        byte[] document = reportGenerator.writeIntoExcel(generateAdvert(2));
        String path = "reports" + SEPARATOR;
        String fileName = DATE_FORMAT.format(LocalDateTime.now()) + ".xlsx";
        //then
        File file = new File(path, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(document);
        fos.flush();
        fos.close();
        //when
        assertTrue(file.exists());
        assertEquals(fileName, file.getName());
        assertEquals(path + fileName, file.getPath());
    }

    @Test
    public void testWriteAdvertsIntoPDF() throws Exception {
        //prepare
        byte[] document = reportGenerator.writeIntoPdf(generateAdvert(2));
        String path = "reports" + SEPARATOR;
        String fileName = DATE_FORMAT.format(LocalDateTime.now()) + ".pdf";
        //then
        File file = new File(path, DATE_FORMAT.format(LocalDateTime.now()) + ".pdf");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(document);
        fos.flush();
        fos.close();
        //when
        assertTrue(file.exists());
        assertEquals(fileName, file.getName());
        assertEquals(path  + fileName, file.getPath());
    }

}