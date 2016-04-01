package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

public class AdvertMapper implements RowMapper<Advert> {

    @Override
    public Advert mapRow(ResultSet resultSet, int i) throws SQLException {
        Advert advert = new Advert();
        Category category = new Category();
        User user = new User();
        try {
            advert.setId(resultSet.getInt("id"));
            advert.setTitle(resultSet.getString("title"));
            advert.setText(resultSet.getString("text"));
            Timestamp timestamp = resultSet.getTimestamp("date");
            int year = timestamp.toLocalDateTime().getYear();
            Month month = timestamp.toLocalDateTime().getMonth();
            int day = timestamp.toLocalDateTime().getDayOfMonth();
            int hours = timestamp.toLocalDateTime().getHour();
            int minutes = timestamp.toLocalDateTime().getMinute();
            int seconds = timestamp.toLocalDateTime().getSecond();
            advert.setModificationDate(LocalDateTime.of(year, month, day, hours, minutes, seconds));
            category.setId(resultSet.getInt("categoryId"));
            advert.setPrice(resultSet.getDouble("price"));
            advert.setCurrency(resultSet.getString("currency"));
            user.setId(resultSet.getInt("userId"));
            advert.setStatus(resultSet.getString("status"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advert;
    }
}
