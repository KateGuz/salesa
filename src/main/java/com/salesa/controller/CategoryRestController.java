package com.salesa.controller;

import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.service.UserService;
import com.salesa.service.cache.CategoryCache;
import com.salesa.util.CategoryParcer;
import com.salesa.util.UserParcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryRestController {
    @Autowired
    private CategoryCache categoryCache;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryParcer categoryParcer;
    @Autowired
    private UserParcer userParcer;


    @RequestMapping(value = "/api/category/{id}", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String getCategory(@PathVariable("id") int id, @RequestHeader("accept") String header) {
        Category categoryById = categoryCache.getCategoryById(id);
        if (header.contains("/json")) {
            return categoryParcer.toJSON(categoryById);
        }
        if (header.contains("/xml")) {
            return categoryParcer.toXML(categoryById);
        } else {
            return categoryById.toString();
        }
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String userInfoJSON(@PathVariable("id") int id, @RequestHeader("accept") String header) {
        User user = userService.get(id);
        user.setPassword(null);

        if (header.contains("/json")) {
            return userParcer.toJSON(user);
        }
        if (header.contains("/xml")) {
            return userParcer.toXML(user);
        } else {
            return user.toString();
        }
    }
}
