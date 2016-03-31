package com.salesa.controller;

import com.salesa.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdvertsController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryDao.getAll());
        return "home";
    }

    @RequestMapping("/category/{categoryId}")
    public String category(@PathVariable("categoryId") int categoryId, Model model) {
        return "home";
    }
}
