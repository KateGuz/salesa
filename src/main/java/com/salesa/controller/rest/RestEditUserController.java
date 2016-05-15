package com.salesa.controller.rest;

import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.UserService;
import com.salesa.util.UserParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class RestEditUserController {
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private UserService userService;
    @Autowired
    private UserParser userParser;

    @RequestMapping(value = "/api/editUser/{userId}", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String editUser(@PathVariable("userId") int userId, HttpSession session, @RequestHeader("accept") String header, HttpServletResponse response){
        if(userSecurity.getUserBySessionId(session.getId()).getId() != userId){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "You have to authorize to be able edit your profile";
        }
        User user = userService.get(userId);
        if(header.contains("/json")){
            return userParser.toJSON(user);
        }
        if(header.contains("/xml")){
            return userParser.toXML(user);
        }
        return user.toString();
    }
    @RequestMapping(value = "/api/editUser/{userId}", method = RequestMethod.PUT,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String editUser(@PathVariable("userId") int userId, HttpSession session, String header, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(userSecurity.getUserBySessionId(session.getId()).getId() != userId){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "You have to authorize to  edit your profile";
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
        return "Your changes was successfully saved \n" + user.toString();
    }
}
