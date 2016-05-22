package com.salesa.dao.impl;

import com.salesa.dao.ReportDao;
import com.salesa.dao.mapper.ReportDataMapper;
import com.salesa.dao.mapper.ReportMapper;
import com.salesa.dao.util.QueryAndParams;
import com.salesa.dao.util.QueryGenerator;
import com.salesa.entity.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportJdbcDao implements ReportDao{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String saveReportSQL;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String getReportByIdSQL;
    @Autowired
    private String deleteReportsSQL;
    @Autowired
    private QueryGenerator queryGenerator;

    public int save(Report report){
        log.info("saving report : {}", report);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", report.getName());
        mapSqlParameterSource.addValue("document", report.getDocument());
        return namedParameterJdbcTemplate.queryForObject(saveReportSQL, mapSqlParameterSource, Integer.class);
    }

    @Override
    public Report get(int id){
        log.info("getting report from database with id =  {}", id);
        return namedParameterJdbcTemplate.queryForObject(getReportByIdSQL, new MapSqlParameterSource("id", id), new ReportMapper());
    }

    @Override
    public void deleteAll() {
        log.info("deleting all reports");
        jdbcTemplate.execute(deleteReportsSQL);
    }

    @Override
    public int getCountActive(String dateFrom, String dateTo) {
        log.info("counting active adverts, period from " + dateFrom + " till " + dateTo);
        QueryAndParams queryAndParams = queryGenerator.generateActive(dateFrom, dateTo);
        return namedParameterJdbcTemplate.queryForObject(queryAndParams.query, queryAndParams.params, int.class);
    }

    @Override
    public int getCountOnHold(String dateFrom, String dateTo) {
        log.info("counting on hold adverts, period from " + dateFrom + " till " + dateTo);
        QueryAndParams queryAndParams = queryGenerator.generateOnHold(dateFrom, dateTo);
        return namedParameterJdbcTemplate.queryForObject(queryAndParams.query, queryAndParams.params, int.class);
    }
}
