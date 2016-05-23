package com.salesa.controller;

import com.salesa.service.DislikeService;
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


import javax.servlet.http.HttpSession;
import java.util.Timer;

@Controller
public class DislikeController {

    private static final long BLOCKED_PERIOD = 7 * 24 * 60 * 60 * 1000;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private DislikeService dislikeService;

    @RequestMapping(value = "/dislike/{userId}", method = RequestMethod.POST)
    public ResponseEntity<String> saveDislike(@PathVariable Integer userId, HttpSession session) throws InterruptedException {

        User sender = (User) session.getAttribute("loggedUser");
        User receiver = userService.get(userId);

        if (dislikeService.checkDislike(sender.getId(), receiver.getId()) == 0) {
            log.info("Start processing dislike request for user with id ", userId);
            int dislikeAmount = receiver.getDislikeAmount() + 1;

            Timer timer = new Timer(true);

            log.info("User with id {} now has {} dislikes", userId, dislikeAmount);

            if (dislikeAmount > 9)
            {
                BlockedUserTask task = new BlockedUserTask(receiver, userService);
                receiver.setStatus("B");
                receiver.setDislikeAmount(dislikeAmount);
                log.info("Start timer for user with status {} and dislikesCount {}", receiver.getStatus(), receiver.getDislikeAmount());
                timer.schedule(task, BLOCKED_PERIOD);
                userService.updateUsersDislike(receiver);
                log.info("Finish timer for user with status {} and dislikesCount {}", receiver.getStatus(), receiver.getDislikeAmount());
                return new ResponseEntity<>(HttpStatus.OK);
            }

            receiver.setDislikeAmount(dislikeAmount);
            userService.updateUsersDislike(receiver);
            dislikeService.updateDislike(sender.getId(), receiver.getId());
            log.info("Finish processing dislike request for user with id {}", userId);
            return new ResponseEntity<>("Ваш отзыв добавлен", HttpStatus.OK);
        }
        return new ResponseEntity<>("Вы уже ставили дизлайк этому пользователю", HttpStatus.OK);
    }

}
