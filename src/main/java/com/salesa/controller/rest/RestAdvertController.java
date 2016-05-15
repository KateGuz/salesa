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
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/v1/advert", method = RequestMethod.POST,
            headers = {"Content-type=application/json;charset=UTF-8"})
    public ResponseEntity<String> addAdvert(@RequestBody String body, HttpSession session) throws IOException {
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        log.info("Incoming request : {}", body);
        Advert advertToSave = advertParser.toAdvert(body);
        log.info("Advert to save {}", advertToSave);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/editAdvert/{advertId}", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String getAdvert(@PathVariable("advertId") int advertId, HttpSession session, HttpServletResponse response, @RequestHeader("accept") String header) throws IOException {
        Advert advert = advertService.get(advertId);
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null || advert.getUser().getId() != user.getId()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Sorry, you have no permission for this action. Please log in";
        }

        /*response.getWriter().println("Please, put new values into fields");*/
        if (header.contains("/json")) {
            return advertParser.toJSON(advert);
        }
//        if (header.contains("/xml")) {
//            return advertParser.toXML(advert);
//        }
        return advert.toString();
    }

    @RequestMapping(value = "/api/editAdvert/{advertId}", method = RequestMethod.PUT)
    public String saveAdvert(@PathVariable("advertId") int advertId, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        Advert advert = advertService.get(advertId);
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null || advert.getUser().getId() != user.getId()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Sorry, you have no permission for this action. Please log in";
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
            switch (status) {
                case "Забронировано":
                    status = "H";
                    break;
                case "Продано":
                    status = "S";
                    break;
                default:
                    status = "A";
            }
            advert.setStatus(status);
        }
        if (request.getParameter("categoryId") != null) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            advert.setCategory(new Category(categoryId));
        }

        advert.setModificationDate(LocalDateTime.now());
        log.info("Updating advert " + advertId);
        advertService.update(advert);
        return "Your advert was successfully changed";
    }

    private Advert constructAdvert(User user, String title, String text, double price, String currency, String status, Integer categoryId) {
        Advert advert = new Advert();
        advert.setTitle(title);
        advert.setText(text);
        advert.setCategory(new Category(categoryId));
        advert.setPrice(price);
        advert.setCurrency(currency);
        advert.setStatus(status);
        advert.setModificationDate(LocalDateTime.now());
        advert.setUser(new User(user.getId()));
        log.info("Created advert {} ", advert);
        return advert;
    }
}
