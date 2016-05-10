package com.salesa.controller.rest;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.service.cache.CategoryCache;
import com.salesa.util.AdvertParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class RestEditAdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private CategoryCache categoryCache;

    @RequestMapping(value = "/v1/advert/{advertId}", method = RequestMethod.PUT)
    public ResponseEntity<String> editAdvert(@PathVariable("advertId") int advertId, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        Advert advert = advertService.get(advertId);
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null || advert.getUser().getId() != user.getId()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String title = request.getParameter("title");
        if (title != null) {
            advert.setTitle(title);
        }
        String text = request.getParameter("text");
        if (text != null) {
            advert.setText(text);
        }
        if (request.getParameter("price") != null) {
            double price = Double.parseDouble(request.getParameter("price"));
            advert.setPrice(price);
        }
        String currency = request.getParameter("currency");
        if (currency != null) {
            advert.setCurrency(currency);
        }
        String status = request.getParameter("status");
        advert.setStatus(status);
        if (request.getParameter("categoryId") != null) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            advert.setCategory(categoryCache.getCategoryById(categoryId));
        }

        advert.setModificationDate(LocalDateTime.now());
        log.info("Updating advert {}" + advert);
        advertService.update(advert);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
