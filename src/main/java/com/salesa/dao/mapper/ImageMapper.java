package com.salesa.dao.mapper;

import com.salesa.entity.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultset, int i) throws SQLException {
        Image image = new Image();

        image.setId(resultset.getInt("id"));
        image.setContent(resultset.getBytes("picture"));
        image.setType(resultset.getString("type"));

        return image;
    }
}
