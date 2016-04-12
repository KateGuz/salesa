package com.salesa.controller;

import com.salesa.entity.User;
import com.salesa.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class testControll {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertService advertService;


    @RequestMapping("/user")
    public String testAjax(@RequestParam("email") String email,
                           @RequestParam("pass") String pass,
                           Model model) {

        System.out.println("email: " + email + " " + "pass: " + pass);

        User user = new User();
        user.setName("Test User");
        user.setId(1);
        user.setEmail(email);
        user.setPassword(pass);

        model.addAttribute("user", user);
        return "home";

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String advert(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("pass") String pass,
                         Model model) {

        System.out.println(name + " " + email + " " + pass);
        model.addAttribute("email", email);
        model.addAttribute("pass", pass);
        model.addAttribute("name", name);
        return "home";
    }

    /*@RequestMapping("/user2222")
    public String testAjax1(Model model) {
        User user = new User();
        user.setName("Test User");
        user.setId(1);
        user.setEmail("email");
        user.setPassword("pass");

        model.addAttribute("user", user);
        return user.toString();

    }*/

}