package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.Category;
import com.salesa.entity.Image;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.service.CategoryService;
import com.salesa.service.ImageService;
import com.salesa.util.DefaultPriceUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class AddAdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserSecurity userSecurity;

    @Autowired
    private DefaultPriceUpdater defaultPriceUpdater;

    @RequestMapping(value = "/addAdvert", method = RequestMethod.GET)
    public String addAdvert(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if ("B".equals(user.getStatus())) {
            return "404";
        }
        model.addAttribute("loggedUserId", user.getId());
        model.addAttribute("categories", categoryService.getAll());
        return "addAdvert";
    }

    @ResponseBody
    @RequestMapping(value = "/addAdvert", method = RequestMethod.POST)
    public ResponseEntity<Integer> addAdvert(HttpServletRequest httpServletRequest, HttpSession session,
                                             @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                             @RequestParam(name = "otherImages", required = false) MultipartFile[] additionalImages) throws IOException {
        String title = httpServletRequest.getParameter("title");
        String text = httpServletRequest.getParameter("text");
        double price = Double.parseDouble(httpServletRequest.getParameter("price"));
        String currency = httpServletRequest.getParameter("currency");
        String status = httpServletRequest.getParameter("status");
        switch (status) {
            case "Продано":
                status = "S";
                break;
            case "Забронировано":
                status = "H";
                break;
            default:
                status = "A";
        }
        Integer categoryId = Integer.parseInt(httpServletRequest.getParameter("categoryId"));
        if (title == null || text == null || price == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Creating advert... Title: {}; text: {}; price: {}; currency: {}; status: {}", title, text, price, currency, status);
        int userId = userSecurity.getUserBySessionId(session.getId()).getId();
        log.info("Author id: {}", userId);
        Advert advert = new Advert();
        advert.setTitle(title);
        advert.setText(text);
        advert.setCategory((new Category(categoryId)));
        advert.setPrice(price);
        advert.setCurrency(currency);
        advert.setStatus(status);
        advert.setModificationDate(LocalDateTime.now());
        advert.setUser(new User(userId));

        int savedAdvertId = advertService.saveAdvert(advert);

        if (mainImage != null) {
            Image image = new Image();
            image.setContent(mainImage.getBytes());
            image.setType("M");
            imageService.saveAdvertImage(image, savedAdvertId);
        }
        if (additionalImages != null) {
            for (MultipartFile additionalImage : additionalImages) {
                Image image = new Image();
                image.setContent(additionalImage.getBytes());
                image.setType("R");
                imageService.saveAdvertImage(image, savedAdvertId);
            }
        }
        log.info("Advert {} was successfully saved.");
        defaultPriceUpdater.updateDefaultPrice();
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
