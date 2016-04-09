package com.salesa.filter;

public class AdvertFilter {

    private int page;
    private int categoryId;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "AdvertFilter{" +
                "page=" + page +
                ", categoryId=" + categoryId +
                '}';
    }
}
