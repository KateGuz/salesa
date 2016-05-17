package com.salesa.dao.impl;

import com.salesa.dao.ImageDao;
import com.salesa.dao.mapper.ImageMapper;
import com.salesa.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageJdbcDao implements ImageDao {

    public static final ImageMapper IMAGE_ROW_MAPPER = new ImageMapper();
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private String saveAdvertImageSQL;

    @Autowired
    private String getAdvertImageSQL;

    @Autowired
    private String getAdvertImageByIdSQL;

    @Override
    public Image getAdvertImageById(int imageId) {
        log.info("Getting image with id {}", imageId);
        Image image = namedParameterJdbcTemplate.queryForObject(getAdvertImageByIdSQL, new MapSqlParameterSource("imageId", imageId), IMAGE_ROW_MAPPER);
        log.info("Query image with id {} finished", imageId);
        return image;
    }

    @Override
    public Image getAdvertImage(int advertId) {
        log.info("query image for advert with id {}", advertId);
        Image image = namedParameterJdbcTemplate.queryForObject(getAdvertImageSQL, new MapSqlParameterSource("advertId", advertId), IMAGE_ROW_MAPPER);
        log.info("query image for advert with id {} finished", advertId);
        return image;
    }

    @Override
    public void saveAdvertImage(Image image, int advertId) {
        log.info("Saving image for advert with id {}", advertId);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("picture", image.getContent());
        mapSqlParameterSource.addValue("type", image.getType());
        mapSqlParameterSource.addValue("advertId", advertId);
        namedParameterJdbcTemplate.update(saveAdvertImageSQL, mapSqlParameterSource);
    }
}
