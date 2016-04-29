package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Controller
public class AddAdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserSecurity userSecurity;

    @RequestMapping(value = "/addAdvert/", method = RequestMethod.GET)
    public String adAdvert(Model model, HttpSession session){
        User user = userSecurity.getUserBySessionId(session.getId());
        session.setAttribute("loggedUser", user);
        model.addAttribute("categories", categoryService.getAll());
        return "addAdvert";
    }

    @RequestMapping(value = "/addAdvert/", method = RequestMethod.POST)
    public ResponseEntity<Void> adAdvert(HttpServletRequest httpServletRequest, HttpSession session) throws IOException {
        /*, @RequestParam(name = "title") String title, @RequestParam(name = "text") String text, @RequestParam(name = "category") Integer categoryId, @RequestParam(name = "price") double price, @RequestParam(name = "currency")String currency, @RequestParam(name = "status") String status*/
        String title = httpServletRequest.getParameter("title");
        String text = httpServletRequest.getParameter("text");
        double price = Double.parseDouble(httpServletRequest.getParameter("price"));
        String currency = httpServletRequest.getParameter("currency");
        String status = httpServletRequest.getParameter("status");
        if(status.equals("Активно")){
            status = "A";
        } else if(status.equals("Забронировано")){
            status = "H";
        } else {
            status = "S";
        }
        Integer categoryId = Integer.parseInt(httpServletRequest.getParameter("categoryId"));
        if(title == null || text == null || price == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Query add new advert: advert id = " + "advert title = " + "user name = " );     int userId = userSecurity.getUserBySessionId(session.getId()).getId();
        Advert advert = new Advert();
        advert.setTitle(title);
        advert.setText(text);
        advert.setCategory((new Category(categoryId)));
        advert.setPrice(price);
        advert.setCurrency(currency);
        advert.setStatus(status);
        advert.setModificationDate(LocalDateTime.now());
        advert.setUser(new User(userId));
        advertService.saveAdvert(advert);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
