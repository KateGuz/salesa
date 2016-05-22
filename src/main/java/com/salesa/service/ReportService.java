package com.salesa.service;

import com.salesa.entity.Report;

public interface ReportService {
    int save(Report report);
    Report get(int id);
    void deleteAll();
    int getCountActive(String dateFrom, String dateTo);
    int getCountOnHold(String dateFrom, String dateTo);
}
