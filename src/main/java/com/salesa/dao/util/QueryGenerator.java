package com.salesa.dao.util;


import com.salesa.entity.Category;
import com.salesa.filter.AdvertFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.salesa.service.cache.CategoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salesa.dao.impl.AdvertJdbcDao.MAX_ADVERTS_PER_PAGE;

@Service
public class QueryGenerator {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final char END_SEPARATOR = ';';
    private static final String WHERE_STATEMENT = " WHERE ";
    private static final String AND_STATEMENT = " AND ";
    private static final String ORDER_BY_STATEMENT = " ORDER BY ";
    private static final String DESC_STATEMENT = " DESC ";

    @Autowired
    private String getAdvertsTemplateSQL;

    @Autowired
    private String getUserAdvertsTemplateSQL;

    @Autowired
    private String addPagingTemplateSQL;

    @Autowired
    private String getUserByIdSQL;

    @Autowired
    private CategoryCache categoryCache;

    public void setGetAdvertsTemplateSQL(String getAdvertsTemplateSQL) {
        this.getAdvertsTemplateSQL = getAdvertsTemplateSQL;
    }

    public void setAddPagingTemplateSQL(String addPagingTemplateSQL) {
        this.addPagingTemplateSQL = addPagingTemplateSQL;
    }

    public QueryAndParams generateAdvertQuery(AdvertFilter advertFilter) {
        StringBuilder query = new StringBuilder(getAdvertsTemplateSQL);
        Map<String, Object> params = new HashMap<>();

        // category
        if (advertFilter.getCategoryId() != 0) {
            query.append(AND_STATEMENT);
            addCategoryFiltering(advertFilter.getCategoryId(), query, params);
        }

        // status filter
        if (advertFilter.isActive()) {
            query.append(AND_STATEMENT);
            query.append("a.status = 'A'");
        }

        // sorting
        if (advertFilter.isSortPriceAsc() != null) {
            query.append(ORDER_BY_STATEMENT);
            query.append("a.defaultPriceUAH");
            if (!advertFilter.isSortPriceAsc()) {
                query.append(DESC_STATEMENT);
            }
        }

        // pagination
        addPagination(advertFilter.getPage(), query, params);

        // post processing
        query.append(END_SEPARATOR);
        return new QueryAndParams(query.toString(), params);
    }

    private void addCategoryFiltering(int categoryId, StringBuilder query, Map<String, Object> params) {

        params.put("categoryId", categoryId);
        query.append("categoryId IN (:categoryId");
        Category targetCategory = categoryCache.getCategoryById(categoryId);
        List<Category> children = targetCategory.getChildren();
        if(children != null){
            for (Category child : children) {
                query.append(", " + child.getId());
            }
        }
        query.append(")");
    }

    private void addPagination(int page, StringBuilder query, Map<String, Object> params) {
        query.append(addPagingTemplateSQL);
        int startPosition = MAX_ADVERTS_PER_PAGE * (page - 1);
        params.put("startPosition", startPosition);
        params.put("dataAmount", MAX_ADVERTS_PER_PAGE);
    }

    public QueryAndParams generateAdvertQuery(int advertId) {
        log.info("Start generating query to get advert by id {}", advertId);
        StringBuilder query = new StringBuilder(getUserAdvertsTemplateSQL);
        Map<String, Object> params = new HashMap<>();
        params.put("id", advertId);
        query.append(AND_STATEMENT);
        query.append("a.id = :id");
        query.append(END_SEPARATOR);
        log.info("Query to get advert with id {} was generated successfully", advertId);
        return new QueryAndParams(query.toString(), params);

    }

    public QueryAndParams generateAdvertByUserIdQuery(int userId) {
        StringBuilder query = new StringBuilder(getUserAdvertsTemplateSQL);
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        query.append(AND_STATEMENT);
        query.append("a.userId = :userId");
        query.append(END_SEPARATOR);
        return new QueryAndParams(query.toString(), params);
    }

    public QueryAndParams generateUserByIdQuery(int userId) {
        StringBuilder query = new StringBuilder(getUserByIdSQL);
        Map<String, Object> params = new HashMap<>();
        params.put("u.id", userId);
        query.append(WHERE_STATEMENT);
        query.append("u.id = :u.id");
        query.append(END_SEPARATOR);
        return new QueryAndParams(query.toString(), params);
    }
}
