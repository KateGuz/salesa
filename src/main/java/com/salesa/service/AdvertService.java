package com.salesa.service;

import com.salesa.dao.filter.UserFilter;
import com.salesa.dao.AdvertJdbcDao;
import com.salesa.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AdvertService {
    @Autowired
    AdvertJdbcDao advertJdbcDao;

    public List<Advert> get(UserFilter userFilter) {
        List<Advert> adverts = advertJdbcDao.get(userFilter);
        List<Advert> result = new ArrayList<>();

        int count = 9;
        if (adverts.size() < 9) {
            count = adverts.size();
        }
        for(int i = 0; i < count; i++){
            result.add(adverts.get(i));
        }
        return result;
    }
}
