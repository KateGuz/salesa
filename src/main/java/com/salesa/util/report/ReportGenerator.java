package com.salesa.util.report;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.salesa.entity.Advert;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ReportGenerator {
    private final String[] HEADER = {"#", "Title", "Description", "Price", "Currency", "Name", "Email"};
    private final Logger log = LoggerFactory.getLogger(getClass());

    public byte[] writeIntoExcel(List<Advert> adverts) {
        Workbook book = buildExcelDocument(adverts);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            book.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] writeIntoPdf(List<Advert> adverts) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            log.info("creating pdf file for report");
            PdfWriter.getInstance(document, out);
            document.open();
            //header
            PdfPTable table = new PdfPTable(HEADER.length);
            PdfPCell[] header = new PdfPCell[HEADER.length];
            for (int i = 0; i < HEADER.length; i++) {
                header[i] = new PdfPCell(new Phrase(HEADER[i]));
                header[i].setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(header[i]);
            }
            //content
            int i = 1;
            for (Advert advert : adverts) {
                table.addCell(String.valueOf(i++));
                table.addCell(advert.getTitle());
                table.addCell(advert.getText());
                table.addCell(String.valueOf(advert.getPrice()));
                table.addCell(advert.getCurrency());
                table.addCell(advert.getUser().getName());
                table.addCell(advert.getUser().getEmail());
            }
            document.add(table);
            document.close();
            return out.toByteArray();

        } catch (IOException | DocumentException ex) {
            ex.getMessage();
        }
        return null;
    }

    private XSSFWorkbook buildExcelDocument(List<Advert> adverts) {
        log.info("creating excel file for report");
        Workbook book = new XSSFWorkbook();
        Font font = book.createFont();
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setColor(Font.COLOR_NORMAL);
        Sheet sheet = book.createSheet("sells");
        Cell[] cells = new Cell[HEADER.length];
        //header
        Row head = sheet.createRow(0);
        for (int i = 0; i < HEADER.length; i++) {
            cells[i] = head.createCell(i);
            cells[i].setCellValue(HEADER[i]);
        }

        //content
        for (int i = 0; i < adverts.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(adverts.get(i).getTitle());
            row.createCell(2).setCellValue(adverts.get(i).getText());
            row.createCell(3).setCellValue(adverts.get(i).getPrice());
            row.createCell(4).setCellValue(adverts.get(i).getCurrency());
            row.createCell(5).setCellValue(adverts.get(i).getUser().getName());
            row.createCell(6).setCellValue(adverts.get(i).getUser().getEmail());
        }

        return (XSSFWorkbook) book;
    }
}
