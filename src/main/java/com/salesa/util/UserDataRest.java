package com.salesa.util;

import com.salesa.entity.Advert;
import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDataRest {
    @Autowired
    private JsonMapper jsonMapper;
    public String toJSON(User user, List<Feedback> feedbacks, List<Advert> adverts){
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        Map<Integer, Object> advertsMap = new HashMap<>();
        for(int i = 0; i < adverts.size(); i++){
            advertsMap.put(i + 1, adverts.get(i));
        }
        root.put("adverts", advertsMap);
        Map<Integer, Object> feedbackMap = new HashMap<>();
        for(int i = 0; i < feedbacks.size(); i++){
            advertsMap.put(i + 1, feedbacks.get(i));
        }
        root.put("feedbacks", feedbackMap);

        return jsonMapper.toJSON(root);
    }

    public String toXML(User user, List<Feedback> feedbacks, List<Advert> adverts){
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        Map<Integer, Object> advertsMap = new HashMap<>();
        for(Advert advert: adverts){
            advertsMap.put(advert.getId(), advert);
        }
        root.put("adverts", advertsMap);
        Map<Integer, Object> feedbackMap = new HashMap<>();
        for(Feedback feedback: feedbacks){
            feedbackMap.put(feedback.getId(), feedback);
        }
        root.put("feedbacks", feedbackMap);

        return jsonMapper.toXML(root);
    }
}
