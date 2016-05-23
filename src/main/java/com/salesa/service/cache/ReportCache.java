package com.salesa.service.cache;

import com.salesa.entity.Advert;
import com.salesa.entity.Report;
import com.salesa.service.AdvertService;
import com.salesa.service.ReportService;
import com.salesa.util.CurrencyConverter;
import com.salesa.util.entity.ReportRequest;
import com.salesa.util.report.ReportGenerator;
import com.salesa.util.report.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReportCache {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-M-yyyy_hh-mm-ss");
    @Autowired
    private ReportGenerator reportGenerator;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private CurrencyConverter currencyConverter;
    @Autowired
    private ReportService reportService;
    @Autowired
    private Sender sender;
    private final Logger log = LoggerFactory.getLogger(getClass());

    private List<ReportRequest> reportRequestCache = new ArrayList<>();

    public List<ReportRequest> getReportRequestCache() {
        return reportRequestCache;
    }

    @Scheduled(fixedRate = 1000 * 60)
    private synchronized void checkReport() throws Exception {
        if (reportRequestCache.isEmpty()) {
            log.info("there are no any request for report");
        } else {
            log.info("creating reports");
            createReports();
            log.info("reports has been created");
        }
    }

    @Async
    private void createReports() throws Exception {
        log.info("creating reports has been started");
        for (ReportRequest reportRequest : reportRequestCache) {
            List<Advert> adverts = advertService.getForReport(reportRequest.getDateFrom(), reportRequest.getDateTo());
            if(adverts.isEmpty()){
                sender.sendWithoutReport(reportRequest);
            }
            for (Advert advert : adverts) {
                currencyConverter.updatePriceAndCurrency(advert, reportRequest.getCurrency());
            }
            Report report = new Report();
            if ("xlsx".equals(reportRequest.getFormat())) {
                report.setName(DATE_FORMAT.format(LocalDateTime.now()) + ".xlsx");
                report.setDocument(reportGenerator.writeIntoExcel(adverts));
            } if("pdf".equals(reportRequest.getFormat())){
                report.setName(DATE_FORMAT.format(LocalDateTime.now()) + ".pdf");
                report.setDocument(reportGenerator.writeIntoPdf(adverts));
            }
            sender.send(reportRequest, reportService.save(report));
        }
        log.info("cleaning cache");
        reportRequestCache.clear();
    }

   @Scheduled(cron = "15 15 15 ? MAY MON")
    private void deleteReports() {
        reportService.deleteAll();
    }
}
