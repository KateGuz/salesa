package com.salesa.security;

import com.salesa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserSecurity {

    @Autowired
    public Map<String, User> sessionIdToUser;

    public User getUserBySessionId(String sessionId) {
        return sessionIdToUser.get(sessionId);
    }

    public void addSession(String sessionId, User user) {
        sessionIdToUser.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        sessionIdToUser.remove(sessionId);
    }

}
