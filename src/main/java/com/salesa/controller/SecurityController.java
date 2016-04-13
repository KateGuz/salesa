package com.salesa.controller;

import com.salesa.entity.User;
import com.salesa.service.AdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdvertService advertService;


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(@RequestParam("email") String email,
                           @RequestParam("pass") String pass,
                           HttpSession session) {

        User user = new User();
        user.setName("Test User");
        user.setId(1);
        user.setEmail(email);
        user.setPassword(pass);

        session.setAttribute("loggedUser", user);
        return "redirect:/";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("pass") String pass,
                         HttpSession session) {

        User user = new User();
        user.setName("Test User");
        user.setId(1);
        user.setEmail(email);
        user.setPassword(pass);

        session.setAttribute("loggedUser", user);

        return "redirect:/";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpSession session) {
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }
}