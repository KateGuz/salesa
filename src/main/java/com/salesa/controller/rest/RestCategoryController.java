package com.salesa.controller.rest;

import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.util.mapper.AdvertParser;
import com.salesa.util.entity.AdvertPageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/category/{categoryId}")
public class RestCategoryController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;
    @Autowired
    private AdvertParser advertParser;

    @RequestMapping(headers = {"Accept=application/json;charset=UTF-8"})
    public String advertsByCategoryJson(@PathVariable("categoryId") int categoryId, @RequestParam(name = "page", defaultValue = "1") int page) {
        log.info("Query adverts(JSON) information for category with id: " + categoryId);
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setCategoryId(categoryId);
        AdvertPageData advertPageData = advertService.get(advertFilter);

        return advertParser.toJSON(advertPageData, page);
    }

    /*@RequestMapping(headers = {"Accept=application/xml;charset=UTF-8"})
    public String advertsByCategoryXml(@PathVariable("categoryId") int categoryId, @RequestParam(name = "page", defaultValue = "1") int page) {
        log.info("Query adverts(XML) information for category with id: " + categoryId);
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setCategoryId(categoryId);
        AdvertPageData advertPageData = advertService.get(advertFilter);

        return advertsParser.toXML(advertPageData, page);
    }*/

}
