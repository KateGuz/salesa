package com.salesa.util;

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
            "  \"title\" : \"Title 1\",\n" +
            "  \"text\" : \"Text 1\",\n" +
            "  \"modificationDate\" : \"2015-04-02T12:25\",\n" +
            "  \"category\" : \"/category/1\",\n" +
            "  \"price\" : 1000.0,\n" +
            "  \"currency\" : \"USD\",\n" +
            "  \"user\" : \"/user/1\",\n" +
            "  \"status\" : \"S\",\n" +
            "  \"images\" : null\n" +
            "}, {\n" +
            "  \"id\" : 2,\n" +
            "  \"title\" : \"Title 2\",\n" +
            "  \"text\" : \"Text 2\",\n" +
            "  \"modificationDate\" : \"2015-04-03T12:25\",\n" +
            "  \"category\" : \"/category/2\",\n" +
            "  \"price\" : 2000.0,\n" +
            "  \"currency\" : \"UAH\",\n" +
            "  \"user\" : \"/user/2\",\n" +
            "  \"status\" : \"A\",\n" +
            "  \"images\" : null\n" +
            "} ]";


    @Test
    public void testObjectToJson() {
        //prepare
        List<Advert> adverts = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Advert advert = new Advert();
            advert.setId(i);
            advert.setCurrency(i == 1 ? "USD" : "UAH");
            LocalDateTime modificationDate = LocalDateTime.of(2015, Month.APRIL, 1 + i, 12, 25);
            advert.setModificationDate(modificationDate);
            advert.setPrice(1000 * i);
            advert.setText("Text " + i);
            advert.setTitle("Title " + i);
            advert.setStatus(i % 2 == 0 ? "A" : "S");
            advert.setUser(new User(i));
            advert.setCategory(new Category(i));
            adverts.add(advert);
        }

        // then
        String actual = restMapper.toJSON(adverts);

        // compare
        assertEquals(EXPECTED_JSON, actual);
    }


}