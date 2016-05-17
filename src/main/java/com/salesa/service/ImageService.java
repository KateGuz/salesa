package com.salesa.service;

import com.salesa.entity.Image;

public interface ImageService {

    void saveAdvertImage(Image image, int advertId);
    Image getAdvertImageById(int imageId);
    Image getAdvertImage(int advertId);

}
