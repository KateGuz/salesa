package com.salesa.dao.util;

import java.util.Map;

public class QueryAndParams {
    public final String query;
    public final Map<String, Object> params;

    public QueryAndParams(String query, Map<String, Object> params) {
        this.query = query;
        this.params = params;
    }

    @Override
    public String toString() {
        return "QueryAndParams{" +
                "query='" + query + '\'' +
                ", params=" + params +
                '}';
    }
}
