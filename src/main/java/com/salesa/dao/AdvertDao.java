package com.salesa.dao;

import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.AdvertPageData;

import java.util.List;

public interface AdvertDao {
    AdvertPageData get(AdvertFilter advertFilter);
    AdvertPageData getActiveAdverts(AdvertFilter advertFilter);
    AdvertPageData getFilteredAdvertsByPrice(AdvertFilter advertFilter);
    AdvertPageData getFilteredAdvertsByPriceDesc(AdvertFilter advertFilter);
    Advert get(int advertId);
    List<Advert> getByUserId(int userId);
}
