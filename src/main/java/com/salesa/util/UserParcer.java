package com.salesa.util;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.salesa.entity.User;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class UserParcer {

    public String toXML(User user) {
        try {
            ObjectMapper xml = new XmlMapper();
            return xml.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSON(User user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonAutoDetect
    private class UserPreview extends User {
        @JsonIgnore
        @Override
        public InputStream getAvatar() {
            return super.getAvatar();
        }
    }
}
