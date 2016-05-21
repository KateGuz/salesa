package com.salesa.dao.util;

import com.salesa.filter.AdvertFilter;
import org.junit.Test;

import static org.junit.Assert.*;


public class QueryGeneratorTest {

    private QueryGenerator queryGenerator = new QueryGenerator();

    @Test
    public void testGenerateAdvertQueryForCategoryAndPageFilter() {
        //prepare
        AdvertFilter filter = new AdvertFilter();
        filter.setCategoryId(1);
        filter.setPage(2);
        queryGenerator.setGetAdvertsTemplateSQL("Template");
        queryGenerator.setAddPagingTemplateSQL(" Page Template");

        //when
        QueryAndParams queryAndParams = queryGenerator.generateAdvertQuery(filter);

        //then
        assertEquals("Template WHERE categoryId = :categoryId Page Template;", queryAndParams.query);
        assertEquals(1, queryAndParams.params.get("categoryId"));
        assertEquals(9, queryAndParams.params.get("startPosition"));
        assertEquals(9, queryAndParams.params.get("dataAmount"));
    }

    @Test
    public void testGenerateAdvertQueryForPageFilter() {
        //prepare
        AdvertFilter filter = new AdvertFilter();
        filter.setPage(3);
        queryGenerator.setGetAdvertsTemplateSQL("Template");
        queryGenerator.setAddPagingTemplateSQL(" Page Template");

        //when
        QueryAndParams queryAndParams = queryGenerator.generateAdvertQuery(filter);

        //then
        assertEquals("Template Page Template;", queryAndParams.query);
        assertEquals(18, queryAndParams.params.get("startPosition"));
        assertEquals(9, queryAndParams.params.get("dataAmount"));
    }


    @Test
    public void testGenerateAdvertQueryWithAllFilters() {
        //prepare
        AdvertFilter filter = new AdvertFilter();
        filter.setPage(3);
        filter.setActive(true);
        filter.setSortPriceAsc(false);

        queryGenerator.setGetAdvertsTemplateSQL("Template");
        queryGenerator.setAddPagingTemplateSQL(" Page Template");

        //when
        QueryAndParams queryAndParams = queryGenerator.generateAdvertQuery(filter);

        //then
        System.out.println(queryAndParams.query);
    }

}
