package com.salesa.util;

import com.salesa.entity.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertParser {
    @Autowired
    private JsonMapper jsonMapper;

    public String toXML(Advert advert) {
        return jsonMapper.toXML(advert);
    }

    public String toJSON(Advert advert) {
        return jsonMapper.toJSON(advert);
    }
}
