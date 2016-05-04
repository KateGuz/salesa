package com.salesa.util;

import com.salesa.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class FeedbackParser {
    @Autowired
    private JsonMapper jsonMapper;
    public String toXML(Feedback feedback){
        return jsonMapper.toXML(feedback);
    }

    public String toJSON(Feedback feedback){
        return jsonMapper.toJSON(feedback);
    }
    public String toJSON(List<Feedback> feedbacks){
        HashMap<String, Object> hm = new HashMap<>();
        for(Feedback feedback : feedbacks){
            hm.put("text", feedback.getText());
        }
        return jsonMapper.toJSON(hm);
    }
}
