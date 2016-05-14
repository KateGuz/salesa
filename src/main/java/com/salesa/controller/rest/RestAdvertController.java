package com.salesa.controller.rest;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.util.mapper.AdvertParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class RestAdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertParser advertParser;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserSecurity userSecurity;

    @RequestMapping(value = "/v1/advert/{advertId}", method = RequestMethod.GET, headers = {"Content-type=application/json;charset=UTF-8"})
    public String getAdvertJSON(@PathVariable("advertId") int advertId) {
        Advert advert = advertService.get(advertId);
        return advertParser.toJSON(advert);
    }
    // TODO: 5/14/2016 xml

    @RequestMapping(value = "/v1/advert", method = RequestMethod.POST,
            headers = {"Content-type=application/json;charset=UTF-8"})
    public ResponseEntity<String> addAdvert(@RequestBody String body, HttpSession session) throws IOException {
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        log.info("Incoming request : {}", body);
        Advert advert = advertParser.toAdvert(body);
        log.info("Advert to save {}", advert);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/editAdvert/{advertId}", method = RequestMethod.PUT)
    public ResponseEntity<String> editAdvert(@PathVariable("advertId") int advertId, HttpServletRequest request, HttpSession session) {
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
        if (status != null) {
            advert.setStatus(status);
        }
        if (request.getParameter("categoryId") != null) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            advert.setCategory(new Category(categoryId));
        }

        advert.setModificationDate(LocalDateTime.now());
        log.info("Updating advert : {}" + advert);
        advertService.update(advert);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

