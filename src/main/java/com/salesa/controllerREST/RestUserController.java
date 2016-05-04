package com.salesa.controllerREST;

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
public class RestUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;
    @Autowired
    private UserService userService;
    @Autowired
    UserDataRest userDataRest;


    @RequestMapping(value = "/api/user/{userId}", method = RequestMethod.GET,
            headers = {"Accept=application/xml", "Accept=application/json"},
            produces = {"application/xml", "application/json"})
    public String user(@PathVariable("userId") int userId, @RequestHeader("accept") String header, HttpServletResponse responsee) throws IOException {
        log.info("Query get adverts by userId: " + userId);
        log.info("Query get feedbacks by userId: " + userId);
        log.info("Query get user by userId: " + userId);
        User user = userService.get(userId);
        List<Advert> adverts = advertService.getByUserId(userId);
        Collections.reverse(adverts);
        List<Feedback> feedbacks = userService.getByUserId(userId);
        Collections.reverse(feedbacks);
        if (header.contains("/json")) {
            return userDataRest.toJSON(user, feedbacks, adverts);
        }
        if (header.contains("/xml")) {
            return userDataRest.toXML(user, feedbacks, adverts);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(user);
            stringBuilder.append("\n");
            for (Advert advert : adverts) {
                stringBuilder.append(advert);
                stringBuilder.append("\n");
            }
            for (Feedback feedback : feedbacks) {
                stringBuilder.append(feedback);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

    }
}
