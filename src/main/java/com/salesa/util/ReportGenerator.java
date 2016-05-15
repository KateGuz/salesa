package com.salesa.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportGenerator {

    public static String writeIntoExcel(String path) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-M-yyyy_hh-mm-ss");
        File report = new File(path, dateFormat.format(LocalDateTime.now()) + ".xlsx");
        String[] fields = {"N", "Title", "Description", "Price", "Currency", "UserName", "UserEmail"};
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("sells");
        Row row = sheet.createRow(0);
        Cell[] cells = new Cell[fields.length];
        for(int i = 0; i < fields.length; i++){
            cells[i] = row.createCell(i);
            cells[i].setCellValue(fields[i]);
        }

        for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i++){
            sheet.autoSizeColumn(i);
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(report)){
            book.write(fileOutputStream);
        } catch (IOException e){
            e.getMessage();
        }
        return report.getAbsolutePath();
    }
}
