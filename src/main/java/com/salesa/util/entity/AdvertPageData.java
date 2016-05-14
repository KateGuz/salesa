package com.salesa.util.entity;
import com.salesa.entity.Advert;

import java.util.List;

public class AdvertPageData {

    private List<Advert> adverts;
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    @Override
    public String toString() {
        return "AdvertPageData{" +
                "adverts=" + adverts +
                ", pageCount=" + pageCount +
                '}';
    }
}