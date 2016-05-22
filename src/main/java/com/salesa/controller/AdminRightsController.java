package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminRightsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    AdvertService advertService;

    @Autowired
    UserSecurity userSecurity;

    @RequestMapping(value = "/makeAdmin/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> makeUserAdmin(HttpServletRequest request, @PathVariable Integer userId){
        User admin = userSecurity.getUserBySessionId(request.getSession().getId());
        User user = userService.get(userId);



        if ("A".equals(admin.getType())) {
            if ("U".equals(user.getType())) {
                log.info("Admin {} make query for adding user {} to admins", admin.getName(), user.getName());
                user.setType("A");
            } else {
                log.info("Admin {} make query for degrading admin {} to users", admin.getName(), user.getName());
                user.setType("U");
            }
            userService.updateUserType(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteAdvert/{advertId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteAdvert(HttpServletRequest request, @PathVariable Integer advertId){
        User admin = userSecurity.getUserBySessionId(request.getSession().getId());
        Advert advert = advertService.get(advertId);

        if ("A".equals(admin.getType())) {
            advertService.delete(advert.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteUser(HttpServletRequest request, @PathVariable Integer userId){
        User admin = userSecurity.getUserBySessionId(request.getSession().getId());
        User user = userService.get(userId);

        if ("A".equals(admin.getType())) {
            userService.deleteUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
