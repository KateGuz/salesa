package com.salesa.util.entity;

import com.salesa.entity.Advert;
import com.salesa.entity.User;

public class AdvertDetails {
    private Advert advert;
    private User user;

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString(){
        return user + " " + advert;
    }
}
