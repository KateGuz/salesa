package com.salesa.service;

import com.salesa.entity.Advert;
import com.salesa.entity.AdvertRest;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.entity.AdvertPageData;

import java.util.List;

public interface AdvertService {
    AdvertPageData get(AdvertFilter advertFilter);
    Advert get(int advertId);
    List<Advert> getByUserId(int userId);
    AdvertPageData getAll(AdvertFilter advertFilter);
    int saveAdvert(Advert advert);
    void update(AdvertRest advert);
    void update(Advert advert);
}
