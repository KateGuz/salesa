package com.salesa.controller;

import com.salesa.entity.Image;
import com.salesa.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class AdvertImageController {

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/advert/{advertId}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int advertId) {
        Image advertImage = advertService.getAdvertImage(advertId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(advertImage.getContent(), headers,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/advert/{advertId}/image", method = RequestMethod.POST)
    public void doUpload(@PathVariable int advertId, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        Image image = new Image();
        image.setContent(multipartFile.getBytes());
        image.setType("X");
        advertService.saveAdvertImage(image, advertId);
    }

    @RequestMapping("/addImage")
    public String addImagePage() {
        return "addAdvertImage";
    }

}
