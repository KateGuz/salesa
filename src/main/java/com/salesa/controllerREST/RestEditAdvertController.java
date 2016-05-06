package com.salesa.controllerREST;

import com.salesa.entity.Advert;
import com.salesa.entity.AdvertRest;
import com.salesa.entity.Category;
import com.salesa.entity.User;
import com.salesa.security.UserSecurity;
import com.salesa.service.AdvertService;
import com.salesa.util.AdvertParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class RestEditAdvertController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdvertService advertService;
    @Autowired
    private UserSecurity userSecurity;
    @Autowired
    private AdvertParser advertParser;

    @RequestMapping(value = "/api/editAdvert/{advertId}", method = RequestMethod.GET,
            headers = {"Accept=application/xml;charset=UTF-8", "Accept=application/json;charset=UTF-8"},
            produces = {"application/xml", "application/json"})
    public String editAdvert(@PathVariable("advertId") int advertId, HttpSession session, HttpServletResponse response, @RequestHeader("accept") String header) throws IOException {
        Advert advert = advertService.get(advertId);
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null || advert.getUser().getId() != user.getId()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Sorry, you have no permission for this action. Please log in";
        }

        /*response.getWriter().println("Please, put new values into fields");*/
        if (header.contains("/json")) {
            return advertParser.toJSON(advert);
        }
        if (header.contains("/xml")) {
            return advertParser.toXML(advert);
        }
        return advert.toString();
    }

    @RequestMapping(value = "/api/editAdvert/{advertId}", method = RequestMethod.PUT)
    public String saveAdvert(@PathVariable("advertId") int advertId, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        Advert advert = advertService.get(advertId);
        User user = userSecurity.getUserBySessionId(session.getId());
        if (user == null || advert.getUser().getId() != user.getId()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Sorry, you have no permission for this action. Please log in";
        }

        String title = request.getParameter("title");
        if (title != null) {
            advert.setTitle(title);
        }
        String text = request.getParameter("text");
        if (text != null) {
            advert.setText(text);
        }
        if (request.getParameter("price") != null) {
            double price = Double.parseDouble(request.getParameter("price"));
            advert.setPrice(price);
        }
        String currency = request.getParameter("currency");
        if (currency != null) {
            advert.setCurrency(currency);
        }
        String status = request.getParameter("status");
        if (status != null) {
            switch (status) {
                case "Забронировано":
                    status = "H";
                    break;
                case "Продано":
                    status = "S";
                    break;
                default:
                    status = "A";
            }
            advert.setStatus(status);
        }
        if (request.getParameter("categoryId") != null) {
            Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
            advert.setCategory(new Category(categoryId));
        }

        advert.setModificationDate(LocalDateTime.now());
        log.info("Updating advert " + advertId);
        advertService.update(advert);
        return "Your advert was successfully changed";
    }
}
