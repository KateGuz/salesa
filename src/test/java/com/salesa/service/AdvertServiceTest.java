package com.salesa.service;

import com.salesa.dao.filter.UserFilter;
import com.salesa.entity.Advert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertNotNull;


public class AdvertServiceTest {
    @Mock
    private AdvertService advertService;
    @InjectMocks
    private UserFilter userFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testGet() throws Exception {
        userFilter = new UserFilter();
        userFilter.setCategoryId(2);

        List<Advert> adverts = advertService.get(userFilter);
        assertNotNull(adverts);
    }
}