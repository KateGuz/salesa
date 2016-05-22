package com.salesa.controller.rest;

import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class RestUserSecurityController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/signIn", method = RequestMethod.POST)
    public ResponseEntity<String> signInREST(@RequestParam("email") String email,
                                     @RequestParam("pass") String pass, HttpSession session, HttpServletResponse response) {
        log.info("Sign in request:" + " email = ", email + " pass = " + pass);
        User user = userService.get(email);
        if (user != null && user.getPassword().equals(pass)) {
            userSecurity.addSession(session.getId(), user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/v1/signUp", method = RequestMethod.POST)
    public ResponseEntity<String> signUpREST(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
                             @RequestParam(name = "pass") String pass, HttpSession session, HttpServletResponse response) {
        log.info("Sign up request  : {}" + "email = " + email + ", name = " + name);
        if ((name.equals("") || email.equals("") || pass.equals("")) ) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(pass);
        log.info("creating new user : {}", user);
        user.setId(userService.save(user));
        userSecurity.addSession(session.getId(), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/signOut", method = RequestMethod.DELETE)
    public ResponseEntity<String> signOut(HttpSession session) {
        log.info("finishing session : ", session.getId());
        userSecurity.deleteSession(session.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
