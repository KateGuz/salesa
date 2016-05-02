package com.salesa.dao.mapper;

import com.salesa.entity.AdvertRest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertRestMapper implements RowMapper<AdvertRest> {

    @Override
    public AdvertRest mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AdvertRest advert = new AdvertRest();
        advert.setId(resultSet.getInt("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setText(resultSet.getString("text"));
        advert.setModificationDate(resultSet.getTimestamp("modificationDate").toLocalDateTime());
        advert.setPrice(resultSet.getDouble("price"));
        advert.setCurrency(resultSet.getString("currency"));
        advert.setStatus(resultSet.getString("status"));
        advert.setUser(resultSet.getString("userId"));
        advert.setCategory(resultSet.getString("categoryId"));

        return advert;
    }
}
