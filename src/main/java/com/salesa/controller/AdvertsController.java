package com.salesa.controller;


import com.salesa.util.CurrencyConverter;
import com.salesa.entity.Advert;
import com.salesa.filter.AdvertFilter;
import com.salesa.service.AdvertService;
import com.salesa.service.CategoryService;
import com.salesa.util.AdvertPageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
public class AdvertsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CurrencyConverter currencyConverter;


    @RequestMapping("/")
    public String home(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(required = false) String currency,
                       Model model, HttpSession session) throws IOException {
        if(currency == null && session.getAttribute("selectedCurrency") == null) {
            currency = "UAH";
        }
        if(currency == null && session.getAttribute("selectedCurrency") != null) {
            currency = (String) session.getAttribute("selectedCurrency");
        }
        model.addAttribute("categories", categoryService.getAll());
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(page);
        AdvertPageData advertPageData = advertService.get(advertFilter);

        for (Advert advert : advertPageData.getAdverts()) {
            currencyConverter.updatePriceAndCurrency(advert, currency);
        }

        model.addAttribute("pageData", advertPageData);
        model.addAttribute("activePage", page);
        model.addAttribute("selectedCurrency", currency);
        session.setAttribute("selectedCurrency", currency);
        return "home";
    }

    @RequestMapping("/category/{categoryId}")
    public String category(@PathVariable("categoryId") int categoryId, @RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        log.info("Query adverts information for category with id: " + categoryId);
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(page);
        advertFilter.setCategoryId(categoryId);
        AdvertPageData advertPageData = advertService.get(advertFilter);
        model.addAttribute("pageData", advertPageData);
        model.addAttribute("categories", categoryService.getAll());
        return "home";
    }
}
