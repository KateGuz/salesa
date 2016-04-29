package com.salesa.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class JsonMapper {
    public String toXML(Object object) {
        try {
            ObjectMapper xml = new XmlMapper();
            xml.registerModule(new JavaTimeModule());
            xml.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            return xml.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSON(Object object) {
        try {
            ObjectMapper json = new ObjectMapper();
            json.registerModule(new JavaTimeModule());
            json.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            return json.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
