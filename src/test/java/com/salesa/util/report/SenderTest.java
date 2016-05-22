package com.salesa.util.report;

import com.salesa.util.entity.ReportRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SenderTest {

    private Sender sender = new Sender("info.salesa.2016@gmail.com", "saleYourGoods24/7/365");
    private String emailTo = "chickitosik@gmail.com";
    private String dateTo = "2016-05-17";
    private String dateFrom = "2016-05-01";
    private ReportRequest reportRequest = new ReportRequest("pdf", dateFrom, dateTo, emailTo, "USD");

    @Test
    public void testSendEmail() throws Exception {
       sender.send(reportRequest, 5);
    }

    @Test
    public void testSendEmailWithoutReport() throws Exception {
        sender.sendWithoutReport(reportRequest);
    }
}