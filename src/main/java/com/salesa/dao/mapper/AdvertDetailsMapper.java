package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.User;
import com.salesa.util.AdvertDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvertDetailsMapper implements RowMapper<AdvertDetails>{

    @Override
    public AdvertDetails mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AdvertDetails advertDetails = new AdvertDetails();
        Advert advert = new Advert();
        advert.setId(resultSet.getInt("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setPrice(resultSet.getDouble("price"));
        advert.setCurrency(resultSet.getString("currency"));
        advert.setStatus(resultSet.getString("status"));
        advertDetails.setAdvert(advert);
        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setPhone(resultSet.getString("phone"));
        user.setEmail(resultSet.getString("email"));
        advertDetails.setUser(user);

        return advertDetails;
    }
}
