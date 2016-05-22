package com.salesa.util.entity;

public class ReportRequest {
    private String format;
    private String dateFrom;
    private String dateTo;
    private String emailTo;
    private String currency;

    public ReportRequest(String format, String dateFrom, String dateTo, String emailTo, String currency) {
        this.format = format;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.emailTo = emailTo;
        this.currency = currency;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "format='" + format + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", emailTo='" + emailTo + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
