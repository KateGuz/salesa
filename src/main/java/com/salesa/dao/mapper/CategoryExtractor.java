package com.salesa.dao.mapper;

import com.salesa.entity.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoryExtractor implements ResultSetExtractor<List<Category>> {

    @Override
    public List<Category> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Integer, Category> categoryMap = new HashMap<>();
        while (resultSet.next()) {
            Category parent = new Category();
            Integer parentId = resultSet.getInt("parentId");
            String parentName = resultSet.getString("parentName");
            parent.setId(parentId);
            parent.setName(parentName);

            Category childCategory = new Category();
            childCategory.setId(resultSet.getInt("childId"));
            childCategory.setName(resultSet.getString("childName"));

            if (categoryMap.containsKey(parentId)) {
                Category savedParent = categoryMap.get(parentId);
                savedParent.getChildren().add(childCategory);
            } else {
                categoryMap.put(parentId, parent);
                if (childCategory.getId() != 0) {
                    parent.setChildren(new ArrayList<>());
                    parent.getChildren().add(childCategory);
                }
            }


        }
        return new ArrayList<>(categoryMap.values());
    }
}

