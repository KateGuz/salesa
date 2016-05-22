package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDataMapper implements org.springframework.jdbc.core.RowMapper<Advert> {

    @Override
    public Advert mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Advert advert = new Advert();
        advert.setId(resultSet.getInt("advertId"));
        advert.setTitle(resultSet.getString("advertTitle"));
        advert.setText(resultSet.getString("advertText"));
        advert.setPrice(resultSet.getDouble("advertPrice"));
        advert.setCurrency(resultSet.getString("advertCurrency"));
        advert.setStatus(resultSet.getString("advertStatus"));
        advert.setModificationDate(resultSet.getTimestamp("advertModificationDate").toLocalDateTime());

        User user = new User();
        user.setName(resultSet.getString("userName"));
        user.setEmail(resultSet.getString("userEmail"));
        advert.setUser(user);
        return advert;
    }
}
