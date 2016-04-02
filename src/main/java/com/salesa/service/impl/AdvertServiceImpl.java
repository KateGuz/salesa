package com.salesa.service.impl;

import com.salesa.dao.AdvertDao;
import com.salesa.entity.Advert;
import com.salesa.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl implements AdvertService {
    @Autowired
    private AdvertDao advertDao;

    @Override
    public List<Advert> getAll() {
        return advertDao.getAll();
    }
}
