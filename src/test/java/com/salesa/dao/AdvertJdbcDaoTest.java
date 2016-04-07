//package com.salesa.dao;
//
//import com.salesa.filter.AdvertFilter;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//public class AdvertJdbcDaoTest {
//
//    @InjectMocks
//    private AdvertJdbcDao advertJdbcDao = new AdvertJdbcDao();
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    public void testGenerateQueryParameters() {
//
//        //prepare
//        AdvertFilter filterOne = new AdvertFilter();
//        filterOne.setPage(1);
//        AdvertFilter filterTwo = new AdvertFilter();
//        filterTwo.setPage(3);
//
//        //when
//        Map<String, Integer> namedParametersOne = advertJdbcDao.generateQueryParameters(filterOne);
//        Map<String, Integer> namedParametersTwo = advertJdbcDao.generateQueryParameters(filterTwo);
//
//        //then
//        assertEquals(0, (int)namedParametersOne.get("startPosition"));
//        assertEquals(18, (int)namedParametersTwo.get("startPosition"));
//
//    }
//}