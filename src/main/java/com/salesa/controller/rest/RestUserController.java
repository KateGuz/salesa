package com.salesa.controller.rest;

import com.salesa.entity.Advert;
import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import com.salesa.service.AdvertService;
import com.salesa.service.UserService;
import com.salesa.util.UserDataRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/user/{userId}")
public class RestUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;
    @Autowired
    private UserService userService;
    @Autowired
    UserDataRest userDataRest;


    @RequestMapping(headers = {"Accept=application/json;charset=UTF-8"})
    public String userJson(@PathVariable("userId") int userId) throws IOException {
        log.info("Query get adverts(json) by userId: " + userId);
        log.info("Query get feedbacks(json) by userId: " + userId);
        log.info("Query get user(json) by userId: " + userId);
        User user = userService.get(userId);
        List<Advert> adverts = advertService.getByUserId(userId);
        Collections.reverse(adverts);
        List<Feedback> feedbacks = userService.getByUserId(userId);
        Collections.reverse(feedbacks);

        return userDataRest.toJSON(user, feedbacks, adverts);
    }

    @RequestMapping(headers = {"Accept=application/xml;charset=UTF-8"})
    public String userXML(@PathVariable("userId") int userId) throws IOException {
        log.info("Query get adverts(xml) by userId: " + userId);
        log.info("Query get feedbacks(xml) by userId: " + userId);
        log.info("Query get user(xml) by userId: " + userId);
        User user = userService.get(userId);
        List<Advert> adverts = advertService.getByUserId(userId);
        Collections.reverse(adverts);
        List<Feedback> feedbacks = userService.getByUserId(userId);
        Collections.reverse(feedbacks);

        return userDataRest.toXML(user, feedbacks, adverts);
    }
}
