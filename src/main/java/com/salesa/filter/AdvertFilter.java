package com.salesa.filter;

public class AdvertFilter {

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

    @Override
    public String toString() {
        return "AdvertFilter{" +
                "page=" + page +
                ", categoryId=" + categoryId +
                ", active=" + active +
                ", sortPriceAsc=" + sortPriceAsc +
                '}';
    }
}
