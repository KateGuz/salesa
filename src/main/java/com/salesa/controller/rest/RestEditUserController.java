package com.salesa.controller.rest;

import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class RestEditUserController {
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> editUser(@PathVariable("userId") int userId, HttpSession session, HttpServletRequest request) throws IOException {
        if(userSecurity.getUserBySessionId(session.getId()).getId() != userId){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.get(userId);
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(name != null){
            user.setName(name);
        }
        if(phone != null){
            user.setPhone(phone);
        }
        if(email != null){
            user.setEmail(email);
        }
        if(password != null){
            user.setPassword(password);
        }
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
