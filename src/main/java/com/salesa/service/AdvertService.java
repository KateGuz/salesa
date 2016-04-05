package com.salesa.service;

import com.salesa.dao.AdvertJdbcDao;
import com.salesa.dao.filter.UserFilter;
import com.salesa.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdvertService {
    @Autowired
    AdvertJdbcDao advertJdbcDao;

    public List<Advert> get(UserFilter userFilter) {
        List<Advert> adverts = advertJdbcDao.get(userFilter);
        return adverts.subList(0, 9);
    }
}
