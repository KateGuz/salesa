package com.salesa.dao;

import com.salesa.filter.AdvertFilter;
import com.salesa.util.AdvertPageData;

public interface AdvertDao {
    AdvertPageData get(AdvertFilter advertFilter);
}
