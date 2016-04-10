package com.salesa.dao.util;

import com.salesa.dao.mapper.AdvertDetailsMapper;
import com.salesa.util.AdvertDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertDetailsTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    String test = "select advert.id, advert.title, advert.price, advert.currency, advert.status, user.name,user.email, user.phone from advert inner join user on user.id = advert.userId where advert.id = ";

    public AdvertDetails getAdvert(int advertId){
        String query = test + advertId + ";";
        return jdbcTemplate.queryForObject(query, new AdvertDetailsMapper());
    }

}
