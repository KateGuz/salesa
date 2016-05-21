package com.salesa.service;

import com.salesa.entity.Image;

import java.util.List;

public interface ImageService {

    void saveAdvertImage(Image image, int advertId);
    Image getAdvertImageById(int imageId);
    List<Image> getAdvertImages(int advertId);
void remove(int imageId);
}
