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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class EditProfileController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/editProfile/{id}", method = RequestMethod.POST)
    public ResponseEntity<Integer> editAdvert(HttpServletRequest httpServletRequest,
                                              @PathVariable("id") int userId,
                                              @RequestParam(name = "avatar", required = false) MultipartFile avatar)
            throws IOException {


        String name = httpServletRequest.getParameter("name");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone");
        String password = httpServletRequest.getParameter("password");
        if (name == null || email == null || password == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.get(userId);
        user.setName(name);
        user.setEmail(email);
        if(!"".equals(phone)) {
            user.setPhone(phone);
        }
        user.setPassword(password);
        if (avatar != null) {
            user.setAvatar(avatar.getBytes());
        }
        userService.update(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }
}
