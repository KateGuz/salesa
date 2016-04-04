package com.salesa.service;

import com.salesa.UserFilter;
import com.salesa.dao.AdvertJdbcAdvertDao;
import com.salesa.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AdvertService {
    @Autowired
    AdvertJdbcAdvertDao advertJdbcDao;

    public List<Advert> get(UserFilter userFilter) {
        int page = 1;
        if (userFilter.getPage() > 0) {
            page = userFilter.getPage();
        }
        int indexFrom = (page - 1) * 9;
        int indexTo = page * 9;
        List<Advert> pageNumberList = advertJdbcDao.get(userFilter);
        if (indexTo > pageNumberList.size()) {
            indexTo = pageNumberList.size();
        }
        List<Advert> returnList = new ArrayList<>();
        for (int i = indexFrom; i < indexTo; i++) {
            returnList.add(pageNumberList.get(i));
        }
        return returnList;
    }

    public List<Advert> get() {
        return advertJdbcDao.get();
    }

    public void setAdvertJdbcDao(AdvertJdbcAdvertDao advertJdbcDao) {
        this.advertJdbcDao = advertJdbcDao;
    }


}
