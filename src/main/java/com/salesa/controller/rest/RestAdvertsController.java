package com.salesa.controller.rest;

import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.util.entity.AdvertPageData;
import com.salesa.util.AdvertsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/adverts")
public class RestAdvertsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertService advertService;
    @Autowired
    private AdvertsParser advertsParser;

    @RequestMapping(headers = {"Accept=application/json; charset=UTF-8"})
    public String advertsJsonRest(@RequestParam(name = "page", defaultValue = "1") int page) {
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(page);
        log.info("Query adverts (JSON) from REST api for filter {}", advertFilter);
        AdvertPageData advertPageData = advertService.get(advertFilter);

        return advertsParser.toJSON(advertPageData, page);
    }

//    @RequestMapping(headers = {"Accept=application/xml; charset=UTF-8"})
//    public String advertsXmlRest(@RequestParam(name = "page", defaultValue = "1") int page) {
//        AdvertFilter advertFilter = new AdvertFilter();
//        advertFilter.setPage(page);
//        log.info("Query adverts (XML) from REST api for filter {}", advertFilter);
//        AdvertPageData advertPageData = advertService.getAll(advertFilter);
//
//        if (advertPageData.getAdvertRests().size() == 0) {
//            return "There are not adverts on this page.";
//        }
//        return advertsParser.toXML(advertPageData, page);
//    }
}
