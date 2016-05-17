package com.salesa.dao;

import com.salesa.entity.Advert;
import com.salesa.entity.Image;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.entity.AdvertPageData;

import java.util.List;

public interface AdvertDao {
    AdvertPageData get(AdvertFilter advertFilter);
    Advert get(int advertId);
    List<Advert> getByUserId(int userId);
    int saveAdvert(Advert advert);
    void update(Advert advert);
}
