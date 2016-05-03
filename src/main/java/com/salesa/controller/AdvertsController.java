package com.salesa.controller;


import com.salesa.entity.CurrencyRate;
import com.salesa.util.CurrencyConverter;
import com.salesa.entity.Advert;
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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
public class AdvertsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CurrencyConverter currencyConverter;


    @RequestMapping("/")
    public String home(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "currency", defaultValue = "UAH") String currency, Model model, HttpSession session) throws IOException {
        model.addAttribute("categories", categoryService.getAll());
        AdvertFilter advertFilter = new AdvertFilter();
        advertFilter.setPage(page);
        AdvertPageData advertPageData = advertService.get(advertFilter);

        for (Advert advert : advertPageData.getAdverts()) {
            String baseCurrency = advert.getCurrency();
            advert.setCurrency(currency);
            double oldPrice = advert.getPrice();
            double newPrice = oldPrice * currencyConverter.getRate(baseCurrency, currency);
            BigDecimal price = new BigDecimal(newPrice);
            price = price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            advert.setPrice(price.doubleValue());
        }

        model.addAttribute("pageData", advertPageData);

        User user = new User();
        user.setName("Test User");
        model.addAttribute("user", user);

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
}
