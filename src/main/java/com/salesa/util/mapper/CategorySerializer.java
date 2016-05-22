package com.salesa.util.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.salesa.entity.Category;

import java.io.IOException;

public class CategorySerializer extends JsonSerializer<Category> {
    @Override
    public void serialize(Category category, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException{
        jsonGenerator.writeObject("/category/" + category.getId());
    }
}
