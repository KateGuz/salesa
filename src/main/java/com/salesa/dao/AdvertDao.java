package com.salesa.dao;

import com.salesa.dao.filter.UserFilter;
import com.salesa.entity.Advert;

import java.util.List;

public interface AdvertDao<E> {
    List<Advert> get(UserFilter userFilter);
}
