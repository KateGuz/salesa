package com.salesa.dao.impl;

import com.salesa.dao.AdvertDao;
import com.salesa.dao.mapper.*;
import com.salesa.dao.util.QueryAndParams;
import com.salesa.dao.util.QueryGenerator;
import com.salesa.entity.Advert;
import com.salesa.entity.Image;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.entity.AdvertPageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AdvertJdbcDao implements AdvertDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final int MAX_ADVERTS_PER_PAGE = 9;
    private static final AdvertExtractor ADVERT_EXTRACTOR = new AdvertExtractor();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private QueryGenerator queryGenerator;

    @Autowired
    private String getAdvertsCountSQL;

    @Autowired
    private String saveAdvertSQL;

    @Autowired
    private String updateAdvertSQL;

    @Autowired
    private String getAdvertImageSQL;


    @Override
    public AdvertPageData get(AdvertFilter advertFilter) {
        QueryAndParams queryAndParams = queryGenerator.generateAdvertQuery(advertFilter);

        long startTime = System.currentTimeMillis();
        log.info("Query adverts information for request {}", advertFilter);
        List<Advert> adverts = namedParameterJdbcTemplate.query(queryAndParams.query, queryAndParams.params, new AdvertMapper());
        log.info("Query adverts without page limit took {} ms", System.currentTimeMillis() - startTime);

        //get images
        for (Advert advert : adverts) {
            advert.setImages(namedParameterJdbcTemplate.query(getAdvertImageSQL, new MapSqlParameterSource("advertId", advert.getId()), new ImageMapper()));
        }

        Integer advertsCount;
        Map<String, Object> paramMap = new HashMap<>();
        String query = getAdvertsCountSQL;
        log.info("Query page information for request {}", advertFilter);
        if (advertFilter.getCategoryId() > 0 || advertFilter.isActive()) {
            if (advertFilter.getCategoryId() > 0) {
                paramMap.put("categoryId", advertFilter.getCategoryId());
                query += " AND categoryId = :categoryId";
            }
            if (advertFilter.isActive()) {
                query += " AND status = 'A'";
            }
            query += ";";
            advertsCount = namedParameterJdbcTemplate.queryForObject(query, paramMap, Integer.class);
        } else {
            advertsCount = namedParameterJdbcTemplate.queryForObject(query, new HashMap<>(), Integer.class);
        }
        log.info("Obtained {} adverts for filter {}", advertsCount, advertFilter);

        int pageCount = advertsCount / MAX_ADVERTS_PER_PAGE;
        AdvertPageData advertPageData = new AdvertPageData();
        advertPageData.setAdverts(adverts);
        advertPageData.setPageCount(advertsCount % MAX_ADVERTS_PER_PAGE == 0 ? pageCount : pageCount + 1);
        return advertPageData;
    }

    @Override
    public Advert get(int advertId) {
        QueryAndParams queryAndParams = queryGenerator.generateAdvertQuery(advertId);
        return namedParameterJdbcTemplate.query(queryAndParams.query, queryAndParams.params, ADVERT_EXTRACTOR).get(0);
    }

    @Override
    public List<Advert> getByUserId(int userId) {
        QueryAndParams queryAndParams = queryGenerator.generateAdvertByUserIdQuery(userId);
        return namedParameterJdbcTemplate.query(queryAndParams.query, queryAndParams.params, ADVERT_EXTRACTOR);
    }

    @Override
    public int saveAdvert(Advert advert) {
        log.info("Saving advert {}", advert);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("title", advert.getTitle());
        mapSqlParameterSource.addValue("text", advert.getText());
        mapSqlParameterSource.addValue("categoryId", advert.getCategory().getId());
        mapSqlParameterSource.addValue("price", advert.getPrice());
        mapSqlParameterSource.addValue("currency", advert.getCurrency());
        mapSqlParameterSource.addValue("status", advert.getStatus());
        mapSqlParameterSource.addValue("modificationDate", advert.getModificationDate());
        mapSqlParameterSource.addValue("userId", advert.getUser().getId());

        int savedAdvertId = namedParameterJdbcTemplate.queryForObject(saveAdvertSQL, mapSqlParameterSource, int.class);

        log.info("saving  advert with id {} finished", savedAdvertId);
        return savedAdvertId;
    }

    @Override
    public void update(Advert advert) {
        log.info("updating advert {}", advert);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("title", advert.getTitle());
        mapSqlParameterSource.addValue("text", advert.getText());
        mapSqlParameterSource.addValue("categoryId", advert.getCategory().getId());
        mapSqlParameterSource.addValue("price", advert.getPrice());
        mapSqlParameterSource.addValue("currency", advert.getCurrency());
        mapSqlParameterSource.addValue("status", advert.getStatus());
        mapSqlParameterSource.addValue("modificationDate", advert.getModificationDate());
        mapSqlParameterSource.addValue("id", advert.getId());

        namedParameterJdbcTemplate.update(updateAdvertSQL, mapSqlParameterSource);
    }

}
