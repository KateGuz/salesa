package com.salesa.controller;

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

import javax.servlet.http.HttpSession;


@Controller
public class UserSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurity userSecurity;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<Void> singUp(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "pass") String password, HttpSession session) {
        log.info("signing up, name " + name + " email " + email + " password " + password);
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setId(userService.save(user));
        userSecurity.addSession(session.getId(), user);
        session.setAttribute("loggedUser", user);
        ResponseEntity<Void> result = new ResponseEntity<>(HttpStatus.OK);
        return result;
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<Void> singIn(@RequestParam("email") String email, @RequestParam("pass") String password, HttpSession session) {
        log.info("signing in,  " + email + " password " + password);

        User user = userService.get(email);
        userSecurity.addSession(session.getId(), user);
        session.setAttribute("loggedUser", user);
        ResponseEntity<Void> result = new ResponseEntity<>(HttpStatus.OK);
        return result;
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String signOut(HttpSession session) {
        session.removeAttribute("loggedUser");
        log.info("signing out");
        return "redirect:/";
    }
}
