package com.salesa.util.entity;
import com.salesa.entity.Advert;
import com.salesa.entity.AdvertRest;
import java.util.List;

public class AdvertPageData {

    private List<Advert> adverts;

    private List<AdvertRest> advertRests;
    private int pageCount;

    public List<AdvertRest> getAdvertRests() {
        return advertRests;
    }

    public void setAdvertRests(List<AdvertRest> advertRests) {
        this.advertRests = advertRests;
    }

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
}
