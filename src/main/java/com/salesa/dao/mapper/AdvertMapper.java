package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertMapper implements RowMapper<Advert> {

    @Override
    public Advert mapRow(ResultSet resultSet, int i) throws SQLException {
        Advert advert = new Advert();
        try {
            advert.setId(resultSet.getInt("id"));
            advert.setTitle(resultSet.getString("title"));
            advert.setText(resultSet.getString("text"));
            // todo: map date
            advert.setCategoryId(resultSet.getInt("categoryId"));
            advert.setPrice(resultSet.getDouble("price"));
            advert.setCurrency(resultSet.getString("currency"));
            advert.setUserId(resultSet.getInt("userId"));
            advert.setStatus(resultSet.getString("status"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advert;
    }
}
