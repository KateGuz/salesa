package com.salesa.filter;

public class AdvertFilter {
    private String searchText;
    private int page;
    private int categoryId;
    private boolean active;
    private Boolean sortPriceAsc;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boolean isSortPriceAsc() {
        return sortPriceAsc;
    }

    public void setSortPriceAsc(Boolean sortPriceAsc) {
        this.sortPriceAsc = sortPriceAsc;
    }

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

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Boolean getSortPriceAsc() {
        return sortPriceAsc;
    }

    @Override
    public String toString() {
        return "AdvertFilter{" +
                "searchText='" + searchText + '\'' +
                ", page=" + page +
                ", categoryId=" + categoryId +
                ", active=" + active +
                ", sortPriceAsc=" + sortPriceAsc +
                '}';
    }
}
