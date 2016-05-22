package com.salesa.controller;

import com.salesa.entity.Report;
import com.salesa.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadReportController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/report/{reportId}",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    @ResponseBody
    public byte[] getReportExcel(@PathVariable ("reportId") int reportId){
        Report report = reportService.get(reportId);
        log.info("getting report with, format xlsx: {} ", report);
        return report.getDocument();
    }

    @RequestMapping(value = "/report/{reportId}", produces = "application/pdf")
    @ResponseBody
    public byte[] getReportPdf(@PathVariable ("reportId") int reportId){
        Report report = reportService.get(reportId);
        log.info("getting report with, format pdf: {} ", report);
        return report.getDocument();
    }

}
