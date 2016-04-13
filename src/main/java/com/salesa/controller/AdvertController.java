package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.service.AdvertService;
import com.salesa.service.cache.CategoryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/advert/{advertId}")
    public String advert(@PathVariable("advertId") int advertId, Model model) {
        log.info("Query get advert by id: " + advertId);
        Advert advert = advertService.get(advertId);
        Category category = categoryCache.getCategoryById(advert.getCategory().getId());
        List<Category> breadcrumbsTree = new ArrayList<>();
        do {
            breadcrumbsTree.add(category);
        } while ((category = category.getParent()) != null);

        Collections.reverse(breadcrumbsTree);
        model.addAttribute("advert", advert);
        model.addAttribute("breadcrumbsTree", breadcrumbsTree);
        return "advert";
    }
}
