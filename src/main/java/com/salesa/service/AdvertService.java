package com.salesa.service;

import com.salesa.filter.AdvertFilter;
import com.salesa.util.AdvertDetails;
import com.salesa.util.AdvertPageData;

public interface AdvertService {
    AdvertPageData get(AdvertFilter advertFilter);
    AdvertDetails get(int advertId);
}
