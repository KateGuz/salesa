package com.salesa.util;

import com.salesa.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CategoryParser {
    @Autowired
    private JsonMapper jsonMapper;

    private Object prepare(Category category) {
        Category preview = new Category();
        Category categoryParent = category.getParent();

        preview.setId(category.getId());
        preview.setName(category.getName());

        if (category.getChildren() != null) {
            List<Category> children = category.getChildren();
            List<HashMap<String, String>> childrens = new ArrayList<>();

            for (Category child : children) {
                HashMap<String, String> childrensNames = new HashMap<>();
                childrensNames.put("subcategory", "/category/" + String.valueOf(child.getId()));
                childrens.add(childrensNames);
            }
            //preview.setSubcategories(childrens);
        }
        if (categoryParent != null) {
           // preview.setParent("/category/" + String.valueOf(categoryParent.getId()));
        } else {
           // preview.setParent("No parent");
        }
        return preview;
    }

    public String toXML(Category category) {
        Object prepare = prepare(category);
        return jsonMapper.toXML(prepare);
    }

    public String toJSON(Category category) {
        Object prepare = prepare(category);
        return jsonMapper.toJSON(prepare);
    }
}
