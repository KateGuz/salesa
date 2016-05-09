package com.salesa.util;

import com.salesa.entity.Advert;
import com.salesa.util.entity.AdvertPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;

@Component
public class AdvertsParser {
    @Autowired
    private JsonMapper jsonMapper;

    private HashMap<String, Object> prepareJsonRootMap(AdvertPageData advertPageData, int page) {
        List<Advert> adverts = advertPageData.getAdverts();
        HashMap<String, Object> jsonRootMap = new HashMap<>();
        jsonRootMap.put("page", page);

        for (int i = 0; i < adverts.size(); i++) {
            Advert advert = adverts.get(i);
            HashMap<String, Object> map = new HashMap<>();

//            List<Image> images = advert.getImages();
            map.put("category", "/category/" + advert.getCategory().getId());
            map.put("user", "/user/" + advert.getUser().getId());
//            map.put("images", images);
            map.put("id", advert.getId());
            map.put("title", advert.getTitle());
            map.put("text", advert.getText());
            map.put("date", advert.getModificationDate());
            map.put("price", advert.getPrice());
            map.put("currency", advert.getCurrency());
            map.put("status", advert.getStatus());
            jsonRootMap.put(String.valueOf(i + 1), map);
        }
        return jsonRootMap;
    }


    public String toXML(AdvertPageData advertPageData, int page) {
        Object prepare = prepareJsonRootMap(advertPageData, page);
        return jsonMapper.toXML(prepare);
    }

    public String toJSON(AdvertPageData advertPageData, int page) {
        Object prepare = prepareJsonRootMap(advertPageData, page);
        return jsonMapper.toJSON(prepare);

    }

    /*public String toXML(List<Advert> adverts) {
        adverts =
        return jsonMapper.toXML();
    }*/
}