package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.service.cache.CategoryCache;
import com.salesa.util.CurrencyConverter;
import com.salesa.util.entity.AdvertPageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class SearchController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CategoryCache categoryCache;
    @Autowired
    private CurrencyConverter currencyConverter;
    @Autowired
    private AdvertService advertService;

    @RequestMapping("/search")
    public String search(@RequestParam("searchText") String searchText, Model model,
                         @RequestParam(name = "page", defaultValue = "1") int page,
                         @RequestParam(required = false) String currency,
                         @RequestParam(defaultValue = "false") String isActiveParam,
                         @RequestParam(required = false) Boolean isSortPriceAsc, HttpSession session) throws IOException {
        if (currency == null && session.getAttribute("selectedCurrency") == null) {
            currency = "UAH";
        }
        if (currency == null && session.getAttribute("selectedCurrency") != null) {
            currency = (String) session.getAttribute("selectedCurrency");
        }
        log.info("SEARCH FOR {}", searchText);
        AdvertFilter advertFilter = createAdvertFilter(page, isActiveParam, isSortPriceAsc, searchText);
        AdvertPageData advertPageData = advertService.search(advertFilter);
        for (Advert advert : advertPageData.getAdverts()) {
            currencyConverter.updatePriceAndCurrency(advert, currency);
        }

        model.addAttribute("searchText", searchText);
        model.addAttribute("pageData", advertPageData);
        model.addAttribute("activePage", page);
        model.addAttribute("selectedCurrency", currency);
        model.addAttribute("filterUrl", createAdvertFilterUrl(advertFilter));
        session.setAttribute("selectedCurrency", currency);
        model.addAttribute("categories", categoryCache.getCategoryTree());
        return "search";
    }

    private AdvertFilter createAdvertFilter(int page, String isActiveParam, Boolean isSortPriceAsc, String text) {
        Boolean isActive = Boolean.valueOf(isActiveParam);
        AdvertFilter filter = new AdvertFilter();
        if (isSortPriceAsc != null) {
            filter.setSortPriceAsc(isSortPriceAsc);
        }
        filter.setSearchText(text);
        filter.setActive(isActive);
        filter.setPage(page);
        return filter;
    }

    private String createAdvertFilterUrl(AdvertFilter advertFilter) {
        StringBuilder builder = new StringBuilder();
        if (advertFilter.isSortPriceAsc() != null) {
            builder.append("&isSortPriceAsc=");
            builder.append(advertFilter.isSortPriceAsc());
        }
        if (advertFilter.isActive()) {
            builder.append("&isActiveParam=true");
        }
        return builder.toString();
    }
}
