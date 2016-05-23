package com.salesa.dao;

import com.salesa.entity.Report;

public interface ReportDao {
    int save(Report report);
    Report get(int id);
    void deleteAll();
    int getCountActive(String dateFrom, String dateTo);
    int getCountOnHold(String dateFrom, String dateTo);

}
