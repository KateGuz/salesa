package com.salesa.util.report;

import com.salesa.util.entity.ReportRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class Sender {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private String name;
    private String pass;
    private Properties props;
    public Sender(String name, String pass) {
        this.name = name;
        this.pass = pass;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(ReportRequest reportRequest, int reportId) throws Exception {
        log.info("sending report for request: {} ", reportRequest);
        String subject = "report about sales from ";
        String text = "click the following link to download report http://localhost:8080/report/";
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(name, pass);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(
                new InternetAddress(name));
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(reportRequest.getEmailTo()));
        message.setSubject(subject + reportRequest.getDateFrom() + " till " + reportRequest.getDateFrom());
        message.setText(text + reportId);

        Transport.send(message);
    }
}
