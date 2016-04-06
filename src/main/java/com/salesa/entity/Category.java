package com.salesa.entity;


public class Category {
    private int id;
    private String name;
   // private Category parent;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    /* public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }*/

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parentId +
                '}';
    }
}
