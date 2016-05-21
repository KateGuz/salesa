package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.service.AdvertService;
import com.salesa.service.cache.CategoryCache;
import com.salesa.util.CurrencyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class AdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private CurrencyConverter currencyConverter;

    @RequestMapping("/advert/{advertId}")
    public String advert(@PathVariable("advertId") int advertId, Model model, HttpSession session, @RequestParam(required = false) String currency) {
        if (currency != null) {
            session.setAttribute("selectedCurrency", currency);
        }
        log.info("Query get advert by id {}", advertId);
        Advert advert = advertService.get(advertId);
        String defaultCurrency = (String) session.getAttribute("selectedCurrency");

        currencyConverter.updatePriceAndCurrency(advert, defaultCurrency);

        Category category = categoryCache.getCategoryById(advert.getCategory().getId());
        List<Category> breadcrumbsTree = new ArrayList<>();
        do {
            breadcrumbsTree.add(category);
        } while ((category = category.getParent()) != null);

        Collections.reverse(breadcrumbsTree);
        model.addAttribute("advert", advert);
        model.addAttribute("breadcrumbsTree", breadcrumbsTree);
        model.addAttribute("selectedCurrency", defaultCurrency);
        log.info("Queried advert {}", advert);
        return "advert";
    }
}
