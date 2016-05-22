package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import com.salesa.service.AdvertService;
import com.salesa.service.UserService;
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
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyConverter currencyConverter;

    @RequestMapping("/user/{userId}")
    public String user(@PathVariable("userId") int userId, Model model, @RequestParam(required = false) String currency, HttpSession session){
        if (currency == null && session.getAttribute("selectedCurrency") == null) {
            currency = "UAH";
        }
        if (currency == null && session.getAttribute("selectedCurrency") != null) {
            currency = (String) session.getAttribute("selectedCurrency");
        }
        if (currency != null) {
            session.setAttribute("selectedCurrency", currency);
        }
        log.info("Query get adverts by userId: " + userId);
        log.info("Query get feedbacks by userId: " + userId);
        log.info("Query get user by userId: " + userId);
        User user = userService.get(userId);
        List<Advert> adverts = advertService.getByUserId(userId);
        String defaultCurrency = (String) session.getAttribute("selectedCurrency");
        for (Advert advert : adverts) {
            currencyConverter.updatePriceAndCurrency(advert, defaultCurrency);
        }
        Collections.reverse(adverts);
        List<Feedback> feedbacks = userService.getByUserId(userId);
        Collections.reverse(feedbacks);
        model.addAttribute("user", user);
        model.addAttribute("adverts", adverts);
        model.addAttribute("feedbacks", feedbacks);
        return "user";
    }

}