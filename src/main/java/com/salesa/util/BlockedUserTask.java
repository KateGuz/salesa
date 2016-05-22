package com.salesa.util;

import com.salesa.entity.User;
import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class BlockedUserTask extends TimerTask {

    private User user;
    private UserService userService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public BlockedUserTask(User userToBlock, UserService userService){
        this.user = userToBlock;
        this.userService = userService;
    }

    @Override
    public void run() {
        log.info("Start countdown for user with status {} and dislikesCount {}", user.getStatus(), user.getDislikeAmount());
        if ("B".equals(user.getStatus())) {
            user.setStatus("A");
            user.setDislikeAmount(0);
            userService.updateUsersDislike(user);
            log.info("Finish countdown for user with status {} and dislikesCount {}", user.getStatus(), user.getDislikeAmount());
        }
    }
}
