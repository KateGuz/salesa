package com.salesa.dao;

import com.salesa.dao.filter.UserFilter;
import com.salesa.dao.mapper.AdvertMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdvertJdbcDaoTest {
    @Mock
    private UserFilter userFilter;
    @Mock
    private JdbcTemplate jdbcTemplate;
    private static final String GET_ALL_SQL = "SELECT * FROM advert;";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGet() throws Exception {
        List expected = new ArrayList<>();
        assertNotNull(userFilter);
        assertEquals(expected, jdbcTemplate.query(GET_ALL_SQL, new AdvertMapper()));
    }
}