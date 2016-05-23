package com.salesa.logging;

import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class LoggingMVCRequestInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserSecurity userSecurity;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!validateRequestForResources(request)) {
            MDC.put("requestId", UUID.randomUUID().toString());
            String sessionId = request.getSession().getId();
            User user = userSecurity.getUserBySessionId(sessionId);
            if (user != null) {
                MDC.put("user", user.getEmail());
            } else {

                MDC.put("user", "guest");
            }
            log.info("start processing request for url {}", request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!validateRequestForResources(request)) {
            log.info("finish processing request for url {}", request.getRequestURI());
        }
        super.afterCompletion(request, response, handler, ex);
    }

    private boolean validateRequestForResources(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().matches(".*(/js/|/css/|/img/|/fonts/).*");
    }
}
