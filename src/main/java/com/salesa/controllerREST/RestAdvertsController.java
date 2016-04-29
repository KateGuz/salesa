package com.salesa.controllerREST;

import com.salesa.entity.AdvertRest;
import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.util.AdvertPageData;
import com.salesa.util.AdvetsParcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestAdvertsController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;
    @Autowired
    private AdvetsParcer advetsParcer;

    @RequestMapping(value = "/api/adverts", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String advertsRest(@RequestParam(name = "page", defaultValue = "1") int page, @RequestHeader("accept") String header) {

        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(page);
        log.info("Query get adverts from REST api");
        AdvertPageData advertPageData = advertService.getAll(advertFilter);

        if (advertPageData.getAdvertRests().size() == 0) {
            return "There are not adverts on this page.";
        }
        if (header.contains("/json")) {
            return advetsParcer.toJSON(advertPageData, page);
        }
        if (header.contains("/xml")) {
            return advetsParcer.toXML(advertPageData, page);
        } else {
            List<AdvertRest> advertRests = advertPageData.getAdvertRests();
            StringBuilder stringBuilder = new StringBuilder();
            for (AdvertRest advert : advertRests) {
                String toString = advert.toString();
                stringBuilder.append(toString);
                stringBuilder.append("\n\n\n\n");
            }
            return stringBuilder.toString();
        }
    }
}
