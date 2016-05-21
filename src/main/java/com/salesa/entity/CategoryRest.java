package com.salesa.entity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;

@JsonAutoDetect
public class CategoryRest {
    private Integer id;
    private String name;
    private String parent;
    private List<HashMap<String, String>> subcategories;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<HashMap<String, String>> getSubcategories() {
        return subcategories;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setSubcategories(List<HashMap<String, String>> subcategories) {
        this.subcategories = subcategories;
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
        return "CategoryRest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", subcategories=" + subcategories +
                '}';
    }
}
