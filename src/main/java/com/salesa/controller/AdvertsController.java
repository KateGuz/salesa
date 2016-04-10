package com.salesa.controller;


import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
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

import java.util.List;

@Controller
public class AdvertsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/")
    public String home(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(page);
        AdvertPageData advertPageData = advertService.get(advertFilter);
        model.addAttribute("pageData", advertPageData);
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

    @RequestMapping("/advert")
    public String category(Model model) {
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(1);
        advertFilter.setCategoryId(5);
        AdvertPageData advertPageData = advertService.get(advertFilter);
        List<Advert> adverts = advertPageData.getAdverts();
        Advert advert = adverts.get(5);
        User user = new User();
        user.setName("Linux Torwalds");
        user.setEmail("lin_tor@linux.com");
        user.setPhone("0-800-999-99-99");
        List<Category> all = categoryService.getAll();
        Category category = all.get(5);

        model.addAttribute("pageData", advertPageData);
        model.addAttribute("user", user);
        model.addAttribute("category", category);
        model.addAttribute("advert", advert);
        return "advert";
    }
}
