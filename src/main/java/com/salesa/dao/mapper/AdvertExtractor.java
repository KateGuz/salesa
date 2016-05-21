package com.salesa.dao.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.Image;
import com.salesa.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertExtractor implements ResultSetExtractor<List<Advert>> {

    @Override
    public List<Advert> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Advert> adverts = new ArrayList<>();


        while (resultSet.next()) {
            Image image = new Image();
            boolean flag = true;
            int id = resultSet.getInt("imageId");
            if (id > 0) {
                image.setId(resultSet.getInt("imageId"));
                image.setType(resultSet.getString("type"));
                for (Advert advert : adverts) {
                    if (advert.getId() == resultSet.getInt("id")) {
                        advert.getImages().add(image);
                        flag = false;
                    }
                }
            }
            if (flag) {
                Advert advert = new Advert();
                advert.setId(resultSet.getInt("id"));
                advert.setTitle(resultSet.getString("title"));
                advert.setText(resultSet.getString("text"));
                advert.setCategory(new Category(resultSet.getInt("categoryId")));
                advert.setUser(new User(resultSet.getInt("userId")));
                advert.setModificationDate(resultSet.getTimestamp("modificationDate").toLocalDateTime());
                advert.setPrice(resultSet.getDouble("price"));
                advert.setCurrency(resultSet.getString("currency"));
                advert.setStatus(resultSet.getString("status"));
                if (id > 0) {
                    advert.setImages(new ArrayList<>());
                    advert.getImages().add(image);
                }
                adverts.add(advert);
            }
        }
        return adverts;
    }
}
