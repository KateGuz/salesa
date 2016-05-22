package com.salesa.security;

import com.salesa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserSecurity {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private Map<String, User> sessionIdToUser = new HashMap<>();

    public User getUserBySessionId(String sessionId) {
        return sessionIdToUser.get(sessionId);
    }

    public void addSession(String sessionId, User user) {
        log.info("Adding session with id {} to user {}", sessionId, user);
        sessionIdToUser.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        log.info("Deleting session with id {}", sessionId);
        sessionIdToUser.remove(sessionId);
    }

}
