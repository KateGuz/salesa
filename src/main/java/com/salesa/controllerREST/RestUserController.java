package com.salesa.controllerREST;

import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RestUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/signIn", method = RequestMethod.PUT)
    public String signInREST(@RequestParam("email") String email,
                             @RequestParam("pass") String pass, HttpSession session) {
        log.info("Received request for api: Sign In - email:[" + email + "], password:[" + pass + "].");
        User user = userService.get(email);
        if (user != null && user.getPassword().equals(pass)) {
            userSecurity.addSession(session.getId(), user);
            return "Sign In is successful. Welcome dear " + user.getName();
        }
        return "Sign In is unsuccessful. Check correctly your @mail or password.";
    }

    @RequestMapping(value = "/api/signUp", method = RequestMethod.PUT)
    public String signUpREST(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
                             @RequestParam(name = "pass") String pass, HttpSession session) {
        log.info("Received request for api: Sign Up - name:[" + name +"], email:[" + email + "], password:[" + pass + "].");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(pass);
        user.setId(userService.save(user));
        userSecurity.addSession(session.getId(), user);
        return "Sign Up is successful. Welcome dear " + user.getName();
    }
}
