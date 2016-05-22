package com.salesa.controller;

import com.salesa.entity.User;
import com.salesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DislikeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dislike/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveDislike(@PathVariable Integer userId) {
        ResponseEntity<Void> result = new ResponseEntity<>(HttpStatus.OK);
        User user = userService.get(userId);
        int dislikeAmount = user.getDislikeAmount() + 1;
        user.setDislikeAmount(dislikeAmount);
        userService.updateUsersDislike(user);
        System.out.println(user);
        return result;
    }
}
