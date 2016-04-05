package com.salesa.controller;

import com.salesa.dao.filter.UserFilter;
import com.salesa.entity.Advert;
import com.salesa.service.AdvertService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class AdvertsControllerTest {

    @Mock
    private AdvertService advertService;
    @Mock
    private UserFilter userFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCategory() throws Exception {
        List<Advert> adverts = new ArrayList<>();
        adverts.add(new Advert());
        adverts.add(new Advert());
        adverts.add(new Advert());

        when(advertService.get(userFilter)).thenReturn(adverts);
    }
}