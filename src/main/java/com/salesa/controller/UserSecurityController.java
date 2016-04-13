package com.salesa.controller;

import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class UserSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    private String singUp(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password,HttpSession session){
        log.info("signing up, name " + name + " email " + email + " password " + password);
        return "";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    private String singIn(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session){
        log.info("signing in,  " + email + " password " + password);
        return "";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.DELETE)
     public String signOut(HttpSession session) {
        session.removeAttribute("loggedUser");
        log.info("signing out");
            return "";
        }
}
