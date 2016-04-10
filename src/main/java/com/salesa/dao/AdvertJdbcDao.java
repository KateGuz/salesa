package com.salesa.dao;

import com.salesa.dao.mapper.AdvertDetailsMapper;
import com.salesa.dao.mapper.AdvertMapper;
import com.salesa.dao.util.QueryAndParams;
import com.salesa.dao.util.QueryGenerator;
import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.AdvertDetails;
import com.salesa.util.AdvertPageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class AdvertJdbcDao implements AdvertDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final int MAX_ADVERTS_PER_PAGE = 9;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private QueryGenerator queryGenerator;

    @Autowired
    private String getAdvertsCountSQL;

    @Override
    public AdvertPageData get(AdvertFilter advertFilter) {
        QueryAndParams queryAndParams = queryGenerator.generateAdvertQuery(advertFilter);

        long startTime = System.currentTimeMillis();
        log.info("Query adverts information for request {}", advertFilter);
        List<Advert> adverts = namedParameterJdbcTemplate.query(queryAndParams.query, queryAndParams.params, new AdvertMapper());
        log.info("Query advert for page took {} ms", queryAndParams.query, System.currentTimeMillis() - startTime);

        Integer advertsCount = namedParameterJdbcTemplate.queryForObject(getAdvertsCountSQL, new HashMap<>(), Integer.class);

        int pageCount = advertsCount / MAX_ADVERTS_PER_PAGE;
        AdvertPageData advertPageData = new AdvertPageData();
        advertPageData.setAdverts(adverts);
        advertPageData.setPageCount(advertsCount % MAX_ADVERTS_PER_PAGE == 0 ? pageCount : pageCount + 1);
        return advertPageData;
    }

}
