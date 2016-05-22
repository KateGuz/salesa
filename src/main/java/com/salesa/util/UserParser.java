package com.salesa.util;


import com.salesa.entity.User;
import com.salesa.util.mapper.RestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserParser {
    @Autowired
    private RestMapper restMapper;

    public String toXML(User user) {
        return restMapper.toXML(user);
    }

    public String toJSON(User user) {
        return restMapper.toJSON(user);
    }
}
