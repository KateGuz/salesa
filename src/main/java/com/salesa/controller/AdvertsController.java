package com.salesa.controller;

import com.salesa.service.AdvertService;
import com.salesa.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdvertsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("adverts", advertService.getAll());
        return "home";
    }

    @RequestMapping("/category/{categoryId}")
    public String category(@PathVariable("categoryId") int categoryId, Model model) {
        log.info("Query adverts information for category with id: " + categoryId);
        return "home";
    }
}
