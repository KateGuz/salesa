package com.salesa.service.impl;

import com.salesa.dao.ReportDao;
import com.salesa.entity.Report;
import com.salesa.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportDao reportDao;

    @Override
    public int save(Report report) {
        return reportDao.save(report);
    }

    @Override
    public Report get(int id) {
        return reportDao.get(id);
    }

    @Override
    public void deleteAll() {
        reportDao.deleteAll();
    }
}
