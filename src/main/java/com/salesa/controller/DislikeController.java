package com.salesa.controller;

import com.salesa.util.BlockedUserTask;
import com.salesa.entity.User;
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


import java.util.Timer;

@Controller
public class DislikeController{

    private static final long BLOCKED_PERIOD = 7 * 24 * 60 * 60 * 1000;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dislike/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveDislike(@PathVariable Integer userId) throws InterruptedException {

        log.info("Start processing dislike request for user with id ", userId);

        Timer timer = new Timer(true);
        User user = userService.get(userId);
        int dislikeAmount = user.getDislikeAmount() + 1;

        log.info("User with id {} has {} dislikes", userId, dislikeAmount);

        if (dislikeAmount > 9) {
            BlockedUserTask task = new BlockedUserTask(user, userService);
            user.setStatus("B");
            user.setDislikeAmount(dislikeAmount);
            log.info("Start timer for user with status {} and dislikesCount {}", user.getStatus(), user.getDislikeAmount());
            timer.schedule(task, BLOCKED_PERIOD);
            userService.updateUsersDislike(user);
            log.info("Finish timer for user with status {} and dislikesCount {}", user.getStatus(), user.getDislikeAmount());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        user.setDislikeAmount(dislikeAmount);
        userService.updateUsersDislike(user);
        log.info("Finish processing dislike request for user with id {}", userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
