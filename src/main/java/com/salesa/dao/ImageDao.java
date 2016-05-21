package com.salesa.dao;

import com.salesa.entity.Image;
import com.salesa.entity.User;

import java.util.List;

public interface ImageDao {
    void saveAdvertImage(Image image, int advertId);
    Image getAdvertImageById(int imageId);
    List<Image> getAdvertImages(int advertId);
    void remove(int imageId);
}
