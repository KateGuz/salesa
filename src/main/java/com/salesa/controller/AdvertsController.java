package com.salesa.controller;

import com.salesa.UserFilter;
import com.salesa.dao.CategoryDao;
import com.salesa.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdvertsController {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private AdvertService advertService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("adverts", advertService.get());
        return "home";
    }

    @RequestMapping(value = "/category/{categoryId}")
    public String category(@PathVariable("categoryId") int categoryId, Model model) {
        UserFilter userFilter = new UserFilter();
        userFilter.setCategoryId(categoryId);

        model.addAttribute("adverts", advertService.get(userFilter));
        model.addAttribute("categories", categoryDao.getAll());
        return "home";
    }
}
