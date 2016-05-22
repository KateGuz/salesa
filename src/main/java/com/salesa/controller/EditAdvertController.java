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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EditAdvertController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserSecurity userSecurity;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DefaultPriceUpdater defaultPriceUpdater;


    @RequestMapping(value = "/editAdvert/{id}", method = RequestMethod.GET)
    public String showEditAdvert(@PathVariable("id") int id, Model model, HttpSession session) {
        model.addAttribute("advert", advertService.get(id));
        model.addAttribute("categories", categoryService.getAll());
        User user = userSecurity.getUserBySessionId(session.getId());
        session.setAttribute("loggedUser", user);
        return "editAdvert";
    }

    @RequestMapping(value = "/editAdvert/{id}", method = RequestMethod.POST)
    public ResponseEntity<Integer> editAdvert(HttpServletRequest httpServletRequest,
                                              @PathVariable("id") int advertId,
                                              @RequestParam(name = "mainImage", required = false) MultipartFile mainImage,
                                              @RequestParam(name = "otherImages", required = false) MultipartFile[] additionalImages) throws IOException {

        String title = httpServletRequest.getParameter("title");
        String text = httpServletRequest.getParameter("text");
        double price = Double.parseDouble(httpServletRequest.getParameter("price"));
        String currency = httpServletRequest.getParameter("currency");
        String status = httpServletRequest.getParameter("status");
        Integer categoryId = Integer.parseInt(httpServletRequest.getParameter("categoryId"));
        if (title == null || text == null || price == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Advert advert = advertService.get(advertId);
        advert.setTitle(title);
        advert.setText(text);
        advert.setCategory((new Category(categoryId)));
        advert.setPrice(price);
        advert.setCurrency(currency);
        advert.setStatus(status);
        advert.setModificationDate(LocalDateTime.now());

        advertService.update(advert);

        if (mainImage != null) {
            List<Image> currentImages = imageService.getAdvertImages(advert.getId());
            int currentMainImageId = -1;
            for (Image currentImage : currentImages) {
                if ("M".equals(currentImage.getType())) {
                    currentMainImageId = currentImage.getId();
                }
            }
            imageService.remove(currentMainImageId);
            Image image = new Image();
            image.setContent(mainImage.getBytes());
            image.setType("M");
            imageService.saveAdvertImage(image, advert.getId());
        }
        if (additionalImages.length > 0) {

            List<Image> currentImages = imageService.getAdvertImages(advert.getId());
            List<Integer> currentAdditionalImagesIds = new ArrayList<>();
            for (Image currentImage : currentImages) {
                if(!"M".equals(currentImage.getType())){
                    currentAdditionalImagesIds.add(currentImage.getId());
                }
            }
            for (Integer currentAdditionalImagesId : currentAdditionalImagesIds) {
                imageService.remove(currentAdditionalImagesId);
            }
            for (MultipartFile additionalImage : additionalImages) {
                Image image = new Image();
                image.setContent(additionalImage.getBytes());
                image.setType("R");
                imageService.saveAdvertImage(image, advert.getId());
            }
        }
        defaultPriceUpdater.updateDefaultPrice();
        return new ResponseEntity<>(advert.getUser().getId(), HttpStatus.OK);
    }
}
