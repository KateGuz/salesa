package com.salesa.dao;

import com.salesa.dao.mapper.AdvertMapper;
import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.AdvertPageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdvertJdbcDao implements AdvertDao {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final int MAX_ADVERTS_PER_PAGE = 9;

    @Autowired
    private String getAllAdverts;

    @Autowired
    private String getLimitNumberOfAdverts;

    @Autowired
    private String getLimitNumberOfAdvertsByCategory;

    @Autowired
    private String getAdvertsByCategory;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public AdvertPageData get(AdvertFilter advertFilter) {
        AdvertPageData advertPageData = new AdvertPageData();
        int categoryId = advertFilter.getCategoryId();
        List<Advert> adverts;
        if(categoryId != 0){

            Map<String, Integer> namedParameters = generateQueryParameters(advertFilter);
            namedParameters.put("categoryId", categoryId);

            long startTime = System.currentTimeMillis();
            log.info("Query adverts information");
            adverts = namedParameterJdbcTemplate.query(getLimitNumberOfAdvertsByCategory, namedParameters, new AdvertMapper());
            log.info("Query {}  took {} ms", getLimitNumberOfAdvertsByCategory, System.currentTimeMillis() - startTime);

            startTime = System.currentTimeMillis();
            List<Advert> allAdverts = namedParameterJdbcTemplate.query(getAllAdverts, new AdvertMapper());
            log.info("Query {}  took {} ms", getAllAdverts, System.currentTimeMillis() - startTime);

            if (allAdverts.size() % MAX_ADVERTS_PER_PAGE == 0) {
                advertPageData.setPageCount(allAdverts.size() / MAX_ADVERTS_PER_PAGE);
            } else {
                advertPageData.setPageCount(allAdverts.size() / MAX_ADVERTS_PER_PAGE + 1);
            }

        }else {

            long startTime = System.currentTimeMillis();
            log.info("Query adverts information");
            adverts = namedParameterJdbcTemplate.query(getLimitNumberOfAdverts, generateQueryParameters(advertFilter), new AdvertMapper());
            log.info("Query {}  took {} ms", getLimitNumberOfAdverts, System.currentTimeMillis() - startTime);

            startTime = System.currentTimeMillis();
            List<Advert> allAdverts = namedParameterJdbcTemplate.query(getAllAdverts, new AdvertMapper());
            log.info("Query {}  took {} ms", getAllAdverts, System.currentTimeMillis() - startTime);

            if (allAdverts.size() % MAX_ADVERTS_PER_PAGE == 0) {
                advertPageData.setPageCount(allAdverts.size() / MAX_ADVERTS_PER_PAGE);
            } else {
                advertPageData.setPageCount(allAdverts.size() / MAX_ADVERTS_PER_PAGE + 1);
            }
        }

        advertPageData.setAdverts(adverts);

        return advertPageData;
    }

     Map<String, Integer> generateQueryParameters(AdvertFilter advertFilter) {
        int page = advertFilter.getPage();
        int startPosition = MAX_ADVERTS_PER_PAGE * (page - 1);

        Map<String, Integer> namedParameters = new HashMap<>();
        namedParameters.put("startPosition", startPosition);
        namedParameters.put("maxAdvertsPerPage", MAX_ADVERTS_PER_PAGE);

        return namedParameters;
    }

}
