package com.salesa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DefaultPriceUpdater {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String updateDefaultPriceSQL;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void updateDefaultPrice() {
        List<String> currencies = new ArrayList<>(Arrays.asList("UAH", "USD", "EUR"));
        for (String currency : currencies) {
            log.info("Updating default advert price for " + currency);
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("rate", currencyConverter.calculateRate(currency, "UAH"));
            mapSqlParameterSource.addValue("currency", currency);
            namedParameterJdbcTemplate.update(updateDefaultPriceSQL, mapSqlParameterSource);
        }
    }

}
