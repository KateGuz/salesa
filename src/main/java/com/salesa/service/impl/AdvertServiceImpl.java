package com.salesa.service.impl;

import com.salesa.dao.AdvertDao;
import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.service.cache.CategoryCache;
import com.salesa.util.AdvertPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao advertDao;
    
    @Override
    public AdvertPageData get(AdvertFilter advertFilter) {
        return advertDao.get(advertFilter);
    }

    @Override
    public AdvertPageData getActive(AdvertFilter advertFilter) {
        return advertDao.getActiveAdverts(advertFilter);
    }

    @Override
    public AdvertPageData getByLowestPrice(AdvertFilter advertFilter) {
        return advertDao.getFilteredAdvertsByPrice(advertFilter);
    }

    @Override
    public Advert get(int advertId){
        return advertDao.get(advertId);
    }

    @Override
    public List<Advert> getByUserId(int userId){
        return advertDao.getByUserId(userId);
    }
}
