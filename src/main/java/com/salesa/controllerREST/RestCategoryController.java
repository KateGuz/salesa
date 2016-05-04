package com.salesa.controllerREST;

import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.service.UserService;
import com.salesa.service.cache.CategoryCache;
import com.salesa.util.CategoryParser;
import com.salesa.util.UserParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestCategoryController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CategoryCache categoryCache;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryParser categoryParser;
    @Autowired
    private UserParser userParser;


    @RequestMapping(value = "/api/category/{id}", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String getCategoryREST(@PathVariable("id") int id, @RequestHeader("accept") String header) {
        log.info("Received request for api: list of categories.");
        Category categoryById = categoryCache.getCategoryById(id);
        if (header.contains("/json")) {
            return categoryParser.toJSON(categoryById);
        }
        if (header.contains("/xml")) {
            return categoryParser.toXML(categoryById);
        } else {
            return categoryById.toString();
        }
    }

}
