package com.salesa.util;

import com.salesa.entity.Feedback;
import com.salesa.util.mapper.RestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class FeedbackParser {
    @Autowired
    private RestMapper restMapper;
    public String toXML(Feedback feedback){
        return restMapper.toXML(feedback);
    }

    public String toJSON(Feedback feedback){
        return restMapper.toJSON(feedback);
    }
    public String toJSON(List<Feedback> feedbacks){
        HashMap<String, Object> hm = new HashMap<>();
        for(Feedback feedback : feedbacks){
            hm.put("text", feedback.getText());
        }
        return restMapper.toJSON(hm);
    }
}
