package com.salesa.dao.impl;

import com.salesa.dao.ImageDao;
import com.salesa.dao.mapper.ImageMapper;
import com.salesa.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ImageJdbcDao implements ImageDao {

    @Autowired
    private String getAllImages;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Image> getAll() {
        return jdbcTemplate.query(getAllImages, new ImageMapper());
    }
}
