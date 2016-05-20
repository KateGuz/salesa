package com.salesa.controller;

import com.salesa.entity.Feedback;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.FeedbackService;
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
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Controller
public class FeedbackController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserSecurity userSecurity;

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/feedback/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveFeedback(HttpServletRequest httpServletRequest, @PathVariable Integer userId) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), StandardCharsets.UTF_8));
        String feedbackText = bufferedReader.readLine();

        ResponseEntity<Void> result = new ResponseEntity<>(HttpStatus.OK);

        log.info("Making feedback for user with id {}", userId);
        Feedback feedback = new Feedback();
        feedback.setUser(new User(userId));
        feedback.setAuthor(userSecurity.getUserBySessionId(httpServletRequest.getSession().getId()));
        feedback.setCreationDate(LocalDateTime.now());
        feedback.setText(feedbackText);

        feedbackService.saveFeedback(feedback);

        log.info("Feedback was successfully saved");
        return result;
    }
}
