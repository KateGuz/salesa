package com.salesa.util.mapper;

import com.salesa.entity.Advert;
import com.salesa.util.entity.AdvertPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertParser {
    @Autowired
    private RestMapper restMapper;

//    public String toXML(AdvertPageData advertPageData, int page) {
//        return jsonMapper.toXML(prepare);
//    }

    public Advert toAdvert(String json) {
        return restMapper.toObject(json, Advert.class);
    }

    public String toJSON(AdvertPageData advertPageData, int page) {
        return restMapper.toJSON(advertPageData.getAdverts());
    }

    public String toJSON(Advert advert) {
        return restMapper.toJSON(advert);
    }
}