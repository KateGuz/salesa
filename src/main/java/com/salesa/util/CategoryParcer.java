package com.salesa.util;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.salesa.entity.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CategoryParcer {
    public Object prepare(Category category) {
        CategoryPreview preview = new CategoryPreview();
        Category categoryParent = category.getParent();

        preview.setId(category.getId());
        preview.setName(category.getName());

        if (category.getChildren() != null) {
            List<Category> children = category.getChildren();
            List<HashMap<String, String>> childrens = new ArrayList<>();

            for (Category child : children) {
                HashMap<String, String> childrensNames = new HashMap<>();
                childrensNames.put("id", String.valueOf(child.getId()));
                childrensNames.put("name", child.getName());
                childrens.add(childrensNames);
            }
            preview.setChildrens(childrens);
        }
        if (categoryParent != null) {
            HashMap<String, String> parent = new HashMap<>();
            parent.put("id", String.valueOf(categoryParent.getId()));
            parent.put("name", categoryParent.getName());
            preview.setParent(parent);
        }
        return preview;
    }

    public String toXML(Category category) {
        try {
            Object prepare = prepare(category);

            ObjectMapper xml = new XmlMapper();
            return xml.writeValueAsString(prepare);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSON(Category category) {
        try {
            Object prepare = prepare(category);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(prepare);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonAutoDetect
    private class CategoryPreview {
        private Integer id;
        private String name;
        private HashMap<String, String> parent;
        private List<HashMap<String, String>> childrens;

        public HashMap<String, String> getParent() {
            return parent;
        }

        public void setParent(HashMap<String, String> parent) {
            this.parent = parent;
        }


        public List<HashMap<String, String>> getChildrens() {
            return childrens;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public void setChildrens(List<HashMap<String, String>> childrens) {
            this.childrens = childrens;
        }


        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }


        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "CategoryPreview{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", parent=" + parent +
                    ", childrens=" + childrens +
                    '}';
        }
    }
}
