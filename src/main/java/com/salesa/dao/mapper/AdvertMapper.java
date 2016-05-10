package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertMapper implements RowMapper<Advert> {

    @Override
    public Advert mapRow(ResultSet resultSet, int i) throws SQLException {
        Advert advert = new Advert();

        advert.setId(resultSet.getInt("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setText(resultSet.getString("text"));
        advert.setModificationDate(resultSet.getTimestamp("modificationDate").toLocalDateTime());
        advert.setPrice(resultSet.getDouble("price"));
        advert.setCurrency(resultSet.getString("currency"));
        advert.setStatus(resultSet.getString("status"));
        advert.setCategory(new Category(resultSet.getInt("categoryId")));
        advert.setUser(new User(resultSet.getInt("userId")));

        return advert;
    }
}
