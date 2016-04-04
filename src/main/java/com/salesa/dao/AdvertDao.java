package com.salesa.dao;

import com.salesa.UserFilter;

import java.util.List;

public interface AdvertDao<E> {
    List<E> get(UserFilter userFilter);
}
