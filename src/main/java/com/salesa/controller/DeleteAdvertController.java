package com.salesa.controller;

import com.salesa.entity.Advert;
import com.salesa.entity.User;
import com.salesa.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class DeleteAdvertController {

    @Autowired
    private AdvertService advertService;


    @RequestMapping(value = "/deleteAdvert/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteAdvert(@PathVariable("id") int id, HttpSession session) throws Exception {
        Advert advert = advertService.get(id);
        int userId = advert.getUser().getId();
        advertService.delete(id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }


}
