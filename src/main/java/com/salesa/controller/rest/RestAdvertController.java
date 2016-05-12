package com.salesa.controller.rest;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class RestAdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserSecurity userSecurity;

    @RequestMapping(value = "/v1/advert", method = RequestMethod.POST)
    public ResponseEntity<String> addAdvert(HttpServletRequest httpServletRequest, HttpSession session, HttpServletResponse response) throws IOException {
        User user = userSecurity.getUserBySessionId(session.getId());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String title = httpServletRequest.getParameter("title");
        String text = httpServletRequest.getParameter("text");
        double price = Double.parseDouble(httpServletRequest.getParameter("price"));
        String currency = httpServletRequest.getParameter("currency");
        String status = httpServletRequest.getParameter("status");
        Integer categoryId = Integer.parseInt(httpServletRequest.getParameter("categoryId"));
        if(title == null || text == null || price == 0 || categoryId == 0 || currency == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Advert advert = constructAdvert(user, title, text, price, currency, status);
        log.info("Creating advert {} " + advert);
        advertService.saveAdvert(advert);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Advert constructAdvert(User user, String title, String text, double price, String currency, String status) {
        Advert advert = new Advert();
        advert.setTitle(title);
        advert.setText(text);
        advert.setCategory(new Category());
        advert.setPrice(price);
        advert.setCurrency(currency);
        advert.setStatus(status);
        advert.setModificationDate(LocalDateTime.now());
        advert.setUser(user);
        return advert;
    }

}
