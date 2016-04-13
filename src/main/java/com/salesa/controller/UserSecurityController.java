package com.salesa.controller;

import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/*")
    private String singForm(Model model){
        return "home";
    }
}
