package com.salesa.dao.mapper;

import com.salesa.entity.Report;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements RowMapper<Report>{
    @Override
    public Report mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Report report = new Report();
        report.setId(resultSet.getInt("id"));
        report.setName(resultSet.getString("name"));
        report.setDocument(resultSet.getBytes("document"));
        return report;
    }
}
