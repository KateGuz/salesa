package com.salesa.controller;

import com.salesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AvatarController {

    @Autowired
    private UserService userService;

    @RequestMapping("/avatar/{userId}")
    public ResponseEntity<byte[]> getImage(@PathVariable int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(userService.get(userId).getAvatar(), headers,
                HttpStatus.OK);
    }

}
