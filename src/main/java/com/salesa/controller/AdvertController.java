package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/advert/{advertId}")
    public String advert(@PathVariable("advertId") int advertId, Model model) {
        log.info("Query get advert by id: " + advertId);
        Advert advert = advertService.get(advertId);
        model.addAttribute("advert", advert);
        return "advert";
    }
}
