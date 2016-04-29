package com.salesa.util;

import com.salesa.entity.AdvertRest;
import com.salesa.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class AdvetsParcer {
    @Autowired
    private JsonMapper jsonMapper;

    private Object prepare(AdvertPageData advertPageData, int page) {
        List<AdvertRest> advertRests = advertPageData.getAdvertRests();
        HashMap<String, Object> rootJSON = new HashMap<>();
        rootJSON.put("page", page);

        for (int i = 0; i < advertRests.size(); i++) {
            AdvertRest advert = advertRests.get(i);
            HashMap<String, Object> map = new HashMap<>();

            List<Image> images = advert.getImages();
            map.put("category", "/category/" + advert.getCategory());
            map.put("user", "/user/" + advert.getUser());
            map.put("images", images);
            map.put("id", advert.getId());
            map.put("title", advert.getTitle());
            map.put("text", advert.getText());
            map.put("date", advert.getModificationDate());
            map.put("price", advert.getPrice());
            map.put("currency", advert.getCurrency());
            map.put("status", advert.getStatus());
            rootJSON.put(String.valueOf(i + 1), map);
        }
        return rootJSON;
    }


    public String toXML(AdvertPageData advertPageData, int page) {
        Object prepare = prepare(advertPageData, page);
        return jsonMapper.toXML(prepare);
    }

    public String toJSON(AdvertPageData advertPageData, int page) {
        Object prepare = prepare(advertPageData, page);
        return jsonMapper.toJSON(prepare);

    }
}
