package com.salesa.util.mapper;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.util.mapper.RestMapper;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RestMapperTest {
    private RestMapper restMapper = new RestMapper();
    private final String EXPECTED_JSON = "[ {\n" +
            "  \"id\" : 1,\n" +
            "  \"title\" : \"title1\",\n" +
            "  \"text\" : \"Text1\",\n" +
            "  \"modificationDate\" : \"2016-08-16T12:15\",\n" +
            "  \"category\" : \"/category/1\",\n" +
            "  \"price\" : 500.0,\n" +
            "  \"currency\" : \"EUR\",\n" +
            "  \"user\" : \"/user/1\",\n" +
            "  \"status\" : \"H\",\n" +
            "  \"images\" : null\n" +
            "}, {\n" +
            "  \"id\" : 2,\n" +
            "  \"title\" : \"title2\",\n" +
            "  \"text\" : \"Text2\",\n" +
            "  \"modificationDate\" : \"2016-08-17T12:15\",\n" +
            "  \"category\" : \"/category/2\",\n" +
            "  \"price\" : 1000.0,\n" +
            "  \"currency\" : \"USD\",\n" +
            "  \"user\" : \"/user/2\",\n" +
            "  \"status\" : \"A\",\n" +
            "  \"images\" : null\n" +
            "} ]";
    @Test
    public void testObjectToJson(){
        //prepare
        List<Advert> adverts = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Advert advert = new Advert();
            advert.setId(i);
            advert.setModificationDate(LocalDateTime.of(2016, Month.AUGUST, 15 + i, 12, 15));
            advert.setPrice(500 * i);
            advert.setText("Text" + i);
            advert.setTitle("title" + i);
            advert.setUser(new User(i));
            advert.setCategory(new Category(i));
            advert.setCurrency(i % 2 == 0 ? "USD" : "EUR");
            advert.setStatus(i % 2 == 0 ? "A" : "H");
            adverts.add(advert);
        }
        //then
        String actual =  restMapper.toJSON(adverts);
        System.out.println(actual);
        //compare
        assertEquals(EXPECTED_JSON, actual);
    }
}