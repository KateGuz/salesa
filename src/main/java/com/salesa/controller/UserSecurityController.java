package com.salesa.controller;

import com.google.gson.Gson;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurity userSecurity;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<String> singUp(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "pass") String password, HttpSession session) {
        if (name.equals("") || email.equals("") || password.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Signing up with name {}, email {} and password {}", name, email, password);
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setId(userService.save(user));
        userSecurity.addSession(session.getId(), user);
        session.setAttribute("loggedUser", user);
        String loggedUser = new Gson().toJson(user);
        log.info("Successful signing up for user {}", user);
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<String> singIn(@RequestParam("email") String email, @RequestParam("pass") String password, HttpSession session) {
        log.info("Signing in with email {} and password {}", email, password);

        User user = userService.get(email);
        if (!(user.getPassword().equals(password))) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userSecurity.addSession(session.getId(), user);
        session.setAttribute("loggedUser", user);
        String loggedUser = new Gson().toJson(user);
        log.info("Successful signing in for user {}", user);
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpSession session) {
        log.info("Signing out");
        session.removeAttribute("loggedUser");
        userSecurity.deleteSession(session.getId());
        return "redirect:/";
    }

}
