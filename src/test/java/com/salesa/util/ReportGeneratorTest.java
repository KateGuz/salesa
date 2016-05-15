package com.salesa.util;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class ReportGeneratorTest {
    @Test
    public void testWriteIntoExcel() throws Exception {
        String path = "\\";
        String file = ReportGenerator.writeIntoExcel(path);
        System.out.println(file);
        assertTrue(new File(file).exists());
    }

}