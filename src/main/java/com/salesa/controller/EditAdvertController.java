package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class EditAdvertController {
    @Autowired
    private AdvertService advertService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserSecurity userSecurity;


    @RequestMapping(value = "/editAdvert/{id}", method = RequestMethod.GET)
    public String editAdvertPage(@PathVariable("id") int id, Model model, HttpSession session) {
        model.addAttribute("advert", advertService.get(id));
        model.addAttribute("categories", categoryService.getAll());
        User user = userSecurity.getUserBySessionId(session.getId());
        session.setAttribute("loggedUser", user);
        return "editAdvert";
    }

    @RequestMapping(value = "/editAdvert/{id}", method = RequestMethod.POST)
    public String editAdvertResult(@ModelAttribute("advert") Advert advert, Model model, HttpSession session) {
        HashMap<String, String> errors = new HashMap<>();
        LocalDateTime time = LocalDateTime.now();
        User user = userSecurity.getUserBySessionId(session.getId());
        session.setAttribute("loggedUser", user);
        advert.setModificationDate(time);
        model.addAttribute("advert", advert);
        model.addAttribute("categories", categoryService.getAll());

        if (advert.getTitle().equals("")) {
            errors.put("title", "Title is empty");
        }
        if (advert.getText().equals("")) {
            errors.put("text", "Input advert message please.");
        }
        if (advert.getPrice() == 0.0 || advert.getPrice() == 0) {
            errors.put("price", "Input your price please.");
        }
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "editAdvert";
        }
        advertService.update(advert);
        return "redirect:/advert/" + advert.getId();
    }
}
