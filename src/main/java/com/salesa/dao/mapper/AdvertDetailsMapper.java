package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertDetailsMapper implements RowMapper<Advert>{

    @Override
    public Advert mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Advert advert = new Advert();
        advert.setId(resultSet.getInt("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setText(resultSet.getString("text"));
        advert.setModificationDate(resultSet.getTimestamp("modificationDate").toLocalDateTime());
        advert.setPrice(resultSet.getDouble("price"));
        advert.setCurrency(resultSet.getString("currency"));
        advert.setStatus(resultSet.getString("status"));

        User user = new User(resultSet.getInt("userId"));
        user.setName(resultSet.getString("name"));
        user.setPhone(resultSet.getString("phone"));
        user.setEmail(resultSet.getString("email"));
        advert.setUser(user);

        Category category = new Category();

        /*category.setId(resultSet.getInt("categoryId"));*/
        category.setName(resultSet.getString("name"));
        System.out.println(category.getName());
        advert.setCategory(category);
        return advert;
    }

}
