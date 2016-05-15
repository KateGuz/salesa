package com.salesa.service;

import com.salesa.entity.Advert;
import com.salesa.entity.Image;
import com.salesa.filter.AdvertFilter;
import com.salesa.util.entity.AdvertPageData;

import java.util.List;

public interface AdvertService {
    AdvertPageData get(AdvertFilter advertFilter);
    Advert get(int advertId);
    List<Advert> getByUserId(int userId);
    int saveAdvert(Advert advert);
    void saveAdvertImage(Image image, int advertId);
    Image getAdvertImage(int advertId);
    Image getAdvertImageById(int imageId);
    void update(Advert advert);
}
