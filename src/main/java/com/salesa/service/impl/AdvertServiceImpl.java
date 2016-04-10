package com.salesa.service.impl;

import com.salesa.dao.AdvertDao;
import com.salesa.dao.AdvertJdbcDao;
import com.salesa.dao.util.AdvertDetailsTest;
import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.util.AdvertDetails;
import com.salesa.util.AdvertPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao advertDao;
    @Autowired
    private AdvertDetailsTest advertDetailsTest;

    @Override
    public AdvertPageData get(AdvertFilter advertFilter) {
        return advertDao.get(advertFilter);
    }

    public AdvertDetails get(int advertId){
        return advertDetailsTest.getAdvert(advertId);
    }
}
