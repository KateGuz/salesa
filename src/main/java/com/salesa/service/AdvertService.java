package com.salesa.service;

import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.AdvertPageData;

public interface AdvertService {
    AdvertPageData get(AdvertFilter advertFilter);
    Advert get(int advertId);
}
