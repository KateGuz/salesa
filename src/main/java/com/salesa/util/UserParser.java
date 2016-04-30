package com.salesa.util;


import com.salesa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserParser {
    @Autowired
    private JsonMapper jsonMapper;

    public String toXML(User user) {
        return jsonMapper.toXML(user);
    }

    public String toJSON(User user) {
        return jsonMapper.toJSON(user);
    }
}
